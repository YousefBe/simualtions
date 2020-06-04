package simualtions;

import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class simulation {
 
	public static void RunSimulation() {
		Scanner z = new Scanner(System.in);
		Scanner input = new Scanner(System.in);

		try {
			int x;
			System.out.println("\t \t welcome to our simulation simple project ");
			System.out.println("our project only calculates daily simulated demands for 10 days ");
			System.out.println("Enter No of Demands :\n ");
			
			x=z.nextInt();
			//initialized the sum of freq. needed for calculating the Probability 
			double FrequncySum= 0.0;
			//Arrays For The APPlication 
			
			//demand Array
			int[] demands =new int [x];
			//Frequency
			int[] Frequancy =new int [x];
			//Intervals
			double[] intervalStart = new double[x];
			double[] intervalEnd = new double[x];
			
			//Array for Daily simulated Demands
			int[]simu=new int[10];
			
			//Arrays For Probability and cumulative  
			double[] prob =new double [x];
			double[] cumu= new double[x];
			
			//Array For random Numbers
			int[]rands= {52 , 37 ,82 ,69 ,98 , 96 ,33 ,50,88 ,90};
			
			double SimuSum = 0;
			DecimalFormat df = new DecimalFormat("#.###");
			
			
			for(int i=0 ; i<x ; i++) {
				System.out.println("Enter The Demand No " +(i+1) );
				demands[i] = input.nextInt();
				System.out.println("Enter The Frequancy Of Dmeand " +(i+1) );
				Frequancy[i]=input.nextInt();
			}
			
			//First For Loop To calculate the Frequancy Sum
			for(int i=0 ; i<x; i++) {
				FrequncySum+=Frequancy[i];
				
			}
			
			//loop to initialize  the Probability
			for(int i=0; i<x ; i++) {
				prob[i] = Frequancy[i]/FrequncySum;
			}	
			
			//loop to initialize  the cumulative

			for(int i=1 ; i<x ; i++) {
				cumu[0] = prob[0];
				cumu[i]+=cumu[i-1]+prob[i];
			}
			
			//loop to initialize  the intervals
			
			for(int i= 1 ; i<x ; i++) {
				intervalStart[0] = 01;
				intervalEnd[0]=cumu[0]*100;
				//first two values only
				//rest of intervals
				intervalStart[i]=(int)(intervalEnd[i-1]+1);
				intervalEnd[i]=(int)(cumu[i]*100);	
			}
			
			//first table that show demands , frequencies , probabilities , cumulative and intervals
			System.out.println("\n\n");
			  System.out. println("Demand \t Frequency \t Porbability \t cumulative \t\t\t\t\t Intervals"	);
			  for(int i=0 ; i<x ; i++) { 
			  System.out.println(demands[i] +" \t\t  " +Frequancy[i] +" \t\t  "+ prob[i]+" \t\t  "+ df.format(cumu[i]) +"\t\t\t\t\t  "+(int)intervalStart[i]+" -> "+(int)intervalEnd[i]);
			  }
			 
			  
			 //loop to initialize  the simulated daily demands array , using the function between implemented at the bottom of the class.
			for(int i=0 ; i<10 ; i++) {
				simu[i] = between(intervalStart ,intervalEnd ,rands[i] ,demands );
			}
			
			//second table that consists of days ,  Random Numbers , and simulated daily demands
			System.out.println("Day \t\t  Random Number \t\t simulated Daily Demand ");
			for(int i=0 ; i<10 ; i++) {
				System.out.println(i+1 +"\t\t  "+ rands[i] + "\t\t\t\t  "+simu[i]);
				SimuSum+=simu[i];
			}	
			
			//the average of daily simulated daily demands.
			System.out.println("\t\t\t\t\t\t  " +SimuSum/10 +" average daily demand ");
		} catch (InputMismatchException e) {
			System.out.println("invalid input , all inputs must be integers ! ");
		}
		System.out.println("Enter 1 to try again , Anywhere to exit ! ");
		try {
			input.nextLine();
			int x= input.nextInt();
			if(x == 1) {
				RunSimulation();
			}else {
				return;
			}
		} catch (InputMismatchException e) {
			System.out.println("invalid input , exiting ");
		}
		

	}
public static int between(double[] array1,double[] arr, int no , int[] arra  ) {
		
		for(int i=0 ; i<array1.length ; i++){
			if(no>array1[i] && no<=arr[i]) {
				return arra[i];
			}
			
		}
		return 0;
	}
	
	
}
