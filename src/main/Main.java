package main;

import IO.Reader;
import IO.Writer;
import data.Graph;
import data.entity.Participant;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

	static void main(String[] args){
		String preferenceSrc;
		String workshopSrc;
        String resultSrc = "result.csv";
		if(args.length<1 || args.length > 3){
			System.out.println("AssignWorkshop preferences.csv workshops.csv [endResult.csv]");
		}
		else {
            preferenceSrc = args[0];
            workshopSrc = args[1];
            if(args.length == 3){
                resultSrc = args[2];
            }

            ArrayList<Participant> participants = Reader.readParticipants(preferenceSrc);
            ArrayList<String[]> allDayWorkshops =  Reader.readWorkshops(workshopSrc);
            if(!(participants == null || allDayWorkshops == null)){
                Collections.shuffle(participants);
                for(String[] workshops : allDayWorkshops) {
                    Graph round = new Graph(20);

                    for(String workshop : workshops){
                        round.addWorkshop(workshop);
                    }

                    for(Participant participant : participants){
                        round.addParticipant(participant);
                    }

                    int flow = round.overflow();
                    if(!round.isOptimalFlow(flow)){
                        if(!round.fixFlow(flow)){
                            System.out.print("HAAAAAAA");
                            return;
                        }
                    }

                    round.pushDivision();
                    participants.sort(Participant::comparePreferenceIndex);
                }

                participants.sort(Participant::compareName);
                for(Participant participant : participants){
                    System.out.println(participant.createPrintable());
                }

                int minvalue = 0;
                for(int i = 0; i < allDayWorkshops.size(); i++){ minvalue += i;}
                int correction = participants.size()*minvalue;

                int allPreferenceValues = 0;
                for(Participant p : participants){ allPreferenceValues = p.addPreferenceIndex(allPreferenceValues);}

                System.out.println("\nPreference deviation: " + (allPreferenceValues-correction));
                System.out.println("Average deviation: " + Math.round((double) (allPreferenceValues - correction) * 100 / participants.size()) / 100.0);
            }

        }
	}
	
}
