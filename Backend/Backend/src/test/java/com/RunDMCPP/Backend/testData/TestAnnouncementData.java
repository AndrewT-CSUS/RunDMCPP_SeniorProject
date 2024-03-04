package com.RunDMCPP.Backend.testData;

import com.RunDMCPP.Backend.models.Announcement;

import java.util.ArrayList;
import java.util.List;

public class TestAnnouncementData {

    public static Announcement announcement1(){
        Announcement a = new Announcement();
        a.setTitle("Announcement 1");
        a.setId("1");
        a.setDescription("Test data for announcement 1");
        a.setTtl(100L);

        return a;
    }

    public static Announcement announcement2(){
        Announcement a = new Announcement();
        a.setTitle("Announcement 2");
        a.setId("2");
        a.setDescription("Test data for announcement 2");
        a.setTtl(200L);

        return a;
    }

    public static Announcement announcement1_2Data(){
        Announcement a = new Announcement();
        a.setTitle("Announcement 2");
        a.setId("1");
        a.setDescription("Test data for announcement 2");
        a.setTtl(200L);

        return a;
    }

    public static Announcement announcement3(){
        Announcement a = new Announcement();
        a.setTitle("Announcement 3");
        a.setId("3");
        a.setDescription("Test data for announcement 3");
        a.setTtl(300L);

        return a;
    }

    public static Announcement announcement4(){
        Announcement a = new Announcement();
        a.setTitle("Announcement 4");
        a.setId("4");
        a.setDescription("Test data for announcement 4");
        a.setTtl(400L);

        return a;
    }
    public static Announcement announcement5(){
        Announcement a = new Announcement();
        a.setTitle("Announcement 5");
        a.setId("5");
        a.setDescription("Test data for announcement 5");
        a.setTtl(500L);

        return a;
    }

    public static List<Announcement> listOfAnnouncements(){
        List<Announcement> announcements = new ArrayList<>();
        announcements.add(announcement1());
        announcements.add(announcement2());
        announcements.add(announcement3());
        return announcements;
    }

    public static List<Announcement> longListOfAnnouncements(){
        List<Announcement> announcements = new ArrayList<>();
        announcements.add(announcement5());
        announcements.add(announcement4());
        announcements.add(announcement3());
        announcements.add(announcement2());
        announcements.add(announcement1());
        return announcements;
    }

    public static List<Announcement> listOfOneAnnouncement(){
        List<Announcement> announcements = new ArrayList<>();
        announcements.add(announcement1());
        return announcements;
    }
}
