package bgu.spl.mics.application.subscribers;

import java.util.List;
import bgu.spl.mics.Event;
import bgu.spl.mics.Future;
import bgu.spl.mics.MessageBroker;
import bgu.spl.mics.MessageBrokerImpl;
import bgu.spl.mics.Subscriber;
import bgu.spl.mics.application.messages.*;
import bgu.spl.mics.application.passiveObjects.Diary;
import bgu.spl.mics.application.passiveObjects.Report;

/**
 * M handles ReadyEvent - fills a report and sends agents to mission.
 *
 * You can add private fields and public methods to this class.
 * You MAY change constructor signatures and even add new public constructors.
 */

public class M extends Subscriber {
	private MessageBroker mb;
	Report r;
	int id;
	int time;

	public M(Integer id) {
		super("M"+id);
		mb=MessageBrokerImpl.getInstance();
		this.id=id;
	}
	
	private void relAgents(List<String> agents) {	//releases agents.
		Event<Boolean> e=new ReleaseAgentsEvent(agents);
		mb.sendEvent(e);
	}

	@Override
	protected void initialize(){
		//Updates the current time.
		subscribeBroadcast(TerminationEvent.class, message->{
			//TODO we need to clear something?
			mb.unregister(this);
			terminate();
			System.out.println(getName()+" terminated");
		});
		this.subscribeBroadcast(TickBroadcast.class,message -> {
			this.time =message.getCurrentTime();
		});
		this.subscribeEvent(MissionReceivedEvent.class, answer ->{
			System.out.println("M handles Mission!!!!!!!!!!!!!!!!!");
			GadgetAvailableEventData data=new GadgetAvailableEventData();
			//Checks if the agents are available.
			Event<AgentAvailbleData> e_agents=new AgentsAvailableEvent(answer.getAgentNumber());
			Future<AgentAvailbleData> f_agents=mb.sendEvent(e_agents);
			//if the agents are available checks availability of the gadget.
			if(f_agents.get().isAvailible()==true) 
			{
				Future<GadgetAvailableEventData> f_gadget;
				f_gadget=mb.sendEvent(new GadgetAvailableEvent(answer.getGadget()));
				data.setGadgetAvlabla(f_gadget.get().getGadgetAvlabla());
				data.setqTime(f_gadget.get().getqTime());
				if(data.getGadgetAvlabla()==false) {
					relAgents(answer.getAgentNumber());
					return;   //if gadget is not available release agents.
				}
			}
			else
			{
				relAgents(answer.getAgentNumber());
				return;
			}
			
			boolean isOk=((time-answer.getTime())>=answer.getTakeTime());//TODO are you sure we need to check this?
			//TODO i think we only need to check if the time iss is >= naw
			if(isOk)  //if time is OK for the mission to be executed. 
			{
				Event<Boolean> e_send=new SendAgentsEvent(answer.getAgentNumber(), answer.getTime());
				mb.sendEvent(e_send);
			}
			else
				relAgents(answer.getAgentNumber());
			
			//adds report to Diary
			Diary d=Diary.getInstance();
			r = new Report(answer.getName(), this.id,  f_agents.get().getMoneyPennyId(), answer.getAgentNumber(),
					answer.getNames(),answer.getGadget(), data.getqTime(), answer.getTime() , this.time);
			d.addReport(r);
		});
		

	}
}
