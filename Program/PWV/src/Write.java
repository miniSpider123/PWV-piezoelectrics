//package PWV;
import java.io.*;

public class Write {
	public void write(String filename, float tab[]) throws FileNotFoundException{
		try {
			System.out.println("Odczyt");
			PrintWriter write = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
			for(int i = 0; i < tab.length; ++i){
				write.println(tab[i]);
			}
			write.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}