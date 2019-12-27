package bgu.spl.mics.application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import bgu.spl.mics.application.passiveObjects.Agent;
import bgu.spl.mics.application.passiveObjects.MissionInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

/**
 *Json_parser is class that parses Json files.
 */
public class Json_parser {

    /**
     *
     */
    private class Gintelligence{
        public MissionInfo[] missions;
    }

    /**
     *
     */
    private class Services {
        public int Moneypenny;
        public int M;
        public int time;
        public Gintelligence[] intelligence;
    }

    /**
     *
     */
    private class Gread {
        public String[] inventory;
        public Agent[] squad;
        public Services services;
    }

    private int m_num;
    private int moneypenny_num;
    private String[] inventory;
    private Agent[] squad;
    private Gintelligence[] intel;
    private int terminationTime;

    /**
     *
     * @param path is the path of Json file that needs to be parsed
     * this builder parser the file using the private class
     * (@link Gread)
     *
     */
    public Json_parser(String path) {
        BufferedReader buff_reader = null;
        try {
            buff_reader = new BufferedReader(new FileReader(path));
            //Gson gson = new GsonBuilder().create();
            Gson gson = new Gson();
            Gread gsonparser;
            gsonparser = gson.fromJson(buff_reader,Gread.class);
            squad = gsonparser.squad;
            for(Agent a:squad)
            {
                a.release();
            }
            inventory = gsonparser.inventory;
            m_num = gsonparser.services.M;
            moneypenny_num = gsonparser.services.Moneypenny;
            terminationTime = gsonparser.services.time;
            intel = gsonparser.services.intelligence;
        } catch (IOException e) {
        }

    }

    public int getMoneypenny_num() {
        return moneypenny_num;
    }

    public int getM_num() {
        return m_num;
    }

    public String[] getInventory() {
        return inventory;
    }

    public Agent[] getSquad() {
        return squad;
    }

    /**
     * @return
     */
    public MissionInfo[][] getIntells() {
        MissionInfo[][] mission = new MissionInfo[intel.length][];
        for (int i = 0; i < intel.length; i++)
            mission[i] = intel[i].missions;
        return mission;
    }

    public int getTerminationTime() {
        return terminationTime;
    }

}
