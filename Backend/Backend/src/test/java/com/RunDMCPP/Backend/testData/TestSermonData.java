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

    public static List<Sermon> smallListOfSermons(){
        List<Sermon> sermons = new ArrayList<>();
        sermons.add(sermon1());
        sermons.add(sermon2());
        return sermons;
    }


    public static Sermon sermon3(){
        Sermon s = new Sermon();
        s.setName("Sermon 3");
        s.setDescription("Test data for sermon 3");
        s.setYoutubeLink("https://www.youtube.com/watch?v=xc_0wfIuuzw");
        s.setDateTime("2024-02-03T20:18");
        s.setId("3");
        return s;
    }

    public static Sermon sermon4(){
        Sermon s = new Sermon();
        s.setName("Sermon 4");
        s.setDescription("Test data for sermon 4");
        s.setYoutubeLink("https://www.youtube.com/watch?v=xc_0wfIuuzw");
        s.setDateTime("2024-02-04T20:18");
        s.setId("4");
        return s;
    }
    public static Sermon sermon5(){
        Sermon s = new Sermon();
        s.setName("Sermon 5");
        s.setDescription("Test data for sermon 5");
        s.setYoutubeLink("https://www.youtube.com/watch?v=xc_0wfIuuzw");
        s.setDateTime("2024-02-05T20:18");
        s.setId("5");
        return s;
    }
    public static Sermon sermon6(){
        Sermon s = new Sermon();
        s.setName("Sermon 6");
        s.setDescription("Test data for sermon 6");
        s.setYoutubeLink("https://www.youtube.com/watch?v=xc_0wfIuuzw");
        s.setDateTime("2024-02-06T20:18");
        s.setId("6");
        return s;
    }
    public static Sermon sermon7(){
        Sermon s = new Sermon();
        s.setName("Sermon 7");
        s.setDescription("Test data for sermon 7");
        s.setYoutubeLink("https://www.youtube.com/watch?v=xc_0wfIuuzw");
        s.setDateTime("2024-02-07T20:18");
        s.setId("7");
        return s;
    }
    public static Sermon sermon8(){
        Sermon s = new Sermon();
        s.setName("Sermon 8");
        s.setDescription("Test data for sermon 8");
        s.setYoutubeLink("https://www.youtube.com/watch?v=xc_0wfIuuzw");
        s.setDateTime("2024-02-08T20:18");
        s.setId("8");
        return s;
    }
    public static Sermon sermon9(){
        Sermon s = new Sermon();
        s.setName("Sermon 9");
        s.setDescription("Test data for sermon 9");
        s.setYoutubeLink("https://www.youtube.com/watch?v=xc_0wfIuuzw");
        s.setDateTime("2024-02-09T20:18");
        s.setId("9");
        return s;
    }
    public static Sermon sermon10(){
        Sermon s = new Sermon();
        s.setName("Sermon 10");
        s.setDescription("Test data for sermon 10");
        s.setYoutubeLink("https://www.youtube.com/watch?v=xc_0wfIuuzw");
        s.setDateTime("2024-02-10T20:18");
        s.setId("10");
        return s;
    }
    public static Sermon sermon11(){
        Sermon s = new Sermon();
        s.setName("Sermon 11");
        s.setDescription("Test data for sermon 11");
        s.setYoutubeLink("https://www.youtube.com/watch?v=xc_0wfIuuzw");
        s.setDateTime("2024-02-11T20:18");
        s.setId("11");
        return s;
    }

    public static Sermon sermon3_UnparseableDate(){
        Sermon s = new Sermon();
        s.setName("Sermon 3");
        s.setDescription("Test data for sermon 3, but with an unparseable date");
        s.setYoutubeLink("https://www.youtube.com/watch?v=xc_0wfIuuzw");
        s.setDateTime("2024-02-02 20:18");
        s.setId("3");
        return s;
    }
    public static List<Sermon> largeListOfSermons(){
        List<Sermon> sermons = new ArrayList<>();
        sermons.add(sermon1());
        sermons.add(sermon2());
        sermons.add(sermon3());
        sermons.add(sermon4());
        sermons.add(sermon5());
        sermons.add(sermon6());
        sermons.add(sermon7());
        sermons.add(sermon8());
        sermons.add(sermon9());
        sermons.add(sermon10());

        return sermons;
    }

    public static List<Sermon> middleListOfSermons(){
        List<Sermon> sermons = new ArrayList<>();
        sermons.add(sermon1());
        sermons.add(sermon2());
        sermons.add(sermon3());
        sermons.add(sermon4());
        sermons.add(sermon5());

        return sermons;
    }

    public static List<Sermon> largerListOfDisorderdSermons(){
        List<Sermon> sermons = new ArrayList<>();
        sermons.add(sermon1());
        sermons.add(sermon3());
        sermons.add(sermon5());
        sermons.add(sermon7());
        sermons.add(sermon9());
        sermons.add(sermon3_UnparseableDate());
        sermons.add(sermon11());
        sermons.add(sermon10());
        sermons.add(sermon1_2Data());
        sermons.add(sermon8());
        sermons.add(sermon6());
        sermons.add(sermon4());
        sermons.add(sermon2());

        return sermons;
    }

    public static List<Sermon> singleItemList(){
        List<Sermon> sermons = new ArrayList<>();
        sermons.add(sermon1());
        return sermons;
    }

    public static List<Sermon> singleItemList_Unparseable(){
        List<Sermon> sermons = new ArrayList<>();
        sermons.add(sermon3_UnparseableDate());
        return sermons;
    }
}
