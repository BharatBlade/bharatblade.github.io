package Readmissions;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.avro.generic.GenericRecord;

import File.FastParquet;
import File.FastWriter;
import Math.FastStatistics;
import STARData.STARDataFollowUp;
import STARData.STARDataPatient;
public class Justin {
	
	public static TreeMap<Integer, STARDataPatient> patientDatabase = new TreeMap<Integer, STARDataPatient>();
	public static FastStatistics stats = new FastStatistics();
	public static FastParquet p = new FastParquet();
	
	public static void main(String[]args) {
		System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", "error");
		p.delimiter = ",";
		
		getLiverTransplantPatients();
		removeInconsistentBMIs();
		calcAvgBMI();
		calcAvgMELD();
		getLiverTransplantPatientFollowUps();
        setReadmissionStatus();
//        printUniqueValues();
        System.out.println("DIABETES STATS---------------------------------------------------------------");
        DiabetesStats();
        System.out.println("HCV STATS---------------------------------------------------------------");
        HCVStats();
        System.out.println("MALIGNANCY EVER STATS---------------------------------------------------------------");
        MalignancyStats();
        System.out.println("AGE STATS---------------------------------------------------------------");
        AgeStats();
        System.out.println("BMI STATS---------------------------------------------------------------");
        BMIStats();
        System.out.println("MELD STATS---------------------------------------------------------------");
        MELDStats();
        System.out.println("FUNCTIONAL STATUS STATS---------------------------------------------------------------");
        FuncStatusStats();
        System.out.println("MELD ROC---------------------------------------------------------------");
        MELDROC();
        
        System.out.println("NUMBER OF PATIENTS---------------------------------------------------------------");
        System.out.println(patientDatabase.size());
//		int count = 0;
//		while(patientDatabase.size() > 0) {
//			count += patientDatabase.firstEntry().getValue().followUps.size();
//			patientDatabase.remove(patientDatabase.firstKey());
//		}		
//		System.out.println(count);
	}
	
	public static void getLiverTransplantPatients() {
		String dirLiverData = "D:\\Research\\Justin\\STAR_STATA\\PARQUET FILES\\LIVER_DATA.parquet";
		p.parquetReader(dirLiverData);
		GenericRecord nextRecord;
		try {
			nextRecord = p.reader.read();
			while(nextRecord != null) {
				String code = nextRecord.get("PTCODE").toString();
				String TRR = nextRecord.get("TRRIDCODE").toString();
				String age = nextRecord.get("AGE").toString();
				boolean ptCodeIsValid = code.length() > 0 && 
										TRR.length() > 0 && 
										code.charAt(0) >= '0' && 
										code.charAt(0) <= '9' &&
										TRR.charAt(0) == 'A' &&
										age.charAt(0) >= '0' && 
										age.charAt(0) <= '9';
				if(ptCodeIsValid) {
					Integer codeInt = Integer.valueOf(code.substring(0, code.indexOf('.')));
					Integer ageInt = Integer.valueOf(age.substring(0, age.indexOf('.')));
					String dischargeDate = nextRecord.get("DISCHARGEDATE").toString();
					if(ageInt >= 18 && ageInt <= 100 && dischargeDate.length() > 0) {
						if(!patientDatabase.containsKey(codeInt)) {
							STARDataPatient patient = new STARDataPatient(nextRecord);
							patientDatabase.put(codeInt, patient);											
						}
						else {
							patientDatabase.get(codeInt).DISCHARGEDATE_DATA.add(dischargeDate);
							if(nextRecord.get("BMICALC").toString().length() > 0)
								patientDatabase.get(codeInt).BMICALC_DATA.add(nextRecord.get("BMICALC").toString());
							if(nextRecord.get("MELDPELDLABSCORE").toString().length() > 0)
								patientDatabase.get(codeInt).MELDPELDLABSCORE_DATA.add(nextRecord.get("MELDPELDLABSCORE").toString());
						}
					}
				}
				nextRecord = p.reader.read();
			}
			p.reader.close();
		} catch (Exception e) { e.printStackTrace(); }
	}
	
	public static void getLiverTransplantPatientFollowUps() {
		String dirLiverFollowUpData = "D:\\Research\\Justin\\STAR_STATA\\PARQUET FILES\\LIVER_FOLLOWUP_DATA.parquet";
		p.parquetReader(dirLiverFollowUpData);
        try {
			GenericRecord nextRecord = p.reader.read();
			while(nextRecord != null) {
				String code = nextRecord.get("PTCODE").toString();
				String TRR = nextRecord.get("TRRIDCODE").toString();
				boolean ptCodeIsValid = code.length() > 0 && 
										TRR.length() > 0 && 
										code.charAt(0) >= '0' && 
										code.charAt(0) <= '9' &&
										TRR.charAt(0) == 'A';
				if(ptCodeIsValid) {
					Integer codeInt = Integer.valueOf(code.substring(0, code.indexOf('.')));
					String Hosp = nextRecord.get("HOSP").toString();
					String pxStatDate = nextRecord.get("PXSTATDATE").toString();
					boolean patientExists = patientDatabase.containsKey(codeInt);
					boolean hospitalized = Hosp.length() == 1 && Hosp.charAt(0) == 'Y';
					boolean pxStatDateIsValid = (pxStatDate.length() > 7 && pxStatDate.charAt(4) == '-' && pxStatDate.charAt(7) == '-');
					if(patientExists && hospitalized && pxStatDateIsValid) {
						patientDatabase.get(codeInt).addFollowUp(nextRecord);
					}
				}
				nextRecord = p.reader.read();
			}
			p.reader.close();
        }
        catch(Exception e) {e.printStackTrace();}
	}
	
	public static void setReadmissionStatus() {
		Object[] arr = patientDatabase.keySet().toArray();
        for(int i = 0; i < arr.length; i++) {
			STARDataPatient patient = patientDatabase.get((Integer)arr[i]);
			for(int j = 0; j < patient.followUps.size(); j++) {
				STARDataFollowUp followUp = patient.followUps.get(j);
    			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        		LocalDate date2 = LocalDate.parse(followUp.PXSTATDATE_FOLLOWUP, formatter);
        		for(int k = 0; k < patient.DISCHARGEDATE_DATA.size(); k++) {
	        		LocalDate date = LocalDate.parse(patient.DISCHARGEDATE_DATA.get(k), formatter);
	        		int difference = (int) ChronoUnit.DAYS.between(date, date2);
	        		patientDatabase.get((Integer)arr[i]).followUps.get(j).daysSinceDischarge.add(difference);
	        		if(difference > 0) {
	        			for(int l = 0; l < stats.intervals.length; l++) {
	        				if(difference <= stats.intervals[l]) {
	        					patientDatabase.get((Integer)arr[i]).readmissions[l] = true;
	        				}
	        			}
	        		}
        		}
			}
		}
	}
	
	public static void calcAvgBMI() {
		Object[] arr = patientDatabase.keySet().toArray();
        for(int i = 0; i < arr.length; i++) {
			STARDataPatient patient = patientDatabase.get((Integer)arr[i]);
			ArrayList<Double> bmiDoubles = new ArrayList<Double>();
			if(patient.BMICALC_DATA.size() > 0) {
				for(int j = 0; j < patient.BMICALC_DATA.size(); j++) {
					bmiDoubles.add(Double.valueOf(patient.BMICALC_DATA.get(j)));
				}
				patient.averageBMI = stats.Mean(bmiDoubles);
			}
        }
	}
	
	public static void calcAvgMELD() {
		Object[] arr = patientDatabase.keySet().toArray();
        for(int i = 0; i < arr.length; i++) {
			STARDataPatient patient = patientDatabase.get((Integer)arr[i]);
			ArrayList<Double> meldDoubles = new ArrayList<Double>();
			if(patient.MELDPELDLABSCORE_DATA.size() > 0) {
				for(int j = 0; j < patient.MELDPELDLABSCORE_DATA.size(); j++) {
					meldDoubles.add(Double.valueOf(patient.MELDPELDLABSCORE_DATA.get(j)));
				}
				patient.averageMELD = stats.Mean(meldDoubles);
			}
        }
	}
	
	public static void removeInconsistentBMIs() {
		Object[] arr = patientDatabase.keySet().toArray();
        for(int i = 0; i < arr.length; i++) {
			STARDataPatient patient = patientDatabase.get((Integer)arr[i]);
			TreeSet<Double> bmiDoubles = new TreeSet<Double>();
			boolean failure = false;
			if(patient.BMICALC_DATA.size() > 0) {
				for(int j = 0; j < patient.BMICALC_DATA.size(); j++) {
					bmiDoubles.add(Double.valueOf(patient.BMICALC_DATA.get(j)));
				}
				double lowestBMI = bmiDoubles.first();
				double highestBMI = bmiDoubles.last();
				for(int j = 0; j < stats.BMI.length-1; j++) {
					boolean one = (lowestBMI > stats.BMI[j] && lowestBMI <= stats.BMI[j+1]);
					boolean two = (highestBMI > stats.BMI[j] && highestBMI <= stats.BMI[j+1]);
					if(one != two) {
						failure = true;
						break;
					}
				}
			}
			else {
				failure = true;
			}
			if(failure) {
				patientDatabase.remove((Integer)arr[i]);
			}
        }
	}
	
	public static void printUniqueValues() {
		TreeSet<String> tree = new TreeSet<String>();
		FastWriter writer = new FastWriter("tree.txt");
		Object[] arr = patientDatabase.keySet().toArray();
        for(int i = 0; i < arr.length; i++) {
			STARDataPatient patient = patientDatabase.get((Integer)arr[i]);
			tree.add(patient.AGE_DATA);
        }
        while(tree.size() > 0) {
        	writer.println(tree.first());
        	tree.remove(tree.first());
        }
		writer.close();
	}

	public static void DiabetesStats() {
		Object[] arr = patientDatabase.keySet().toArray();
		for(int i = 0; i < arr.length; i++) {
			STARDataPatient patient = patientDatabase.get((Integer)arr[i]);
			String strDiab = patient.DIAB_DATA;
			if(strDiab.equals("1.0")) {
    			stats.noDiab[stats.noDiab.length-1]++;
    			for(int j = 0; j < patient.readmissions.length; j++) {
        			if(patient.readmissions[j]) {
        				stats.noDiab[j]++;
        			}
    			}
    		}
    		else if(strDiab.equals("2.0") || strDiab.equals("3.0") || strDiab.equals("4.0") || strDiab.equals("5.0")) {
    			stats.diab[stats.diab.length-1]++;
    			for(int j = 0; j < patient.readmissions.length; j++) {
        			if(patient.readmissions[j]) {
        				stats.diab[j]++;
        			}
    			}
    		}
		}
        for(int i = 0; i < stats.diab.length-1; i++) {
        	System.out.println(stats.chiSquare(stats.diab[i], stats.noDiab[i], stats.diab[stats.diab.length-1], stats.noDiab[stats.noDiab.length-1]));
        }
        System.out.println(Arrays.toString(stats.diab));
        System.out.println(Arrays.toString(stats.noDiab));
	}

	public static void HCVStats() {
		Object[] arr = patientDatabase.keySet().toArray();
		for(int i = 0; i < arr.length; i++) {
			STARDataPatient patient = patientDatabase.get((Integer)arr[i]);
			String sero = patient.HCVSEROSTATUS_DATA;
			if(sero.equals("P")) {
				stats.hcv[stats.hcv.length-1]++;
    			for(int j = 0; j < patient.readmissions.length; j++) {
        			if(patient.readmissions[j]) {
        				stats.hcv[j]++;
        			}
    			}
			}
			else if(sero.equals("N")) {
				stats.nohcv[stats.nohcv.length-1]++;
    			for(int j = 0; j < patient.readmissions.length; j++) {
        			if(patient.readmissions[j]) {
        				stats.nohcv[j]++;
        			}
    			}
			}
		}
		for(int i = 0; i < stats.hcv.length-1; i++) {
			System.out.println(stats.chiSquare(stats.hcv[i], stats.nohcv[i], stats.hcv[stats.hcv.length-1], stats.nohcv[stats.nohcv.length-1]));
        }
        System.out.println(Arrays.toString(stats.hcv));
        System.out.println(Arrays.toString(stats.nohcv));
	}
	
	public static void MalignancyStats() {
		Object[] arr = patientDatabase.keySet().toArray();
		for(int i = 0; i < arr.length; i++) {
			STARDataPatient patient = patientDatabase.get((Integer)arr[i]);
			String malig = patient.MALIG_DATA;
			if(malig.equals("Y")) {
				stats.malig[stats.malig.length-1]++;
    			for(int j = 0; j < patient.readmissions.length; j++) {
        			if(patient.readmissions[j]) {
        				stats.malig[j]++;
        			}
    			}
			}
			else if(malig.equals("N")) {
				stats.noMalig[stats.noMalig.length-1]++;
    			for(int j = 0; j < patient.readmissions.length; j++) {
        			if(patient.readmissions[j]) {
        				stats.noMalig[j]++;
        			}
    			}
			}
		}
		for(int i = 0; i < stats.malig.length-1; i++) {
			System.out.println(stats.chiSquare(stats.malig[i], stats.noMalig[i], stats.malig[stats.malig.length-1], stats.noMalig[stats.noMalig.length-1]));
	    }
	    System.out.println(Arrays.toString(stats.malig));
	    System.out.println(Arrays.toString(stats.noMalig));
	}

	public static void AgeStats() {
		Object[] arr = patientDatabase.keySet().toArray();
		for(int i = 0; i < arr.length; i++) {
			STARDataPatient patient = patientDatabase.get((Integer)arr[i]);
			double age = Double.valueOf(patient.AGE_DATA);
			for(int j = 0; j < patient.readmissions.length; j++) {
      			if(patient.readmissions[j]) {
      				stats.ageHosp[j] += age;
      				stats.ageHospCount[j]++;
      				stats.ageHospList.get(j).add(age);
      				
      			}
      			else {
      				stats.ageNoHosp[j] += age;
      				stats.ageNoHospCount[j]++;
      				stats.ageNoHospList.get(j).add(age);
      			}
  			}
		}
		for(int j = 0; j < stats.ageHosp.length; j++) {
			stats.ageHosp[j] = stats.ageHosp[j]/stats.ageHospCount[j];
			stats.ageNoHosp[j] = stats.ageNoHosp[j]/stats.ageNoHospCount[j];
		}
	    System.out.println(Arrays.toString(stats.ageHosp));
	    System.out.println(Arrays.toString(stats.ageHospCount));
	    System.out.println(Arrays.toString(stats.ageNoHosp));
	    System.out.println(Arrays.toString(stats.ageNoHospCount));
	    for(int i = 0; i < stats.ageHospList.size(); i++)
	    	System.out.println(stats.tTest(stats.ageHospList.get(i), stats.ageNoHospList.get(i)));
	}
	
	public static void BMIStats() {
		Object[] arr = patientDatabase.keySet().toArray();
		for(int i = 0; i < arr.length; i++) {
			STARDataPatient patient = patientDatabase.get((Integer)arr[i]);
    		double b = patient.averageBMI;
    		for(int j = 0; j < patient.readmissions.length; j++) {
        		if(patient.readmissions[j]) {
        			for(int k = 0; k < stats.BMI.length-1; k++) {
        				if(b > stats.BMI[k] && b <= stats.BMI[k+1]) {
               				stats.bmiHosp[j][k] += b;
               				stats.bmiHospCount[j][k]++;
               				stats.bmiHospList.get(j).add(b);
               				stats.bmiSpecificHospList.get(j).get(k).add(b);
        				}
        			}
    			}
        		else {
        			for(int k = 0; k < stats.BMI.length-1; k++) {
        				if(b > stats.BMI[k] && b <= stats.BMI[k+1]) {
               				stats.bmiNoHosp[j][k] += b;
               				stats.bmiNoHospCount[j][k]++;            					
               				stats.bmiNoHospList.get(j).add(b);
               				stats.bmiSpecificNoHospList.get(j).get(k).add(b);
        				}
        			}
        		}
    		}
        }
		for(int i = 0; i < stats.bmiHosp.length; i++) {
			for(int j = 0; j < stats.bmiHosp[i].length; j++) {
				stats.bmiHosp[i][j] = stats.bmiHosp[i][j]/stats.bmiHospCount[i][j];
				stats.bmiNoHosp[i][j] = stats.bmiNoHosp[i][j]/stats.bmiNoHospCount[i][j];
			}
		}
		    
		for(int i = 0; i < stats.bmiHosp.length; i++) { System.out.println(Arrays.toString(stats.bmiHosp[i])); }
		for(int i = 0; i < stats.bmiHospCount.length; i++) { System.out.println(Arrays.toString(stats.bmiHospCount[i])); }
		for(int i = 0; i < stats.bmiNoHosp.length; i++) { System.out.println(Arrays.toString(stats.bmiNoHosp[i])); }
		for(int i = 0; i < stats.bmiNoHospCount.length; i++) { System.out.println(Arrays.toString(stats.bmiNoHospCount[i])); }
		System.out.println();
		for(int i = 0; i < stats.bmiHosp.length; i++) {
			for(int j = 0; j < stats.bmiHosp[i].length; j++) {
				System.out.print(stats.chiSquare(stats.bmiHospCount[i][j], stats.bmiNoHospCount[i][j], stats.sum(stats.bmiHospCount[i]), stats.sum(stats.bmiNoHospCount[i])) + ", ");
			}
			System.out.println();
		}
		System.out.println();
		for(int i = 0; i < stats.bmiHospList.size(); i++) {
			System.out.println(stats.tTest(stats.bmiHospList.get(i), stats.bmiNoHospList.get(i)));
		}
		System.out.println();
		System.out.println();
		for(int i = 0; i < stats.bmiSpecificHospList.size(); i++) {
			for(int j = 0; j < stats.bmiSpecificHospList.get(i).size(); j++) {
				System.out.print(stats.tTest(stats.bmiSpecificHospList.get(i).get(j), stats.bmiSpecificNoHospList.get(i).get(j)) + ", ");
			}
			System.out.println();
		}
	}
		
	public static void MELDStats() {
		Object[] arr = patientDatabase.keySet().toArray();
		for(int i = 0; i < arr.length; i++) {
			STARDataPatient patient = patientDatabase.get((Integer)arr[i]);
    		double b = patient.averageMELD;
    		for(int j = 0; j < patient.readmissions.length; j++) {
        		if(patient.readmissions[j]) {
        			for(int k = 0; k < stats.MELD.length-1; k++) {
        				if(b > stats.MELD[k] && b <= stats.MELD[k+1]) {
               				stats.meldHosp[j][k] += b;
               				stats.meldHospCount[j][k]++;
               				stats.meldHospList.get(j).add(b);
               				stats.meldSpecificHospList.get(j).get(k).add(b);
        				}
        			}
    			}
        		else {
        			for(int k = 0; k < stats.MELD.length-1; k++) {
        				if(b > stats.MELD[k] && b <= stats.MELD[k+1]) {
               				stats.meldNoHosp[j][k] += b;
               				stats.meldNoHospCount[j][k]++;            					
               				stats.meldNoHospList.get(j).add(b);
               				stats.meldSpecificNoHospList.get(j).get(k).add(b);
        				}
        			}
        		}
    		}
        }
		for(int i = 0; i < stats.meldHosp.length; i++) {
			for(int j = 0; j < stats.meldHosp[i].length; j++) {
				stats.meldHosp[i][j] = stats.meldHosp[i][j]/stats.meldHospCount[i][j];
				stats.meldNoHosp[i][j] = stats.meldNoHosp[i][j]/stats.meldNoHospCount[i][j];
			}
		}
		    
		for(int i = 0; i < stats.meldHosp.length; i++) { System.out.println(Arrays.toString(stats.meldHosp[i])); }
		for(int i = 0; i < stats.meldHospCount.length; i++) { System.out.println(Arrays.toString(stats.meldHospCount[i])); }
		for(int i = 0; i < stats.meldNoHosp.length; i++) { System.out.println(Arrays.toString(stats.meldNoHosp[i])); }
		for(int i = 0; i < stats.meldNoHospCount.length; i++) { System.out.println(Arrays.toString(stats.meldNoHospCount[i])); }
		
		for(int i = 0; i < stats.meldHosp.length; i++) {
			for(int j = 0; j < stats.meldHosp[i].length; j++) {
				System.out.print(stats.chiSquare(stats.meldHospCount[i][j], stats.meldNoHospCount[i][j], stats.sum(stats.meldHospCount[i]), stats.sum(stats.meldNoHospCount[i])) + ", ");
			}
			System.out.println();
		}
		System.out.println();
		for(int i = 0; i < stats.meldHospList.size(); i++) {
			System.out.println(stats.tTest(stats.meldHospList.get(i), stats.meldNoHospList.get(i)));
		}
		System.out.println();
		System.out.println();
		for(int i = 0; i < stats.meldSpecificHospList.size(); i++) {
			for(int j = 0; j < stats.meldSpecificHospList.get(i).size(); j++) {
				System.out.print(stats.tTest(stats.meldSpecificHospList.get(i).get(j), stats.meldSpecificNoHospList.get(i).get(j)) + ", ");
			}
			System.out.println();
		}
	}

	public static void FuncStatusStats() {
		Object[] arr = patientDatabase.keySet().toArray();
		for(int i = 0; i < arr.length; i++) {
			STARDataPatient patient = patientDatabase.get((Integer)arr[i]);
			if(patient.FUNCSTATTRR_DATA.length() == 6) {
	    		double funcstat = Double.valueOf(patient.FUNCSTATTRR_DATA);
	    		for(int j = 0; j < patient.readmissions.length; j++) {
	        		if(patient.readmissions[j]) {
	        			for(int k = 0; k < stats.funcStat.length; k++) {
	    	    			if(funcstat == stats.funcStat[k]) {
	    	    				stats.funcStatHosp[j][k]++;
	    	    				break;
	    	    			}
	        			}
	    			}
	        		else {
	        			for(int k = 0; k < stats.funcStat.length; k++) {
	    	    			if(funcstat == stats.funcStat[k]) {
	    	    				stats.funcStatNoHosp[j][k]++;
	    	    				break;
	    	    			}
	        			}
	        		}
	    		}
			}
        }
		for(int i = 0; i < stats.funcStatHosp.length; i++) {
	    	System.out.println(stats.intervals[i]);
	    	System.out.println(Arrays.toString(stats.funcStatHosp[i]));
	    	System.out.println(Arrays.toString(stats.funcStatNoHosp[i]));
	    }
		System.out.println();
		for(int i = 0; i < stats.funcStatHosp.length; i++) {
			for(int j = 0; j < stats.funcStatHosp[i].length; j++) {
				System.out.print(stats.chiSquare(stats.funcStatHosp[i][j], stats.funcStatNoHosp[i][j], stats.sum(stats.funcStatHosp[i]), stats.sum(stats.funcStatNoHosp[i])) + ", ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void MELDROC() {
		for(int i = 0; i < stats.meldSpecificHospList.size(); i++) {
			FastWriter writer = new FastWriter("ROC_FINAL" + stats.intervals[i] + ".csv");
			for(int j = 0; j < stats.meldSpecificHospList.get(i).size(); j++) {
				for(int k = 0; k < stats.meldSpecificHospList.get(i).get(j).size(); k++) {
					writer.println(stats.meldSpecificHospList.get(i).get(j).get(k) + ",1");
				}
				for(int k = 0; k < stats.meldSpecificNoHospList.get(i).get(j).size(); k++) {
					writer.println(stats.meldSpecificNoHospList.get(i).get(j).get(k) + ",0");
				}
			}
			writer.close();
		}
	}
}