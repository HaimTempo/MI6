package bgu.spl.mics.application.messages;

import bgu.spl.mics.Event;
import bgu.spl.mics.application.subscribers.Moneypenny;
import java.util.List;

/**
 * implements {@link Event} object.
 * Sent by {@link Moneypenny}.
 * this event send all the @param agents to mission.
 */
public class SendAgentsEvent implements Event<Boolean> {
    private List<String> agents;
    private int time;
    
    public SendAgentsEvent(List<String> agents, int time) {
        this.agents = agents;
        this.time = time;
    }
    
    /**
	 * Sets the agents ids and the time to send them.
	 */
    public void setAgents(List<String> agents, int time) {
        this.agents = agents;
        this.time=time;
    }
    
    /**
	 * Sets the time to send the agents.
	 */
    public void setTime(int time) {
        this.time = time;
    }
    
    /**
	 * Retrieves the agents ids.
	 */
    public List<String> getAgents() {
        return agents;
    }
    
    /**
	 * Retrieves the time of the mission.
	 */
    public int getTime() {
        return time;
    }
}