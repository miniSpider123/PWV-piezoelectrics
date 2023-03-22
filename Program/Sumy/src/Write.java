//package puls;
import java.io.*;

public class Write {
//Zapisuje dane z przekazanej tablicy do pliku
	public void write(String filename, int tab[][]) throws FileNotFoundException{
		try {
			System.out.println("Odczyt");
			PrintWriter write = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
			for(int i = 0; i < 39990; ++i){
				for(int j = 0; j < 2; ++j){
					write.print(tab[i][j]);
					write.print("\t");
				}
				write.println();
			}
			write.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}