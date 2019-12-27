package bgu.spl.mics.application.messages;

import java.util.List;

/**
 * {@link AgentAvailbleEvent} data object.
 * Contains answer to the get request of {@link AgentAvailbleEvent}: @param available,
 * all agents names: @param agentsNames and MoneyPenny ID: @param moneyPennyId.
 */
public class AgentAvailbleData {
	private boolean available;
	private List<String> agentsSerial;
	private int moneyPennyId;

	public AgentAvailbleData(boolean available, List<String> agentsSerial, int moneyPennyId) {
		this.available = available;
		this.agentsSerial = agentsSerial;
		this.moneyPennyId = moneyPennyId;
	}
	
	/**
	 * Sets the availability status.
	 */
	public void setAvailible(boolean availible) {
		this.available = availible;
	}
	
	/**
	 * Sets the sets agents names.
	 */
	public void setAgentsNames(List<String> agentsNames) {
		this.agentsSerial = agentsNames;
	}
	
	/**
	 * Sets MoneyPenny ID.
	 */
	public void setMoneyPennyId(int moneyPennyId) {
		this.moneyPennyId = moneyPennyId;
	}
	
	/**
	 * Returns availability status.
	 */
	public boolean isAvailible() {
		return available;
	}
	
	/**
	 * Retrieves the agents names.
	 */
	public List<String> getAgentsNames() {
		return agentsSerial;
	}
	
	/**
	 * Retrieves the MoneyPenny ID.
	 */
	public int getMoneyPennyId() {
		return moneyPennyId;
	}
}