package data;

import data.entity.Participant;
import data.vertex.ParticipantVertex;
import data.vertex.WorkshopVertex;

import java.util.ArrayList;

public class Graph {
    private ArrayList<ParticipantVertex> participants;
    private ArrayList<WorkshopVertex> workshops;
	private ArrayList<Edge> edges;
	private Vertex start;
	private Vertex end;
	private int workshopSize;
	
	public Graph(int workshopSize){
		start = new Vertex();
		end = new Vertex();
        participants = new ArrayList<>();
        workshops = new ArrayList<>();
		edges = new ArrayList<>();
		this.workshopSize = workshopSize;
	}

    public void addWorkshop(String name){
        WorkshopVertex workshop = new WorkshopVertex(name);

        workshops.add(workshop);
        edges.add(new Edge(workshop,end, workshopSize));
    }

	public void addParticipant(Participant p){
        ParticipantVertex participant = new ParticipantVertex(p);

        participants.add(participant);
        edges.add(new Edge(start, participant, 1));

        while(participant.hasNextPreference()){
            String workshop = participant.getNextPreference();
            for(WorkshopVertex w : workshops){
                if(w.is(workshop)){
                    edges.add(new Edge(participant,w,1));
                    break;
                }
            }
        }
	}
	
	public int overflow(){
		return start.overflow(Integer.MAX_VALUE);
	}

	public boolean isOptimalFlow(int resultFlow) {
		return resultFlow == participants.size();
	}

    public boolean fixFlow(int resultFlow) {
	    int residu = 0;
	    int fixFlowResult = end.fixFlow(residu);
		return this.isOptimalFlow(fixFlowResult + resultFlow);
    }

    public void pushDivision() {
        for(ParticipantVertex participant : participants){
            participant.pushChoice();
        }
    }
}
