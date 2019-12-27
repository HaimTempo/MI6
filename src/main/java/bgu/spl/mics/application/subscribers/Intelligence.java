package bgu.spl.mics.application.subscribers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import bgu.spl.mics.MessageBroker;
import bgu.spl.mics.MessageBrokerImpl;
import bgu.spl.mics.Subscriber;
import bgu.spl.mics.application.messages.MissionReceivedEvent;
import bgu.spl.mics.application.messages.TickBroadcast;
import bgu.spl.mics.application.passiveObjects.MissionInfo;

/**
 * A Publisher\Subscriber.
 * Holds a list of Info objects and sends them
 *
 * You can add private fields and public methods to this class.
 * You MAY change constructor signatures and even add new public constructors.
 */
public class Intelligence extends Subscriber {
	MessageBroker mb=MessageBrokerImpl.getInstance();
	List<MissionInfo> info;
	Iterator<MissionInfo> curMission;
	MissionInfo myMission;

	public Intelligence(int id,MissionInfo[] missions) {
		super("Intelligence"+id);
		info = new ArrayList<>();
		for (MissionInfo mission : missions) {
			info.add(mission);
		}
		info.sort((a,b)->a.getTimeIssued()-b.getTimeIssued());
		curMission = info.iterator();
	}

	/**
	 * subscribe to the relevant events and broadcasts
	 */
	@Override
	protected void initialize() {
		System.out.println("Intelligence init");
		this.subscribeBroadcast(TickBroadcast.class,message -> {
			System.out.println("Intelligence reseved info");
			int time = message.getCurrentTime();
			while (curMission.hasNext() && (  myMission==null || myMission.getTimeIssued() <= time))//add all relevent missions
			{
				myMission = curMission.next();
				MissionReceivedEvent e = new MissionReceivedEvent(myMission.getMissionName(), myMission.getSerialAgentsNumbers(), myMission.getGadget(),
								myMission.getTimeIssued(), myMission.getAgentsNames(),myMission.getDuration());
				mb.sendEvent(e);
			}
		});
	}


}
