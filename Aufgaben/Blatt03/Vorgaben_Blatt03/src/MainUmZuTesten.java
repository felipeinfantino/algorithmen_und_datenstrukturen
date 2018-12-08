import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class MainUmZuTesten {

	public static void main(String[] args) throws FastaException, IOException {
		FastaParser fp = new FastaParser();
		File f = new File("C:\\Users\\Felipe\\Desktop\\AlgoDat Tareas\\Aufgaben\\Blatt03\\Vorgaben_Blatt03\\src\\FASTA.txt");
		System.out.println(fp.handleException(f));
		
		
			
		
		
		

	}

}
