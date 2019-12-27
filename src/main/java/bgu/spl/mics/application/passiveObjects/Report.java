package bgu.spl.mics.application.passiveObjects;

import java.util.List;

/**
 * Passive data-object representing a delivery vehicle of the store.
 * You must not alter any of the given public methods of this class.
 * <p>
 * You may add ONLY private fields and methods to this class.
 */
public class Report {
	private String missionName;
	private int M_ID;
	private int Moneypenny_ID;
	private List<String> agentsSerialNumbersNumber;
	private List<String> agentsNames;
	private	String gadget;
	private int qTime;
	private	int timeIssued;
	private int timeCreated;

	public Report(String missionName,
			int M_ID,
			int Moneypenny_ID,
			List<String> agentsSerialNumbersNumber,
			List<String> agentsNames,
			String gadget,
			int qTime,
			int timeIssued,
			int timeCreated) {
		
		this.missionName=missionName;
		this.M_ID=M_ID;
		this.Moneypenny_ID=Moneypenny_ID;
		this.agentsSerialNumbersNumber=agentsSerialNumbersNumber;
		this.agentsNames=agentsNames;
		this.gadget=gadget;
		this.qTime=qTime;
		this.timeIssued=timeIssued;
		this.timeCreated=timeCreated;
	}

	/**
	 * Retrieves the mission name.
	 */
	public String getMissionName() {
		return missionName;
	}

	/**
	 * Sets the mission name.
	 */
	public void setMissionName(String missionName) {
		this.missionName=missionName;
	}

	/**
	 * Retrieves the M's id.
	 */
	public int getM() {
		return M_ID;
	}

	/**
	 * Sets the M's id.
	 */
	public void setM(int m) {
		this.M_ID=m;
	}

	/**
	 * Retrieves the Moneypenny's id.
	 */
	public int getMoneypenny() {
		return Moneypenny_ID;
	}

	/**
	 * Sets the Moneypenny's id.
	 */
	public void setMoneypenny(int moneypenny) {
		Moneypenny_ID=moneypenny;
	}

	/**
	 * Retrieves the serial numbers of the agents.
	 * <p>
	 * @return The serial numbers of the agents.
	 */
	public List<String> getAgentsSerialNumbersNumber() {
		return agentsSerialNumbersNumber;
	}

	/**
	 * Sets the serial numbers of the agents.
	 */
	public void setAgentsSerialNumbersNumber(List<String> agentsSerialNumbersNumber) {
		this.agentsSerialNumbersNumber=agentsSerialNumbersNumber;
	}

	/**
	 * Retrieves the agents names.
	 * <p>
	 * @return The agents names.
	 */
	public List<String> getAgentsNames() {
		return agentsNames;
	}

	/**
	 * Sets the agents names.
	 */
	public void setAgentsNames(List<String> agentsNames) {
		this.agentsNames=agentsNames;
	}

	/**
	 * Retrieves the name of the gadget.
	 * <p>
	 * @return the name of the gadget.
	 */
	public String getGadgetName() {
		return gadget;
	}

	/**
	 * Sets the name of the gadget.
	 */
	public void setGadgetName(String gadgetName) {
		gadget=gadgetName;
	}

	/**
	 * Retrieves the time-tick in which Q Received the GadgetAvailableEvent for that mission.
	 */
	public int getQTime() {
		return qTime;
	}

	/**
	 * Sets the time-tick in which Q Received the GadgetAvailableEvent for that mission.
	 */
	public void setQTime(int qTime) {
		this.qTime=qTime;
	}

	/**
	 * Retrieves the time when the mission was sent by an Intelligence Publisher.
	 */
	public int getTimeIssued() {
		return timeIssued;
	}

	/**
	 * Sets the time when the mission was sent by an Intelligence Publisher.
	 */
	public void setTimeIssued(int timeIssued) {
		this.timeIssued=timeIssued;
	}

	/**
	 * Retrieves the time-tick when the report has been created.
	 */
	public int getTimeCreated() {
		return timeCreated;
	}

	/**
	 * Sets the time-tick when the report has been created.
	 */
	public void setTimeCreated(int timeCreated) {
		this.timeCreated=timeCreated;
	}
}
