//package PWV;
import java.io.*;

public class Main {
	public static void main(String[] args) throws FileNotFoundException{
		float[] array = new Read().read("bzrez.txt");
		new Write().write("szybkosc.txt", array);
		System.out.println("Koniec");
	}
}