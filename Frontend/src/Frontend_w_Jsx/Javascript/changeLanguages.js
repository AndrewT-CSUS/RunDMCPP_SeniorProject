function toggleText(event) {
    var text = event.textContent || event.innerText;
    var headerLang = {
        kor: {
            //small groups
            //q & a
            //prayer requests
            sgkc: "새크라멘토 영광교회",
            announcements: "발표",
            aboutUs: "교회소개",
            ourBeliefs: "사역",
            services: "예배시간",
            events: "교회행사",
            photos: "갤러리",
            sermons: "설교",
            upcomingSermons: "다음주 설교",
            previousSermons: "지난주 설교",
            home: "홈",
            serviceSchedule: "교회일정",
            serviceScheduleDay: "일",
            serviceScheduleTime: "시간", 
            serviceScheduleSunday: "일요일",
            serviceScheduleWednesday: "목요일"
        }, 
        eng: {
            sgkc: "Sacramento Glory Korean Church",
            announcements: "Announcements",
            aboutUs: "About Us",
            ourBeliefs: "Our Beliefs",
            services: "Services",
            events: "Events",
            photos: "Photo Gallery",
            sermons: "Sermons",
            upcomingSermons: "Upcoming Sermons",
            previousSermons: "Past Sermons",
            home: "Home",
            serviceSchedule: "Church Service Schedule",
            serviceScheduleDay: "Days",
            serviceScheduleTime: "Time", 
            serviceScheduleSunday: "Sunday",
            serviceScheduleWednesday: "Wednesday"
        }
    };
    if (text == '한국어') {
        event.innerHTML = 'English';
        sgkc.textContent = headerLang.kor.sgkc;
        announcements.textContent = headerLang.kor.announcements;
        aboutUs.textContent = headerLang.kor.aboutUs;
        ourBeliefs.textContent = headerLang.kor.ourBeliefs;
        services.textContent = headerLang.kor.services;
        eventsDrop.textContent = headerLang.kor.events;
        eventsPage.textContent = headerLang.kor.events;
        photoGallery.textContent = headerLang.kor.photos;
        sermons.textContent = headerLang.kor.sermons;
        upcomingSermons.textContent = headerLang.kor.upcomingSermons;
        previousSermons.textContent = headerLang.kor.previousSermons;
        home.textContent = headerLang.kor.home;
        serviceSchedule.textContent = headerLang.kor.serviceSchedule;
        serviceScheduleDay.textContent = headerLang.kor.serviceScheduleDay;
        serviceScheduleTime.textContent = headerLang.kor.serviceScheduleTime;
        serviceScheduleSunday.textContent = headerLang.kor.serviceScheduleSunday;
        serviceScheduleWednesday.textContent = headerLang.kor.serviceScheduleWednesday;

    } else {
        event.innerHTML = '한국어';
        sgkc.textContent = headerLang.eng.sgkc;
        announcements.textContent = headerLang.eng.announcements;
        aboutUs.textContent = headerLang.eng.aboutUs;
        ourBeliefs.textContent = headerLang.eng.ourBeliefs;
        services.textContent = headerLang.eng.services;
        eventsDrop.textContent = headerLang.eng.events;
        eventsPage.textContent = headerLang.eng.events;
        photoGallery.textContent = headerLang.eng.photos;
        sermons.textContent = headerLang.eng.sermons;
        upcomingSermons.textContent = headerLang.eng.upcomingSermons;
        previousSermons.textContent = headerLang.eng.previousSermons;
        home.textContent = headerLang.eng.home;
        serviceSchedule.textContent = headerLang.eng.serviceSchedule;
        serviceScheduleDay.textContent = headerLang.eng.serviceScheduleDay;
        serviceScheduleTime.textContent = headerLang.eng.serviceScheduleTime;
        serviceScheduleSunday.textContent = headerLang.eng.serviceScheduleSunday;
        serviceScheduleWednesday.textContent = headerLang.eng.serviceScheduleWednesday;
    }
}