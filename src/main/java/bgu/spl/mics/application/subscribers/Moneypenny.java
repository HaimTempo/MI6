package bgu.spl.mics.application.subscribers;

import java.util.List;
import bgu.spl.mics.Callback;
import bgu.spl.mics.application.messages.AgentAvailbleData;
import bgu.spl.mics.application.messages.AgentsAvailableEvent;
import bgu.spl.mics.Subscriber;
import bgu.spl.mics.application.messages.ReleaseAgentsEvent;
import bgu.spl.mics.application.passiveObjects.Agent;
import bgu.spl.mics.application.passiveObjects.Squad;
import bgu.spl.mics.application.messages.SendAgentsEvent;

/**
 * Only this type of Subscriber can access the squad.
 * Three are several Moneypenny-instances - each of them holds a unique serial number that will later be printed on the report.
 *
 * You can add private fields and public methods to this class.
 * You MAY change constructor signatures and even add new public constructors.
 */
public class Moneypenny extends Subscriber {
	private int id;
	private Squad squad;

	public Moneypenny(Integer id) {
		super("MP "+id);
		this.id=id;
		squad=Squad.getInstance();
	}
	
	public void load(Agent[] agents) {
		squad.load(agents);
	}//1 MP
	
	private boolean getAgents(List<String> serials) {
		return squad.getAgents(serials);
	}//1 MP
	
	private void relAgents(List<String> serials) {
		squad.releaseAgents(serials);
	}//2 MP
	
	private void sendAgents(List<String> serials, int time) {
		squad.sendAgents(serials, time);
	}//2 MP


	/**
	 * this subscribers to all the relevent broudcasts
	 */
	@Override
	protected void initialize() {
		Thread.currentThread().setName(this.getName());//for debug

		if (id%2==0) {
			subscribeEvent(AgentsAvailableEvent.class,message -> {//message type is AgentsAvailableEvent
				//we 90% need a try catch
				boolean agentsAvailble=getAgents(message.getAgents());//TODO are the agents available
				List<String> agentsSerials = message.getAgents();//todo i dont know if message.getAgents() is the serial numbers or the names
				AgentAvailbleData futureAnswer=new AgentAvailbleData(agentsAvailble, agentsSerials,this.id);
				this.complete(message,futureAnswer);//this is a subscriber
			});


		}
		else
		{
			subscribeEvent(ReleaseAgentsEvent.class, message ->
			{
				relAgents(message.getAgentsId());
				complete(message,true);//release agents doesn't needs an answer??
			});
		}

		subscribeEvent(SendAgentsEvent.class, new Callback<SendAgentsEvent>() {
			@Override
			public void call(SendAgentsEvent c) {
				try 
				{
					sendAgents(c.getAgents(), c.getTime());
					complete(c, true);//we are always suppost to be able to send the agents
				}
				catch (Exception ex)
				{
					System.out.println("opssss i am not suppost to get here");
				}
			}
		});

	}
}