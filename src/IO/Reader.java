package IO;
import java.util.ArrayList;
import java.util.Scanner;

import data.entity.Participant;

public class Reader {

	public static ArrayList<Participant> readParticipants(String src){
        Scanner sc = IOcreator.createScanner(src);
        if(sc == null){
            return null;
        }

        // Skip header line
        sc.nextLine();

        ArrayList<Participant> result = new ArrayList<>();
        while(sc.hasNextLine()){
            String input = sc.nextLine();
            String[] inputSplit = input.split(";");
            String name = inputSplit[4];
            ArrayList<String> preferences = new ArrayList<>();


            int index = inputSplit.length-2;
            while(inputSplit[index].charAt(0) != '"'){
                preferences.addFirst(inputSplit[index]);
                index--;
            }
            preferences.addFirst(inputSplit[index].substring(1));
            result.add(new Participant(name, preferences));
        }
        return result;
    }

    public static ArrayList<String[]> readWorkshops(String src){
        Scanner sc = IOcreator.createScanner(src);
        if(sc == null){
            return null;
        }

        sc.nextLine();

        ArrayList<String[]> result = new ArrayList<>();
        while(sc.hasNextLine()){
            String input = sc.nextLine();
            String[] workshops = input.split(";");

            result.add(workshops);
        }
        return result;
    }
}
