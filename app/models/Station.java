package models;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.Logger;
import play.db.jpa.Model;

@Entity
public class Station extends Model
{
    public String title;
    public double latitude;
    public double longitude;

    public Station (String title, double latitude, double longitude)
    {
        this.title = title;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @OneToMany(cascade = CascadeType.ALL)
    public List<Reading> readings = new ArrayList<Reading>();


    public List<Reading> getReadings()
    {
        return readings;
    }

    private Reading latestReading() {
        if (readings.size() > 0) {
            return readings.get(readings.size() - 1);
        } else {
            return new Reading(0,0,0,0,0,"none");
        }
    }
    private Reading secondlatestReading() {
        if (readings.size() > 2) {
            return readings.get(readings.size() - 2);
        } else {
            return new Reading(0,0,0,0,0,"none");
        }
    }
    private Reading thirdlatestReading() {
        if (readings.size() > 2) {
            return readings.get(readings.size() - 3);
        } else {
            return new Reading(0,0,0,0,0,"none");
        }
    }
public String tempTrend() {
    if (readings.size() > 2) {
        if (latestReading().getReadingTemperature() > secondlatestReading().getReadingTemperature()) {
            if (secondlatestReading().getReadingTemperature() > thirdlatestReading().getReadingTemperature()) {
                return "Rising";
            }
        } else if (latestReading().getReadingTemperature() < secondlatestReading().getReadingTemperature()) {
            if (secondlatestReading().getReadingTemperature() < thirdlatestReading().getReadingTemperature()) {
                return "Falling";
            }
        }
    } else if (readings.size() <= 2) {
            return " ";
    }
    return "Steady";
}
    public String windTrend() {
        if (readings.size() > 2) {
            if (latestReading().getReadingWindSpeed() > secondlatestReading().getReadingWindSpeed()) {
                if (secondlatestReading().getReadingWindSpeed() > thirdlatestReading().getReadingWindSpeed()) {
                    return "Rising";
                }
            } else if (latestReading().getReadingWindSpeed() < secondlatestReading().getReadingWindSpeed()) {
                if (secondlatestReading().getReadingWindSpeed() < thirdlatestReading().getReadingWindSpeed()) {
                    return "Falling";
                }
            }
        } else if (readings.size() <= 2) {
            return " ";
        }
        return "Steady";
    }
    public String pressureTrend() {
        if (readings.size() > 2) {
            if (latestReading().getReadingPressure() > secondlatestReading().getReadingPressure()) {
                if (secondlatestReading().getReadingPressure() > thirdlatestReading().getReadingPressure()) {
                    return "Rising";
                }
            } else if (latestReading().getReadingPressure() < secondlatestReading().getReadingPressure()) {
                if (secondlatestReading().getReadingPressure() < thirdlatestReading().getReadingPressure()) {
                    return "Falling";
                }
            }
        } else if (readings.size() <= 2) {
            return " ";
        }
        return "Steady";
    }
   public double minWindSpeed() {
       if (readings.size() != 0) {
           Reading minWindSpeed = readings.get(0);
           for (Reading reading : readings) {
               if (reading.getReadingWindSpeed() < minWindSpeed.getReadingWindSpeed())
                   minWindSpeed = reading;
           }
           return minWindSpeed.getReadingWindSpeed();
       } else
           return 0.0;
   }
    public double maxWindSpeed() {
        if (readings.size() != 0) {
            Reading maxWindSpeed = readings.get(0);
            for (Reading reading : readings) {
                if (reading.getReadingWindSpeed() > maxWindSpeed.getReadingWindSpeed())
                    maxWindSpeed = reading;
            }
            return maxWindSpeed.getReadingWindSpeed();
        } else
            return 0.0;
    }
    public double minPressure() {
        if (readings.size() != 0) {
            Reading minPressure = readings.get(0);
            for (Reading reading : readings) {
                if (reading.getReadingPressure() < minPressure.getReadingPressure())
                    minPressure = reading;
            }
            return minPressure.getReadingPressure();
        } else
            return 0.0;
    }
    public double maxPressure() {
        if (readings.size() != 0) {
            Reading maxPressure = readings.get(0);
            for (Reading reading : readings) {
                if (reading.getReadingPressure() > maxPressure.getReadingPressure())
                    maxPressure = reading;
            }
            return maxPressure.getReadingPressure();
        } else
            return 0.0;
    }
// user to get the title field of the Array List of stations to then
    // be passed into a method which sorted each station in that array list
    public static List<Station> sortStations(List<Station> station) {
        station.sort(Comparator.comparing(Station::getStationTitle));
        Logger.info("Sorting Station alphabetically");

        return station;
    }


    //getters

    public String getStationTitle() { return title; }
    public int latestCode()
    {
        return latestReading().getReadingCode();
    }

    public String latestWeatherCode()
    {
        return latestReading().getWeatherCode();
    }
    public double latestTemperature()
    {
        return latestReading().getReadingTemperature();
    }
    public double latestFahrenheit()
    {
        return latestReading().getReadingFahrenheit();
    }

    public double latestWindSpeed()
    {
        return latestReading().getReadingWindSpeed();
    }
    public int latestBeaufort()
    {
        return latestReading().getBeaufort();
    }
    public String latestWindDirectionCompass()
    {
        return latestReading().getWindDirectionCompass();
    }
    public double latestWindChill()
    {
        return latestReading().getWindChill();
    }
    public int latestPressure()
    {
        return latestReading().getReadingPressure();
    }

}