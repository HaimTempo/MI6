package bgu.spl.mics.application.subscribers;

import bgu.spl.mics.application.messages.GadgetAvailableEvent;
import bgu.spl.mics.application.messages.GadgetAvailableEventData;
import bgu.spl.mics.Subscriber;
import bgu.spl.mics.application.messages.TickBroadcast;
import bgu.spl.mics.application.passiveObjects.Inventory;

/**
 * Q is the only Subscriber\Publisher that has access to the {@link bgu.spl.mics.application.passiveObjects.Inventory}.
 *
 * You can add private fields and public methods to this class.
 * You MAY change constructor signatures and even add new public constructors.
 */
public class Q extends Subscriber {
	private Inventory inv = Inventory.getInstance();
	private int qtime;
		
	static private Q instance = new Q();
	private Q() 
	{
		super("Q");
	}
	
	public static Q getInstance() {
		return instance;
	}
	
	public boolean getItem(String gadget) 
	{
		return inv.getItem(gadget);
	}
	
	public void unregister() {
		this.unregister();
	}
	
	@Override
	protected void initialize() {
		this.subscribeEvent(GadgetAvailableEvent.class, answer ->{
			String gadget_to_check = answer.getGadget();
			boolean exists=getItem(gadget_to_check);
			GadgetAvailableEventData data;
			
			if(exists)
			{
				data = new GadgetAvailableEventData(true, qtime);
			}
			else
			{
				data = new GadgetAvailableEventData(false, qtime);			
			}
			this.complete(answer, data);
		});
		
		this.subscribeBroadcast(TickBroadcast.class,message -> {
			this.qtime =message.getCurrentTime();
		});
	}
}
