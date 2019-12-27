package bgu.spl.mics.application.messages;

import java.util.List;
import bgu.spl.mics.Event;

/**
 * Implements {@link Event} object.
 */
public class GetAgentsEvent implements Event<Boolean>{
	List<String> agentsId;
	
	/**
	 * Sets the agents Ids.
	 */
	public void setAgentsEvent(List<String> agentsId){
		this.agentsId=agentsId;
	}
	
	/**
	 * Retrieves the agents Ids.
	 */
	public List<String> get_agentsId() {
		return agentsId;
	}
}
