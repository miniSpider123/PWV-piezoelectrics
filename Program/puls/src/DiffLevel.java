//package puls;
import java.io.*;
import java.util.*;

public class DiffLevel {
//Oblicza próg dyskryminacji
		int diff1;
		int diff2;
		
		DiffLevel(){};
	
	  private static final int NUM = 10;
//	  private static final int MEASURES = 40000;
	  	  
	  private static void changeValues(Mean object, int num1, int num2){
		  int mean1 = 0;
		  int mean2 = 0;

		  for(int i = 0; i < object.tab1.length - 1; ++i){
				object.tab1[i] = object.tab1[i + 1];
		        mean1 += object.tab1[i];
		        object.tab2[i] = object.tab2[i + 1];
		        mean2 += object.tab2[i];
		     }
		  
		  object.tab1[object.tab1.length - 1] = num1;
	      mean1 += object.tab1[object.tab1.length - 1];	        
	      object.tab2[object.tab1.length - 1] = num2;
	      mean2 += object.tab2[object.tab1.length - 1];
	      
	      object.mean1 = mean1;
	      object.mean2 = mean2;
	  }
	  
	  
   public static DiffLevel diffLevel(String filename) throws FileNotFoundException{
			
	  File file = new File(filename);
	  Scanner read = new Scanner(file);
		
		int mean1a = 0;
		int mean2a = 0;
//		int[] tab = new int[2];
		int[] tabValue1 = new int[NUM];
		int[] tabValue2 = new int[NUM];
		DiffLevel object = new DiffLevel();
		
		for(int i = 0; i < NUM; ++i){
			tabValue1[i] = read.nextInt();
		    mean1a += tabValue1[i];	        
		    tabValue2[i] = read.nextInt();
		    mean2a += tabValue2[i];
		}
		
		int maxDiff1 = 20;
		int minDiff1 = 8;
		int maxDiff2 = 20;
		int minDiff2 = 8;
		
		Mean value = new Mean(mean1a, mean2a, tabValue1, tabValue2);	

//		for(int i = 0; i < MEASURES - 10; ++i){
		    while(read.hasNext()){		 
		    	if((value.mean1 - mean1a > maxDiff1) && (value.mean1 < 1900)){
		    		maxDiff1 = value.mean1 - mean2a;
			    	System.out.println(value.mean1 + " max1");
		    	}
		    	if((value.mean1 - mean1a < minDiff1) && (value.mean1 > 1200)){
		    		minDiff1 = value.mean1 - mean2a;
			    	System.out.println(value.mean1 + " min1");
		    	}
		    	if((value.mean2 - mean2a > maxDiff2) && (value.mean2 < 1900)){
		    		maxDiff2 = value.mean2 - mean2a;
			    	System.out.println(value.mean2 + " max2");
		    	}
		    	if((value.mean2 - mean2a < minDiff2) && (value.mean2 > 1200)){
		    		minDiff2 = value.mean2 - mean2a;
			    	System.out.println(value.mean2 + " min2");
		    	}
		    	mean1a = value.mean1;
		    	mean2a = value.mean2;
		    	changeValues(value, read.nextInt(), read.nextInt());
		    }		
//		}
		read.close();
		object.diff1 = Math.abs((maxDiff1 + minDiff1)/2);
		object.diff2 = Math.abs((maxDiff2 + minDiff2)/2);
		
		return object;
	}
}