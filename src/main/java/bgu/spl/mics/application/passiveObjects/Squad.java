package bgu.spl.mics.application.passiveObjects;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Passive data-object representing a information about an agent in MI6.
 * You must not alter any of the given public methods of this class. 
 * <p>
 * You may add ONLY private fields and methods to this class.
 */
public class Squad {//m
	//TODO: thread safe? we did Agent thread safe.
	private Map<String, Agent> agents;
	static private Squad instance=new Squad();

	private Squad() {
		agents = new ConcurrentHashMap<String, Agent>();
	}

	/**
	 * Retrieves the single instance of this class.
	 */
	public static Squad getInstance() {
		return instance;
	}

	/**
	 * Initializes the squad. This method adds all the agents to the squad.
	 * <p>
	 * @param agents 	Data structure containing all data necessary for initialization
	 * 						of the squad.
	 */
	public void load (Agent[] agents) {
		System.out.println(agents.length);
		for (Agent agent : agents) {
			this.agents.put(agent.getSerialNumber(), agent);
		}
	}

	/**
	 * Releases agents.
	 */
	public void releaseAgents(List<String> serials){
		synchronized (agents) {//TODO check this prev we did sync on all the function
			for (Agent agent : this.agents.values()) {
				agent.release();
			}
			notifyAll();
		}
	}

	/**
	 * simulates executing a mission by calling sleep.
	 * @param time   time ticks to sleep
	 */
	public void sendAgents(List<String> serials, int time){
		for (String s : serials) {
			Agent a=agents.get(s);
				a.acquire();
		}

		try {
			Thread.sleep((long)time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		for (String s : serials) {
			Agent a=agents.get(s);
				a.release();
		}
	}

	/**
	 * acquires an agent, i.e. holds the agent until the caller is done with it
	 * @param serials   the serial numbers of the agents
	 * @return ‘false’ if an agent of serialNumber ‘serial’ is missing, and ‘true’ otherwise
	 */
	public synchronized boolean getAgents(List<String> serials){
		boolean missing=false;
		for (String id: serials) 
		{
			if(this.agents.containsKey(id)) 
			{
				Agent agent = this.agents.get(id);
				while(!agent.isAvailable())
				{
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				agent.acquire();
			}
			else
				return true;
		}
		return missing;
	}

	/**
	 * gets the agents names
	 * @param serials the serial numbers of the agents
	 * @return a list of the names of the agents with the specified serials.
	 */
	public List<String> getAgentsNames(List<String> serials){
		List<String> agentsNames=new LinkedList<String>();
		for (String id : serials) {
			if(this.agents.containsKey(id))
			{
				agentsNames.add(this.agents.get(id).getName());
			}
		}
		return agentsNames;
	}
}
