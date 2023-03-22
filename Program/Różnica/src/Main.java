//package puls;
import java.io.*;

public class Main {
	public static void main(String[] args) throws FileNotFoundException{
		int[][] array = new Read().read("bzrez.txt");
		int[][] array2 = new Read().sum(array);
		new Write().write("roznice.txt", array);
		new Write().write("roznice_sumy.txt", array2);
		System.out.println("Koniec");
	}
}