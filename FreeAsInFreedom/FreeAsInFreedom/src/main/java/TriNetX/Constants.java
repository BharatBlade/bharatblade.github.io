package TriNetX;

import java.util.ArrayList;

public class Constants {
	
	public char [] a = new char [16];
	public char [] b = new char [94];

	
	
	public String id = "";
	public String sex = "";
	public String race = "";
	public String ethnicity = "";
	public String marital_status = "";
	public String yob = "";
	public String rym = "";
	public String myd = "";
	public String postal = "";
	public String region = "";
	public String source = "";
	
	public String [][][] ICD = 	{     
									{	
										{"Diabetes","E10","E11","E12","E13","E14","250"},
										{"Diabetes1","E10","250.*1","250.*3"},
										{"Diabetes2","E11","250.*0","250.*2"},
										{"DiabetesOther","E12","E13","E14","249"},
										{"Obesity","E65","E66","278.0","278.1"},
										{"Hyperlipidemia","E78","272"},
										{"Hypertension","I10","I11","I12","I13","I14","I15","401","402","403","404","405"},
										{"GestationalDiabetes","O24","648.0","648.6"}
									},
									{	
										{"PeripheralArterialDisease","I73.89","I73.9","443.89","443.9"},
										{"IschemicHeartDisease","I20","I21","I22","I23","I24","I25.1","I25.2","I25.5","I25.6","I25.7","I25.8","I25.9","410","411","412","413","414"},
										{"ArrhythmiaUS","I44","I45","I46","I47","I48","I49","426","427"},
										{"HeartFailure","I50","428"},
										{"Cardiomyopathy","I42","425"},
										{"HeartFailureSweden","I50","I42.0","I42.1","I42.2","I42.3","I42.4","I42.5","I42.6","I42.7","I42.8","I42.9","425","428"},
										{"CardiacArrest","I46","427.5"},
										{"CerebrovascularTIASweden","I60","I61","I62","I63","I64","I65","I66","I67","I68","I69","G45","430","431","432","433","434","435","436","437","438"},
										{"ArteriosclerosisSweden","I70","I71","I72","I73","I74","440","441","442","443","444"},
										{"RCVS","I67.841","435.9"},
										{"VenousThromboembolism","I80","I26","451B","415B"},
										{"CardiovascularAnySweden","I0","I1","I2","I3","I4","I5","I6","I7","I73.0","I74","I75"},
										{"AcuteCVDADHDSweden","I20","I21","I22","I23","I24","I25","I26","I50","I60","I61","I62","I63","I64","I65","I66","I67","I68","I69","I80"}
									},
									{
										{"Asthma","J45","493"},
										{"AnkylosingSpondylitis","M45","720.0"},
										{"Celiac","K90.0","579.0"},
										{"Crohn","K50","555"},
										{"Eczema","L20","L21","L22","L23","L24","L25","L26","L27","L30","690","691","692","693"},
										{"Graves","E05.00","242.00"},
										{"Hashimoto","E06.3","245.2"},
										{"MultipleSclerosis","G35","340"},
										{"Psoriasis","L40","696.0","696.1","694.3"},
										{"RheumatoidArthritis","M05","M06","714"},
										{"Sarcoidosis","D86","135"},
										{"sjogren","M35.0","710.2"},
										{"SLE","M32","710.0"},
										{"UlcerativeColitis","K51","556"}
									}
								};
	public boolean [][] ICDHas = {new boolean[ICD[0].length], new boolean[ICD[1].length], new boolean[ICD[2].length]};
	public int [][] ICD_date = {new int[ICD[0].length], new int[ICD[1].length], new int[ICD[2].length]};
	
//	public boolean diabetes = false; 			public int diabetes_date = 0;				//E10, E11, E12-E14											//250								0
//	public boolean diabetes1 = false; 			public int diabetes1_date = 0;				//E10														//250.X1, 250.X3					1
//	public boolean diabetes2 = false; 			public int diabetes2_date = 0;				//E11														//250.X0, 250.X2					2
//	public boolean diabetesOther = false; 		public int diabetesOther_date = 0;			//E12-E14													//249								3
//	public boolean obesity = false; 			public int obesity_date = 0;				//E65-E66													//278.0-278.1						4
//	public boolean hyperlipidemia = false; 		public int hyperlipidemia_date = 0;			//E78 														//272								5
//	public boolean HTN = false; 				public int HTN_date = 0;					//I10-I15													//401-405							6
//	public boolean gestationalDiabetes = false; public int gestationalDiabetes_date = 0;	//O24														//648.0, 648.6						7
//	
//	public boolean [] metab = new boolean[8];	//0
//	public int [] metab_date = new int[8];
//	
//	public boolean PAD = false; 				public int PAD_date = 0;					//I73.89-I73.9												//443.89-443.9						0
//	public boolean IHD = false; 				public int IHD_date = 0;					//I20-I24, I25.1-I25.2, I25.5-I25.9							//410-414							1
//	public boolean arrhythmia = false; 			public int arrhythmia_date = 0;				//I44-I49													//426-427							2
//	public boolean heartFailure = false; 		public int heartFailure_date = 0;			//I50														//428								3
//	public boolean cardiomyopathy = false; 		public int cardiomyopathy_date = 0;			//I42														//425								4
//	public boolean heartFailureSweden = false; 	public int heartFailureSweden_date = 0;		//I50,I42.0-I42.9											//425, 428							5
//	public boolean cardiacArrest = false; 		public int cardiacArrest_date = 0;			//I46														//427.5								6
//	public boolean cvrTiaSweden = false; 		public int cvrTiaSweden_date = 0;			//I60-I69, G45												//430-438							7
//	public boolean arteriosclerSweden = false; 	public int arteriosclerSweden_date = 0;		//I70-I74													//440-444							8
//	public boolean RCVS = false; 				public int RCVS_date = 0;					//I67.841													//435.9								9
//	public boolean VenousThromboembol = false; 	public int VenousThromboembol_date = 0;		//I80, I26													//451B, 415B		   			   10
//	public boolean cvrAnySweden = false;		public int cvrAnySweden_date = 0;			//I00-I70, I73.0, I74-I75														   			   11
//	public boolean acuteCVDADHDSweden = false;	public int acuteCVDADHDSweden_date = 0;		//I20-I25, I26, I50, I60-I69, I80												   			   12
//	
//	public boolean [] cvd = new boolean[13];	//1
//	public int [] cvd_date = new int[13];
//	
//	public boolean epilepsy = false; 			public int epilepsy_date = 0;
//	public boolean huntington = false; 			public int huntington_date = 0;
//	
//	public boolean asthma = false; 				public int asthma_date = 0;					//J45														//493								0
//	public boolean ankylosing = false; 			public int ankylosing_date = 0;				//M45														//720.0								1
//	public boolean celiac = false; 				public int celiac_date = 0;					//K90.0														//579.0								2
//	public boolean crohn = false; 				public int crohn_date = 0;					//K50														//555								3
//	public boolean eczema = false; 				public int eczema_date = 0;					//L20-L27, L30												//690-693							4
//	public boolean graves = false; 				public int graves_date = 0;					//E05.00													//242.00							5
//	public boolean hashimoto = false; 			public int hashimoto_date = 0;				//E06.3														//245.2								6
//	public boolean MS = false; 					public int MS_date = 0;						//G35														//340								7
//	public boolean Psoriasis = false; 			public int Psoriasis_date = 0;				//L40														//696.0-696.1, 694.3				8
//	public boolean Rheumatoid = false; 			public int Rheumatoid_date = 0;				//M05-M06													//714								9
//	public boolean sarcoidosis = false; 		public int sarcoidosis_date = 0;			//D86														//135					   		   10
//	public boolean sjogren = false; 			public int sjogren_date = 0;				//M35.0X													//710.2				   			   11
//	public boolean SLE = false; 				public int SLE_date = 0;					//M32														//710.0				   			   12
//	public boolean UC = false; 					public int UC_date = 0;						//K51														//556					   		   13
//
//	public boolean [] immune = new boolean[14];	//2
//	public int [] immune_date = new int[14];
//	
//	public boolean ADHD = false;				public int ADHD_date = 0;					//F90.0, F90.1, F90.2, F90.8, F90.9							//314.0, 314.0X						0
//	public boolean conduct = false; 			public int conduct_date = 0;				//F91.0, F91.1, F91.2, F91.3, F91.8, F91.9					//312.00-312.03, 312.10-312.13,		1
//																																						//312.20-312.23, 312.4,
//																																						//312.81-312.82, 312.89-312.9,
//																																						//313.81
//	public boolean ODD = false; 				public int ODD_date = 0;					//F91.3														//313.81							2
//	public boolean ID = false; 					public int ID_date = 0;						//F70-F73, F78-F79											//317, 318.0-318.2, 319				3
//	public boolean PervasiveSpecific = false; 	public int PervasiveSpecific_date = 0;		//F80.0-F80.2, F80.4, F80.81-F80.82, F80.89-F80.9, 			//299.00-299.01, 299.10-299.11,		4
//																							//F81.0, F81.2, F81.81, F81.89-F81.9, F82,					//299.80-299.81, 299.90-299.91,
//																							//F84.0, F84.2-F84.3, F84.5, F84.8-F84.9, F88-F89			//315.00-315.02, 315.09-315.2,
//																																						//315.31-315.32, 315.34-315.35,
//																																						//315.39-315.5, 315.8-315.9
//	public boolean autism = false; 				public int autism_date = 0;					//F84.0														//299.00-299.01						5
//	public boolean OCD = false; 				public int OCD_date = 0;					//F42.2-F42.4, F42.8-F42.9									//300.3								6
//	
//	public boolean [] psych = new boolean[7];	//3
//	public int [] psych_date = new int[7];
//	
//	public boolean alcoholAbuse = false;		public int alcoholAbuse_date = 0;			//F10																							0
//	public boolean drugAbuse = false; 			public int drugAbuse_date = 0;				//F11-F16, F18-F19																				1
//	public boolean substance = false; 			public int substance_date = 0;				//F10-F19													291-292, 303-305					2
//	public boolean gambling = false; 			public int gambling_date = 0;				//F63.0, Z72.6												312.31, V69.3						3
//	public boolean smoking = false; 			public int smoking_date = 0;				//F17														305.1								4
//	
//	public boolean [] addict = new boolean[5];	//4
//	public int [] addict_date = new int[5];
//
//	public boolean accInjury = false;		public int accidentInjury_date = 0;			//S00-T14, V01-X59												800-904, 910-929, 950-957, 959,		0
//																						//																E807-E849, E880-E888, E916-E920,
//																						//																E922
//	public boolean transportAcc = false;	public int transportAcc_date = 0;			//V01-V99, X60-X84																					1
//	public boolean selfHarm = false;		public int selfHarm_date = 0;				//Y10-Y34																							2
//	
//	public boolean [] injury = new boolean[3];	//5
//	public int [] injury_date = new int[3];
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	ArrayList<boolean[]> fin = new ArrayList<boolean[]>();
//	ArrayList<int[]> fin_date = new ArrayList<int[]>();
	
	
	
	public String compress(String z) {
		String [] temp = z.split("(?<=\\G........)");
		String temp2 = "";
		for(int i = 0; i < temp.length; i++) temp2 += convert(temp[i]);
		return "'" + temp2;
	}
	
	public String convert(String z) {
		for(int i = 48; i < 58; i++) a[i-48] = ((char)(i));
		for(int i = 97; i < 103; i++) a[i-87] = ((char)(i));
		for(int i = 33; i < 127; i++) b[i-33] = ((char)(i));
		String ans = "";
		double total = 0;
		for(int i = 0; i < z.length(); i++)
			for(int j = 0; j < a.length; j++)
				if(z.substring(i, i+1).equals(Character.toString(a[j]))) {
					total += (j*Math.pow(16, 7-i));
					break;
				}
		for(int i = 4; i >= 0; i--) 
			ans += b[(int)(total / Math.pow(94, i) % 94)];
		return ans;
	}
	
}
