package Math;
import java.util.ArrayList;

public class FastStatistics {
	public int [] intervals = {30, 90, 365, 365*5};
	public int [] diab = new int [intervals.length+1]; //30,90, 1, 5, Total
	public int [] noDiab = new int [intervals.length+1];
	public int [] hcv = new int [intervals.length+1]; //30,90, 1, 5, Total
	public int [] nohcv = new int [intervals.length+1];
	public double [] BMI = {0, 18.5, 25, 30, 35, 40, 50, 100};
	public double [][] bmiHosp = new double [intervals.length][BMI.length-1];
	public double [][] bmiHospCount = new double [intervals.length][BMI.length-1];
	public double [][] bmiNoHosp = new double [intervals.length][BMI.length-1];
	public double [][] bmiNoHospCount = new double [intervals.length][BMI.length-1];
	public ArrayList<ArrayList<Double>> bmiHospList = new ArrayList<ArrayList<Double>>();
	public ArrayList<ArrayList<Double>> bmiNoHospList = new ArrayList<ArrayList<Double>>();
	public ArrayList<ArrayList<ArrayList<Double>>> bmiSpecificHospList = new ArrayList<ArrayList<ArrayList<Double>>>();
	public ArrayList<ArrayList<ArrayList<Double>>> bmiSpecificNoHospList = new ArrayList<ArrayList<ArrayList<Double>>>();
	public double [] MELD = {6, 15, 21, 28, 40};
	public double [][] meldHosp = new double [intervals.length][BMI.length-1];
	public double [][] meldHospCount = new double [intervals.length][BMI.length-1];
	public double [][] meldNoHosp = new double [intervals.length][BMI.length-1];
	public double [][] meldNoHospCount = new double [intervals.length][BMI.length-1];
	public ArrayList<ArrayList<Double>> meldHospList = new ArrayList<ArrayList<Double>>();
	public ArrayList<ArrayList<Double>> meldNoHospList = new ArrayList<ArrayList<Double>>();
	public ArrayList<ArrayList<ArrayList<Double>>> meldSpecificHospList = new ArrayList<ArrayList<ArrayList<Double>>>();
	public ArrayList<ArrayList<ArrayList<Double>>> meldSpecificNoHospList = new ArrayList<ArrayList<ArrayList<Double>>>();
	ArrayList<ArrayList<Double>> MELDDistribution = new ArrayList<ArrayList<Double>>();
	public double [] ageHosp = new double [intervals.length];
	public double [] ageHospCount = new double [intervals.length];
	public double [] ageNoHosp = new double [intervals.length];
	public double [] ageNoHospCount = new double [intervals.length];
	public ArrayList<ArrayList<Double>> ageHospList = new ArrayList<ArrayList<Double>>();
	public ArrayList<ArrayList<Double>> ageNoHospList = new ArrayList<ArrayList<Double>>();
	public int [] malig = new int [intervals.length+1]; //30,90, 1, 5, Total
	public int [] noMalig = new int [intervals.length+1];
	public double [] funcStat = {2010, 2020, 2030, 2040, 2050, 2060, 2070, 2080, 2090, 2100};
	public double [][] funcStatHosp = new double [intervals.length][funcStat.length];
	public double [][] funcStatNoHosp = new double [intervals.length][funcStat.length];
	
	public FastStatistics() {
		for(int i = 0; i < intervals.length; i++) {
			bmiHospList.add(new ArrayList<Double>());
			bmiNoHospList.add(new ArrayList<Double>());
			meldHospList.add(new ArrayList<Double>());
			meldNoHospList.add(new ArrayList<Double>());
			ageHospList.add(new ArrayList<Double>());
			ageNoHospList.add(new ArrayList<Double>());
		}
		for(int i = 0; i < intervals.length; i++) {
			bmiSpecificHospList.add(new ArrayList<ArrayList<Double>>());
			bmiSpecificNoHospList.add(new ArrayList<ArrayList<Double>>());
			meldSpecificHospList.add(new ArrayList<ArrayList<Double>>());
			meldSpecificNoHospList.add(new ArrayList<ArrayList<Double>>());
			for(int j = 0; j < BMI.length-1; j++) {
				bmiSpecificHospList.get(i).add(new ArrayList<Double>());
				bmiSpecificNoHospList.get(i).add(new ArrayList<Double>());
			}
			for(int j = 0; j < MELD.length-1; j++) {
				meldSpecificHospList.get(i).add(new ArrayList<Double>());
				meldSpecificNoHospList.get(i).add(new ArrayList<Double>());				
			}
		}
	}
	public double chiSquare(double subY, double subN, double popY, double popN) {
		double expected = (subY + subN)*(popY/(popY+popN));
		double observed = subY;
		double score = Math.pow(observed - expected, 2)/expected;
		
		return score;
	}
	
	public double sum(double [] arr) {
		double total = 0;
		for(int i = 0; i < arr.length; i++) {
			total += arr[i];
		}
		return total;
	}
	
	public double Mean(ArrayList<Double> arr) {
        double sum = 0;
        for (int i = 0; i < arr.size(); i++)
            sum = sum + arr.get(i);
        return sum / arr.size();
    }
	public double Mean(double [] arr) {
        double sum = 0;
        for (int i = 0; i < arr.length; i++)
            sum = sum + arr[i];
        return sum / arr.length;
    }
 
    // Function to find standard
    // deviation of given array.
	public double standardDeviation(ArrayList<Double> arr, double mean) {
        double sum = 0;
        for (int i = 0; i < arr.size(); i++)
            sum = sum + (arr.get(i) - mean)
                  * (arr.get(i) - mean);
 
        return Math.sqrt(sum / (arr.size() - 1));
    }
	public double standardDeviation(double [] arr, double mean) {
        double sum = 0;
        for (int i = 0; i < arr.length; i++)
            sum = sum + (arr[i] - mean)
                  * (arr[i] - mean);
 
        return Math.sqrt(sum / (arr.length - 1));
    }
	
 
    // Function to find t-test of
    // two set of statistical data.
    public double tTest(ArrayList<Double> arr1, ArrayList<Double> arr2) {
        double mean1 = Mean(arr1);
        double mean2 = Mean(arr2);
        double sd1 = standardDeviation(arr1, mean1);
        double sd2 = standardDeviation(arr2, mean2);
 
        // Formula to find t-test
        // of two set of data.
        double t_test = (mean1 - mean2) /
                       Math.sqrt((sd1 * sd1) /
                    		   arr1.size() + (sd2 * sd2) / arr2.size());
        return t_test;
    }
    public double tTest(double [] arr1, double [] arr2) {
        double mean1 = Mean(arr1);
        double mean2 = Mean(arr2);
        double sd1 = standardDeviation(arr1, mean1);
        double sd2 = standardDeviation(arr2, mean2);
 
        // Formula to find t-test
        // of two set of data.
        double t_test = (mean1 - mean2) /
                       Math.sqrt((sd1 * sd1) /
                    		   arr1.length + (sd2 * sd2) / arr2.length);
        return t_test;
    }
}
