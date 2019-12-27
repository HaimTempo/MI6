package bgu.spl.mics.application.publishers;

import bgu.spl.mics.Broadcast;
import bgu.spl.mics.MessageBroker;
import bgu.spl.mics.MessageBrokerImpl;
import bgu.spl.mics.Publisher;
import bgu.spl.mics.application.messages.TerminationEvent;
import bgu.spl.mics.application.messages.TickBroadcast;

/**
 * TimeService is the global system timer There is only one instance of this Publisher.
 * It keeps track of the amount of ticks passed since initialization and notifies
 * all other subscribers about the current time tick using {@link Tick Broadcast}.
 * This class may not hold references for objects which it is not responsible for.
 * 
 * You can add private fields and public methods to this class.
 * You MAY change constructor signatures and even add new public constructors.
 */
public class TimeService extends Publisher {
	private int duration;
	private MessageBroker mb;
	static private int currTime;
	private static int jumps=100;

	public TimeService(Integer duration) {
		super("Change_This_Name");
		this.duration=duration*jumps;
		mb=MessageBrokerImpl.getInstance();
	}

	public static int getCurrTime() {
		return currTime;
	}

	public void set_duration(int duration) {
		this.duration=duration;
	}

	public int get_duration() {
		return duration;
	}

	@Override
	protected void initialize() {
		//He is only a publisher.
	}

	@Override
	public void run() {
		try 
		{
			while(duration >= 0) {
				TickBroadcast b=new TickBroadcast(currTime);
				mb.sendBroadcast(b);
				duration-=jumps;
				Thread.sleep(jumps);
				System.out.println(duration);
				currTime+=jumps;
			}
			System.out.println("Terminatttttttttttttttttttttttttttttttttttttttttttttttttttttting");
			Broadcast end = new TerminationEvent();
			mb.sendBroadcast(end);
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
}
