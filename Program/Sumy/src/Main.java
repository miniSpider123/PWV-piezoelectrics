//package puls;
import java.io.*;

public class Main {
	public static void main(String[] args) throws FileNotFoundException{
		int[][] array = new Read().read("bzrez.txt");
		new Write().write("sumy.txt", array);
		System.out.println("Koniec");
	}
}