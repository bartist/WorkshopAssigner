package data;

import java.util.ArrayList;
import java.util.Collections;

public class Vertex {
	private final ArrayList<Edge> to;
	private final ArrayList<Edge> from;

	private int totalFlow;
    private boolean fixFlag;


	public Vertex(){
		totalFlow = 0;
        to = new ArrayList<Edge>();
        from = new ArrayList<Edge>();
		fixFlag = false;
	}
	
	public void addEdge(Edge e){
		to.add(e);
	}

	public void addFrom(Edge e) { from.add(e); }

	public int overflow(int stream) {
		if(this.isEnd())
		    return stream;
		int flow = 0;
		
		Collections.shuffle(to);
		
		for(Edge e : to){
			flow += e.overflow(stream - flow);
			if(flow == stream) break;
		}
		totalFlow += flow;
		return flow;
	}


    public int getFlow() {
        return totalFlow;
    }

    public int fixFlow(int residu) {
	    if(this.isStart()) // start is found
	        return residu;

	    if(fixFlag) // if this vertex is already in the fixpath.
	        return 0;

	    fixFlag=true;

	    int remainder = residu;
	    for(Edge e : from) {
	        remainder -= e.fixFlow(remainder);
	        if(remainder==0)
	            break;
        }

        // When no path towards the start is possible
        if(remainder == residu){
	        for(Edge e: to) {
                remainder -= e.reverseFixFlow(remainder);
                if(remainder==0)
                    break;
            }
        }

        fixFlag=false;
        return residu-remainder;
    }

    public boolean isStart() {
		return from.isEmpty();
	}

	public boolean isEnd() {
		return to.isEmpty();
	}
}
