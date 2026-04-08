package IO;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class IOcreator {

	public static Scanner createScanner(String s){
		try {
			return new Scanner(new BufferedReader(new FileReader(s)));
		} catch (FileNotFoundException e) {
			System.out.println("File could not be opened");
		}
		return null;
	}
	
}
