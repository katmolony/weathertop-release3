package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Member;
import models.Station;
import models.Reading;
import play.Logger;
import play.mvc.Controller;

public class Dashboard extends Controller {
    public static void index() {
        Logger.info("Rendering Dashboard");
        Member member = Accounts.getLoggedInMember();
        List<Station> station = member.station;
        render("dashboard.html", member, station);
    }

    public static void addStation(String title, double latitude, double longitude) {
        Member member = Accounts.getLoggedInMember();
        Station station = new Station(title, latitude, longitude);
        member.station.add(station);
        member.save();
        Logger.info("Adding a new station called " + title);
        station.save();
        redirect("/dashboard");
    }

    public static void deleteStation(Long id, Long stationid) {
        Member member = Member.findById(id);
        Station station = Station.findById(stationid);
        member.station.remove(station);
        member.save();
        station.delete();
        Logger.info("Removing " + station.title);
        redirect("/dashboard");
    }
}
