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

    public static List<Announcement> listOfAnnouncements(){
        List<Announcement> announcements = new ArrayList<>();
        announcements.add(announcement1());
        announcements.add(announcement2());
        return announcements;
    }
}
