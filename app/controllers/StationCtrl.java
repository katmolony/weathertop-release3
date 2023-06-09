package controllers;

import java.util.Comparator;
import java.util.List;

import models.Station;
import models.Reading;
import play.Logger;
import play.mvc.Controller;

public class StationCtrl extends Controller {
    public static void index(Long id) {
        Station station = Station.findById(id);
        Logger.info("Station id = " + id);
        render("station.html", station);
    }

    public static void addReading(Long id, int code, double temperature, double windSpeed, double windDirection, int pressure, String dateTime) {
        Reading reading = new Reading(code, temperature, windSpeed, windDirection, pressure, dateTime);
        Station station = Station.findById(id);
        station.readings.add(reading);
        station.save();
        redirect("/stations/" + id);
    }

    public static void deletereading(Long id, Long readingid) {
        Station station = Station.findById(id);
        Reading reading = Reading.findById(readingid);
        Logger.info("Removing " + reading.code);
        station.readings.remove(reading);
        station.save();
        reading.delete();
        render("station.html", station);
    }
}
