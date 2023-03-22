package puls;
import java.io.*;
import java.util.*;

public class Read{
//Odczytuje i zapisuje do tablicy wartości szybkości fali tętna z pliku
  private static final int NUM = 10;
  private static final float LENGTH = 20f;
  
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
  
  
  public float[] read(String filename) throws FileNotFoundException{
		
  File file = new File(filename);
  Scanner read = new Scanner(file);
	
	float[] tab = new float[500];
	int tabCount = 0;
	int mean1a = 0;
	int mean2a = 0;
	int[] tabValue1 = new int[NUM];
	int[] tabValue2 = new int[NUM];
	int startPulse = 0;
	int endPulse = 0;
	float waveTime = 0;
	int diff1 = 6;
	int diff2 = 6;
	boolean count = true;
	int line = 0;
	
	for(int i = 0; i < NUM; ++i){
		tabValue1[i] = read.nextInt();
	    mean1a += tabValue1[i];	        
	    tabValue2[i] = read.nextInt();
	    mean2a += tabValue2[i];
	    ++line;
	}
	Mean value = new Mean(mean1a, mean2a, tabValue1, tabValue2);
	
 while(line < 39900){
	changeValues(value, read.nextInt(), read.nextInt());
	++line;
	
	if(value.mean1 - mean1a >= diff1){
	   startPulse = line;
	   System.out.println("Pierwsza warto��");
	   
	   while(count){
	      if(value.mean2 - mean2a >= diff2){
	         endPulse = line;
	         System.out.println("Druga warto��");
	         count = false;
	       }
	      mean2a = value.mean2;
	      changeValues(value, read.nextInt(), read.nextInt());
	      ++line;
	   }
	
	if((endPulse != 0) && (startPulse != 0)){
	   System.out.println("Zapis");
	   waveTime = (float) ((endPulse - startPulse)*8.3);
	   System.out.println((LENGTH*10)/(waveTime));
	   
	   startPulse = 0;
	   endPulse = 0;
	   tab[tabCount] = LENGTH*10/waveTime;
	   ++tabCount;
	}
	   while(!count){
		   mean1a = value.mean1;
		   mean2a = value.mean2;
		   changeValues(value, read.nextInt(), read.nextInt());
		   ++line;
		   
		   if((value.mean1 - mean1a < diff1) && (value.mean2 - mean2a < diff2)){
			   count = true;
		   }
	   }
	}
	
    mean1a = value.mean1;
    mean2a = value.mean2;
 }

 read.close();
 return tab;
 }
}
