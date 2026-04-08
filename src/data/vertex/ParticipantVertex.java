package data.vertex;

import data.Vertex;
import data.entity.Participant;

public class ParticipantVertex extends Vertex {
    private Participant participant;

    public ParticipantVertex(Participant participant) {
        this.participant = participant;
        super();
    }

    public boolean hasNextPreference() {
        return participant.hasNextPreference();
    }

    public String getNextPreference() {
        return participant.getNextPreference();
    }
}
