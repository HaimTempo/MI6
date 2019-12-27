package bgu.spl.mics.application.messages;

import java.util.List;
import bgu.spl.mics.Event;
import bgu.spl.mics.application.passiveObjects.Inventory;
import bgu.spl.mics.application.passiveObjects.Squad;
import bgu.spl.mics.application.subscribers.Intelligence;
import bgu.spl.mics.application.subscribers.Moneypenny;
import bgu.spl.mics.application.subscribers.Q;

/**
 * implements {@link Event} object.
 * Sent by {@link Intelligence}.
 * Invokes {@link Moneypenny} to check the {@link Squad} 
 * and {@link Q} to check the {@link Inventory}
 */
public class MissionReceivedEvent implements Event<Boolean> {
	private String name,gadget;
	private List<String> agentNumber;
	private int timeIss;
	private List<String> names;
	private int takeTime;
	
	public MissionReceivedEvent(String name, List<String> agentNumber, String gadget,
			int time, List<String> names, int takeTime){
		this.agentNumber=agentNumber;
		this.name=name;
		this.gadget=gadget;
		this.timeIss=time;
		this.names=names;
		this.takeTime=takeTime;
	}
	
	/**
	 * Retrieves the name of the mission.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Retrieves the duration of the mission.
	 */
	public int getTakeTime() {
		return takeTime;
	}
	
	/**
	 * Retrieves the agents ids.
	 */
	public List<String> getAgentNumber() {
		return agentNumber;
	}
	
	/**
	 * Retrieves the gadget.
	 */
	public String getGadget() {
		return gadget;
	}
	
	/**
	 * Retrieves the time issued.
	 */
	public int getTime() {
		return timeIss;
	}
	
	/**
	 * Retrieves the agents names.
	 */
	public List<String> getNames() {
		return names;
	}
	
	/**
	 * Sets the mission names.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Sets the agents ids.
	 */
	public void setAgentNumber(List<String> agentNumber) {
		this.agentNumber = agentNumber;
	}
	
	/**
	 * Sets the gadget.
	 */
	public void setGadget(String gadget) {
		this.gadget = gadget;
	}
	
	/**
	 * Sets the issued time.
	 */
	public void setTime(int time) {
		this.timeIss = time;
	}
	
	/**
	 * Sets the agents names.
	 */
	public void setNames(List<String> names) {
		this.names=names;
	}
	
	/**
	 * Sets the duration of the mission.
	 */
	public void setTakeTime(int takeTime) {
		this.takeTime=takeTime;
	}
}
