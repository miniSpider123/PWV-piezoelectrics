//package puls;
import java.io.*;

public class Main {
	public static void main(String[] args) throws FileNotFoundException{
		DiffLevel ob = new DiffLevel();
		ob = ob.diffLevel("bzrez.txt");
		System.out.println(ob.diff1 + "\t" + ob.diff2);
		float[] array = new Read().read("bzrez.txt", ob.diff1, ob.diff2);
		new Write().write("szybkosc.txt", array);
		System.out.println("Koniec");
	}
}