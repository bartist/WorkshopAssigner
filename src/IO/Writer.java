package IO;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class Writer {

	public static void writeTo(String target, String[] data, int numberOfWorkshops){
        try(PrintWriter file = new PrintWriter(target, StandardCharsets.UTF_8)){
            StringBuilder header = new StringBuilder();
            header.append("name");
            for(int i = 1; i <= numberOfWorkshops; i++){
                header.append(";workshop").append(i);
            }

            file.println(header);
            for(String participant : data) {
                file.println(participant);
            }

        } catch (Exception e) {
            System.out.println("Result file could not be produced");
        }
    }

}