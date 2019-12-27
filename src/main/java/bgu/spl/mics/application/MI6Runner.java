package bgu.spl.mics.application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import bgu.spl.mics.Subscriber;
import bgu.spl.mics.application.passiveObjects.Diary;
import bgu.spl.mics.application.passiveObjects.Inventory;
import bgu.spl.mics.application.passiveObjects.MissionInfo;
import bgu.spl.mics.application.passiveObjects.Squad;
import bgu.spl.mics.application.publishers.TimeService;
import bgu.spl.mics.application.subscribers.Intelligence;
import bgu.spl.mics.application.subscribers.M;
import bgu.spl.mics.application.subscribers.Moneypenny;
import bgu.spl.mics.application.subscribers.Q;

/** This is the Main class of the application. You should parse the input file, 
 * create the different instances of the objects, and run the system.
 * In the end, you should output serialized objects.
 */
public class MI6Runner {
	static int thisTime; //TODO: update!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	public static void start_t(Thread[] t, Subscriber[] s, Class<? extends Subscriber> type) {
		for (int i=0;i<t.length;i++) 
		{
			try {
				s[i]=type.getConstructor(Integer.class).newInstance(i);
			} catch (Exception e) {
				e.printStackTrace();
			}
			t[i] = new Thread(s[i],s[i].getName());
			t[i].start();
		}
	}

	public static void generate_output(Inventory inveInstance, String gadget_output, String diary_output) {
		inveInstance.printToFile(gadget_output);
		Diary.getInstance().printToFile(diary_output);
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println("##Started##");
		Scanner myObj = new Scanner(System.in);
		//String[] input= myObj.nextLine().split(" ");
		String[] input = new String[3];
		input[0]="../input.json";
		input[1] = "o.json";
		input[2] = "o2.json";
		String gadget_output=input[1];
		String diary_output=input[2];
		Json_parser json=new Json_parser(input[0]);//j path
		myObj.close();

		int terminationTime=json.getTerminationTime();
		Squad.getInstance().load(json.getSquad());//load Squad
		Inventory.getInstance().load(json.getInventory());//load Inve

		MissionInfo[][] missions = json.getIntells();
		Thread[] intelThreads=new Thread[missions.length];
		Intelligence[] intelInstances=new Intelligence[missions.length];
		for (int i = 0; i < intelInstances.length; i++) {
			intelInstances[i]= new Intelligence(i+1, missions[i]);
			intelThreads[i] = new Thread(intelInstances[i],intelInstances[i].getName());
			intelThreads[i].start();
		}
		
		Thread[] mTh = new Thread[json.getM_num()];//load Ms
		M[] mInstances = new M[json.getM_num()];
		start_t(mTh, mInstances,M.class); 

		Thread[] mpTh=new Thread[json.getMoneypenny_num()];
		Moneypenny[] mpInstance = new Moneypenny[json.getMoneypenny_num()];
		start_t(mpTh, mpInstance,Moneypenny.class);


		TimeService tsInstance=new TimeService(json.getTerminationTime());
		Thread timeServiceTh = new Thread(tsInstance);
		timeServiceTh.start();

		Q qInstance=Q.getInstance();
		Thread qThread = new Thread(qInstance);
		qThread.start();

		System.out.println("all threads started ");

		timeServiceTh.join();
		System.out.println("Done timeServiceTh");
		qThread.join();
		System.out.println("Done qThread");
		joinAll(intelThreads);
		System.out.println("Done intelThreads");
		joinAll(mpTh);
		System.out.println("Done mpTh");
		System.out.println("Doneeee");

		generate_output(Inventory.getInstance(), gadget_output, diary_output);
	}
	//what happens if we terminate something that someone else is waiting for
	//Terminate gracefully !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

	private static void joinAll(Thread[] threads) {
		for (int i = 0; i < threads.length; i++) {
			try {
				threads[i].join();
				System.out.println("Done first thread "+i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
