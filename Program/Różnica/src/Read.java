//package puls;
import java.io.*;
import java.util.*;

public class Read{
//Odczytuje i zapisuje do tablicy wszystkie sumy
  private static final int NUM = 10;
  
  private void changeValues(Mean object, int num1, int num2){
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
  
  
  public int[][] read(String filename) throws FileNotFoundException{
//Obliczanie ró¿nic
	  
  File file = new File(filename);
  Scanner read = new Scanner(file);
	
	int[][] tab = new int[39990][2];
	int mean1a = 0;
	int mean2a = 0;
	int diff1 = 0;
	int diff2 = 0;
	int[] tabValue1 = new int[NUM];
	int[] tabValue2 = new int[NUM];
	int j = 1;
	
	for(int i = 0; i < NUM; ++i){
		tabValue1[i] = read.nextInt();
	    mean1a += tabValue1[i];	        
	    tabValue2[i] = read.nextInt();
	    mean2a += tabValue2[i];
	}
	
	Mean value = new Mean(mean1a, mean2a, tabValue1, tabValue2);
	
 while(j < 39990){
	changeValues(value, read.nextInt(), read.nextInt());
	diff1 = value.mean1 - mean1a;
	diff2 = value.mean2 - mean2a;
	tab[j][0] = diff1;
	tab[j][1] = diff2;
	mean1a = value.mean1;
	mean2a = value.mean2;
	++j;
	}

 read.close();
 return tab;
 }
  
  
  public int[][] sum(int[][] array) throws FileNotFoundException{
//Sumowanie ró¿nic		
		
		int[][] tab = new int[39990][2];
		int mean1a = 0;
		int mean2a = 0;
		int[] tabValue1 = new int[NUM];
		int[] tabValue2 = new int[NUM];
		int j = 10;
		int num = 0;
		
		for(int i = 0; i < NUM; ++i){
			tabValue1[i] = array[num][0];
		    mean1a += tabValue1[i];	        
		    tabValue2[i] = array[num][1];
		    mean2a += tabValue2[i];
		    ++num;
		}
		tab[0][0] = mean1a;
		tab[0][1] = mean2a;
		Mean value = new Mean(mean1a, mean2a, tabValue1, tabValue2);
		
	 while(j < 39990){
		changeValues(value, array[num][0], array[num][1]);
		tab[j][0] = value.mean1;
		tab[j][1] = value.mean2;
		++j;
		++num;
		}

	 return tab;
	 }
}
