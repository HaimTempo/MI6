package bgu.spl.mics.application.messages;

import bgu.spl.mics.Event;

/**
 * Implements {@link Event} object.
 * This event is for checking if the gadget wanted is available.
 */
public class GadgetAvailableEvent implements Event<GadgetAvailableEventData> {
	private String gadget;
	public GadgetAvailableEvent(String gadget) {
		this.gadget=gadget;
	}

	/**
	 * Sets the gadget.
	 */
	public void setGadget(String gadget) {
		this.gadget = gadget;
	}
	
	/**
	 * Retrieves the gadget.
	 */
	public String getGadget() {
		return gadget;
	}
}