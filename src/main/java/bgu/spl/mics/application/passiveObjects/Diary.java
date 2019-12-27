package bgu.spl.mics.application.passiveObjects;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import com.google.gson.Gson;

/**
 * Passive object representing the diary where all reports are stored.
 * <p>
 * This class must be implemented safely as a thread-safe singleton.
 * You must not alter any of the given public methods of this class.
 * <p>
 * You can add ONLY private fields and methods to this class as you see fit.
 */
public class Diary {//m
	
	/**
	 * Retrieves the single instance of this class.
	 */
	static Diary instance=new Diary();
	private List<Report> reports;
	private int total;
	
	private Diary() {
		reports = new LinkedList<>();
		total=0;
	}
	
	public static Diary getInstance() {
		return instance;
	}

	public List<Report> getReports() {
		return reports;
	}

	/**
	 * adds a report to the diary
	 * @param reportToAdd - the report to add
	 */
	public void addReport(Report reportToAdd){
		reports.add(reportToAdd);
		total++;
	}

	/**
	 *
	 * <p>
	 * Prints to a file name @filename a serialized object List<Report> which is a
	 * List of all the reports in the diary.
	 * This method is called by the main method in order to generate the output.
	 */
	public void printToFile(String filename){
		Gson gson = new Gson();
		try 
		{
			String json = gson.toJson(reports);
			FileWriter f = new FileWriter(filename);
			f.write(json);
			f.close();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	/**
	 * Gets the total number of received missions (executed / aborted) be all the M-instances.
	 * @return the total number of received missions (executed / aborted) be all the M-instances.
	 */
	public int getTotal(){
		return total;
	}
}
