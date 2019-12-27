package bgu.spl.mics.application.messages;

import java.util.List;
import bgu.spl.mics.Event;

public class ReleaseAgentsEvent implements Event<Boolean> {
	private List<String> agentsId;

	/**
	 * implements {@link Event} object.
	 * Sent by {@link Moneypenny}.
	 * Invokes {@link Moneypenny} to release all the agents recieved: @param agentsId
	 */
	public ReleaseAgentsEvent(List<String> agentsId) {
		this.agentsId = agentsId;
	}
	
	public ReleaseAgentsEvent() {
	}

	/**
	 * retrieves all the agents ids.
	 * @returns this is the agents that will be returned from the future
	 */
	public List<String> getAgentsId() {
		return agentsId;
	}
	
	/**
	 * sets all the agents ids.
	 */
	public void setAgentsId(List<String> agentsId) {
		this.agentsId = agentsId;
	}
}