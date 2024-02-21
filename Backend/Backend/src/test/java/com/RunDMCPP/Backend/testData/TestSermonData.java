package com.RunDMCPP.Backend.testData;

import com.RunDMCPP.Backend.models.Sermon;

import java.util.ArrayList;
import java.util.List;

public class TestSermonData {

    public static Sermon sermon1(){
        Sermon s = new Sermon();
        s.setName("Sermon 1");
        s.setDescription("Test data for sermon 1");
        s.setYoutubeLink("https://www.youtube.com/watch?v=fmHOqoDeiDg");
        s.setDateTime("2024-01-01T20:18");
        s.setId("1");
        return s;
    }

    public static Sermon sermon2(){
        Sermon s = new Sermon();
        s.setName("Sermon 2");
        s.setDescription("Test data for sermon 2");
        s.setYoutubeLink("https://www.youtube.com/watch?v=xc_0wfIuuzw");
        s.setDateTime("2024-02-02T20:18");
        s.setId("2");
        return s;
    }

    public static Sermon sermon1_2Data(){
        Sermon s = new Sermon();
        s.setName("Sermon 2");
        s.setDescription("Test data for sermon 2");
        s.setYoutubeLink("https://www.youtube.com/watch?v=xc_0wfIuuzw");
        s.setDateTime("2024-02-02T20:18");
        s.setId("1");
        return s;
    }

    public static List<Sermon> listOfSermons(){
        List<Sermon> sermons = new ArrayList<>();
        sermons.add(sermon1());
        sermons.add(sermon2());
        return sermons;
    }
}
