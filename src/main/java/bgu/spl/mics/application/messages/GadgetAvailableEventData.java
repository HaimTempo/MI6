package bgu.spl.mics.application.messages;

public class GadgetAvailableEventData{
	private Boolean gadgetAvlabla;
	private int qTime;
	
	/**
	 * Contains answer to the get request of {@link GadgetAvailableEvent}
	 */
	public GadgetAvailableEventData(Boolean gadgetAvlabla, int qTime) {
		this.gadgetAvlabla = gadgetAvlabla;
		this.qTime = qTime;
	}
	
	public GadgetAvailableEventData() {
		
	}
	
	/**
	 * Retrieves the gadget availability status.
	 */
	public Boolean getGadgetAvlabla() {
		return gadgetAvlabla;
	}
	
	/**
	 * Sets the gadget availability status.
	 */
	public void setGadgetAvlabla(Boolean gadgetAvlabla) {
		this.gadgetAvlabla = gadgetAvlabla;
	}
	
	/**
	 * Retrieves the q initialization time.
	 */
	public int getqTime() {
		return qTime;
	}
	
	/**
	 * Sets the q initialization time.
	 */
	public void setqTime(int qTime) {
		this.qTime = qTime;
	}	
}
