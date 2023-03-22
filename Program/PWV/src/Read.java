//package PWV;
import java.io.*;
import java.util.*;

public class Read{
  private static final int NUM = 10;
  private static final float LENGTH = 20f;
  
  public float[] read(String filename) throws FileNotFoundException{
		
	File file = new File(filename);
	Scanner read = new Scanner(file);
	
	float[] tab = new float[500];
	int tabCount = 0;
	int[] tabValue1 = new int[NUM];
	int[] tabValue2 = new int[NUM];
	int mean1 = 0;
	int mean1a = 0;
	int mean2 = 0;
	int mean2a = 0;
	int startPulse = 0;
	int endPulse = 0;
	float waveTime = 0;
	int diff1 = 6;
	int diff2 = 6;
	boolean count = true;
	int line = 0;
	
    for(int j = 0; j < NUM ; ++j){
        tabValue2[j] = read.nextInt();
        mean2 += tabValue2[j];	        
        tabValue1[j] = read.nextInt();
        mean1 += tabValue1[j]; 
        ++line;
    }
        
while(line < 39900){

    for(int i = 0; i < NUM - 1; ++i){
        tabValue2[i] = tabValue2[i+1];
        mean2 += tabValue2[i];	        
        tabValue1[i] = tabValue1[i+1];
        mean1 += tabValue1[i];       
     }  
     tabValue1[NUM - 1] = read.nextInt();
	 tabValue2[NUM - 1] = read.nextInt();
     mean2 += tabValue2[NUM - 1];
     mean1 += tabValue1[NUM - 1];
     ++line;
     
	if(mean1 - mean1a >= diff1){
	   startPulse = line;
	   System.out.println("Pierwsza wartoœæ");
	   
	   while(count){
	       if(mean2 - mean2a >= diff2){
	         endPulse = line;
	         System.out.println("Druga wartoœæ");
	         count = false;
	       }
	      mean2a = mean2;
	      mean2 = 0;
	      mean1 = 0;
	      for(int i = 0; i < NUM - 1; ++i){
	        tabValue2[i] = tabValue2[i+1];
	        mean2 += tabValue2[i];	        
	        tabValue1[i] = tabValue1[i+1];
	        mean1 += tabValue1[i];
	     }  
	      
		 tabValue1[NUM - 1] = read.nextInt();
	     tabValue2[NUM - 1] = read.nextInt();
	     mean2 += tabValue2[NUM - 1];
	     mean1 += tabValue1[NUM - 1];
	     ++line;
	   }
	  
	}
	
	if((endPulse != 0) && (startPulse != 0)){
	   System.out.println("Zapis");
	   waveTime = (float) ((endPulse - startPulse)*8.3);
	   System.out.println((LENGTH*10)/(waveTime));
	   
	   startPulse = 0;
	   endPulse = 0;
	   tab[tabCount] = LENGTH*10/waveTime;
	   ++tabCount;
	   
	   while(!count){
		   mean1a = mean1;
		   mean2a = mean2;
		   mean1 = 0;
		   mean2 = 0;
		   
		   for(int i = 0; i < NUM - 1; ++i){
			 tabValue2[i] = tabValue2[i+1];
			 mean2 += tabValue2[i];	        
			 tabValue1[i] = tabValue1[i+1];
			 mean1 += tabValue1[i];
		   }  
		   tabValue1[NUM - 1] = read.nextInt();
		   tabValue2[NUM - 1] = read.nextInt();
		   mean2 += tabValue2[NUM - 1];
		   mean1 += tabValue1[NUM - 1];
		   ++line;
		   
		   if((mean1 - mean1a < diff1) && (mean2 - mean2a < diff2)){
			   count = true;
		   }
		   
	   }
	   
	}
	
    mean1a = mean1;
    mean2a = mean2;
    mean1 = 0;
    mean2 = 0;
}

read.close();
return tab;
}
}
