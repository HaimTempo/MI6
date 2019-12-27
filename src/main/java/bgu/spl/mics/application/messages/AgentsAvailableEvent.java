package bgu.spl.mics.application.messages;

import java.util.List;
import bgu.spl.mics.Event;
import bgu.spl.mics.Message;

/**
 * implements {@link Message} object.
 * Returns {@link AgentAvailbleData}  
 */
public class AgentsAvailableEvent implements Event<AgentAvailbleData> {
	private List<String> agentsSerial;
	public AgentsAvailableEvent(List<String> agents){
		this.agentsSerial=agents;
	}
	
	/**
	 * Sets the agents list.
	 */
	public void setAgents(List<String> agents) {
		this.agentsSerial = agents;
	}
	
	/**
	 * Retrieves the agents serial list.
	 */
	public List<String> getAgents() {
		return agentsSerial;
	}
}