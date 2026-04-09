package data.vertex;

import data.Vertex;

public class WorkshopVertex extends Vertex {
    private String workshop;

    public WorkshopVertex(String workshop) {
        super();
        this.workshop = workshop;
    }

    public boolean is(String workshop) {
        return this.workshop.equals(workshop);
    }

    @Override
    public String pushChoice(){
        return workshop;
    }
}
