package College;
public class Cards {
	public static int count = 0;
	public static boolean success = false;
	
	public static void main(String[]args){
		//the target sum and product
		int sumTarget = 36;
		int prodTarget = 360;
		
		//the ten runs of 1000 tournaments
		for(int j = 0; j < 10; j++){
			success = false;
			count = 0;
			int [][] genepool = new int [30][10];
			for(int i = 0; i < genepool.length; i++){
				int [] genotype = {(int)(Math.random()*2),(int)(Math.random()*2),(int)(Math.random()*2),(int)(Math.random()*2),(int)(Math.random()*2),(int)(Math.random()*2),(int)(Math.random()*2),(int)(Math.random()*2),(int)(Math.random()*2),(int)(Math.random()*2)};
				genepool[i] = genotype;
			}
			
			//the 1000 tournaments
			for(int i = 0; i < 1000; i++){
				genepool = tournament(genepool, sumTarget, prodTarget);
				if(success)
					break;
			}
			
			//if there is no solution after 1000 tournaments
			//then print no solution
			if(!success){
				System.out.println("No solution found");
				System.out.println();
			}
		}
	}
	
	//returns combined error of a genotype's solution
	//by comparing it to the target sum and product
	public static double fitness(int [][] genepool, int i, int sumTarget, int prodTarget){
		int runningSum = 0;
		int runningProd = 1;
		for(int j = 0; j < genepool[i].length; j++){
			if(genepool[i][j] == 0)
				runningSum += j+1;
			else
				runningProd *= j+1;
		}
		double scaledSumError = Math.abs(((double)runningSum - sumTarget)/sumTarget);
		double scaledProdError = Math.abs(((double)runningProd - prodTarget)/prodTarget);
		double combinedError = Math.abs(scaledSumError + scaledProdError);
		return combinedError;
	}
	
	//determines fitness of two random genotypes
	//and returns changed array of the genepool after "evolution"
	public static int[][] tournament(int [][] genepool, int sumTarget, int prodTarget){
		int rand1 = (int)(Math.random()*genepool.length);
		int rand2 = (int)(Math.random()*genepool.length);
		double fit1 = fitness(genepool, rand1, sumTarget, prodTarget);
		double fit2 = fitness(genepool, rand2, sumTarget, prodTarget);
		if(fit1 == 0.000)
			check(genepool, rand1);
		else if(fit2 == 0.000)
			check(genepool, rand2);
		if(fit1 < fit2)
			genepool = evolve(genepool, rand1, rand2);
		else
			genepool = evolve(genepool, rand2, rand1);
		count++;
		return genepool;
	}
	
	//checks to make sure that the solution actually works
	//by calculating sum and product of the solution
	//and sets global "success" variable to true
	//which ends the tournaments of that run
	public static void check(int [][] genepool, int rand1){
		int tempSum = 0;
		int tempProd = 1;
		System.out.println("Tournaments: " + count);
		System.out.print("Genotype: ");
		for(int i = 0; i < genepool[rand1].length-1; i++)
			System.out.print(genepool[rand1][i] + ", ");
		System.out.print(genepool[rand1][genepool[rand1].length-1]);
		System.out.println();
		String temp = "";
		for(int i = 0; i < genepool[rand1].length; i++)
			if(genepool[rand1][i] == 0){
				tempSum += i+1;
				temp += (i+1) + ", ";
			}
		System.out.println("Sum Pile: " + temp.substring(0, temp.length()-2));
		temp = "";
		for(int i = 0; i < genepool[rand1].length; i++)
			if(genepool[rand1][i] == 1){
				tempProd *= i+1;
				temp += (i+1) + ", ";
			}
		System.out.println("Product Pile: " + temp.substring(0, temp.length()-2));
		System.out.println("Sum: " + tempSum);
		System.out.println("Product: " + tempProd);
		System.out.println();
		success = true;
	}
	
	//changes the genotype of the weaker solution towards the stronger solution
	public static int[][] evolve(int [][] genepool, int rand1, int rand2){
		for(int i = 0; i < 10; i++)
			if((int)(Math.random()*2) == 0)
				genepool[rand2][i] = genepool[rand1][i];
		for(int i = 0; i < 10; i++)
			if((int)(Math.random()*10) == 0)
				if(genepool[rand2][i] == 0)
					genepool[rand2][i] = 1;
				else
					genepool[rand2][i] = 0;
		return genepool;
	}
}