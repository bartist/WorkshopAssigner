package main;

import IO.Reader;
import IO.Writer;
import data.Graph;
import data.entity.Participant;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args){
		String preferenceSrc;
		String workshopSrc;
        String resultSrc = "verdeling.csv";
		if(args.length<1 || args.length > 3){
			System.out.println("AssignWorkshop preferences.csv workshops.csv [endresult.csv]");
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
                for(String[] workshops : allDayWorkshops) {
                    Graph round = new Graph(20);

                    for(String workshop : workshops){
                        round.addWorkshop(workshop);;
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
                }

                for(Participant participant : participants){
                    System.out.println(participant.createPrintable());
                }
            }

        }
	}
	
}
