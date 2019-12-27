package bgu.spl.mics.application.passiveObjects;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

/**
 *  That's where Q holds his gadget (e.g. an explosive pen was used in GoldenEye, a geiger counter in Dr. No, etc).
 * <p>
 * This class must be implemented safely as a thread-safe singleton.
 * You must not alter any of the given public methods of this class.
 * <p>
 * You can add ONLY private fields and methods to this class as you see fit.
 */
public class Inventory {//hm

	static private Inventory instance=new Inventory();
	private List<String> gadgets=new ArrayList<String>();
	
	/**
	 * Retrieves the single instance of this class.
	 */
	public static Inventory getInstance() {
		return instance;
	}

	/**
	 * Initializes the inventory. This method adds all the items given to the gadget
	 * inventory.
	 * <p>
	 * @param inventory 	Data structure containing all data necessary for initialization
	 * 						of the inventory.
	 */
	public void load (String[] inventory) {
		for(int g=0;g<inventory.length;g++) {
			this.gadgets.add(inventory[g]);
		}
	}

	/**
	 * acquires a gadget and returns 'true' if it exists.
	 * <p>
	 * @param gadget 		Name of the gadget to check if available
	 * @return 	‘false’ if the gadget is missing, and ‘true’ otherwise
	 */
	public boolean getItem(String gadget)
	{
		System.out.println(gadget+"--------------------!!!!-!@");
		return gadgets.remove(gadget);
	}

	/**
	 *
	 * <p>
	 * Prints to a file name @filename a serialized object List<String> which is a
	 * list of all the of the gadgeds.
	 * This method is called by the main method in order to generate the output.
	 */
	public void printToFile(String filename)
	{
		try 
		{ 
			Gson gson = new Gson();
			String json = gson.toJson(gadgets);
			FileWriter f = new FileWriter(filename);
			f.write(json);
			f.close();
		}
		catch(Exception e) {
			System.out.println("Error in creating file: " + e);
		}	
	}
	
}
