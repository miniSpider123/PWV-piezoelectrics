package puls;
import java.io.*;
import java.util.*;

public class DiffLevel {
//Oblicza próg dyskryminacji
	  private static final int NUM = 10;
	  private static final int MEASURES = 40000;
	  
	  private void changeValues(Mean object, int num1, int num2){
		  int mean1 = 0;
		  int mean2 = 0;

		  for(int i = 0; i < object.tab1.length - 1; ++i){
				System.out.println(i);
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
	  
	  
	  public int[] diffLevel(String filename) throws FileNotFoundException{
			
	  File file = new File(filename);
	  Scanner read = new Scanner(file);
		
		int[] endTab = new int[2];
		int[] tab1 = new int[MEASURES - 10];
		int[] tab2 = new int[MEASURES - 10];
		int mean1a = 0;
		int mean2a = 0;
		int[] tabValue1 = new int[NUM];
		int[] tabValue2 = new int[NUM];
		int diff1 = 0;
		int diff2 = 0;
		int maxValue1 = 0;
		int maxValue2 = 0;
		for(int i = 0; i < NUM; ++i){
			tabValue1[i] = read.nextInt();
		    mean1a += tabValue1[i];	        
		    tabValue2[i] = read.nextInt();
		    mean2a += tabValue2[i];
		}
		    Mean value = new Mean(mean1a, mean2a, tabValue1, tabValue2);	

		for(int i = 0; i < MEASURES - 10; ++i){
		    if(read.hasNext()){		    
		    	tab1[i] = value.mean1 - mean1a;
		    	tab2[i] = value.mean2 - mean2a;
		    	mean1a = value.mean1;
		    	mean2a = value.mean2;
		    	if(value.mean1 > maxValue1){
		    		maxValue1 = value.mean1;
		    	}
		    	if(value.mean2 > maxValue2){
		    		maxValue2 = value.mean2;
		    	}
		    	changeValues(value, read.nextInt(), read.nextInt());
		    }
		    else{
		    	break;
		    }
		
		}
		read.close();
		return endTab;
		}
}
