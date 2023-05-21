package utilities;

public class Conversions {

    public Conversions () {}

    public static String convertWeatherCode(int code){
        if (code <=100) {
            return "Clear";
        } else if (code<=200) {
            return "Partial clouds";
        }else if (code<=300) {
            return "Cloudy";
        }else if (code<=400) {
            return "Light Showers";
        }else if (code<=500) {
            return "Heavy Showers";
        }else if (code<=600) {
            return "Rain";
        }else if (code<=700) {
            return "Snow";
        }else if (code<=800) {
            return "Thunder";
        } else
        {
            return "No Code";
        }
    }
    public static double convertToFahrenheit(double temperature) {
        double fahrenheit = temperature * 9/5 +32;
        return fahrenheit;
    }

    public static int convertToBeaufort(double windSpeed) {
        if (windSpeed <= 1) {
            return 0;
        } else if (windSpeed <= 5) {
            return 1;
        } else if (windSpeed <= 11) {
            return 2;
        } else if (windSpeed <= 19) {
            return 3;
        } else if (windSpeed <= 28) {
            return 4;
        } else if (windSpeed <= 38) {
            return 5;
        } else if (windSpeed <= 49) {
            return 6;
        } else if (windSpeed <= 61) {
            return 7;
        } else if (windSpeed <= 74) {
            return 8;
        } else if (windSpeed <= 88) {
            return 9;
        } else if (windSpeed <= 102) {
            return 10;
        } else if (windSpeed <= 117) {
            return 11;
        } else {
            return 0;
        }
    }

    public static String windDirectionCompass (double windDirection)
    {
        if ((windDirection <= 11.25) || (windDirection > 348.75)) {
            return "N";
        } else if ((windDirection > 11.25) && (windDirection < 33.75)) {
            return "NNE";
        } else if ((windDirection > 33.75) && (windDirection < 56.25)) {
            return "NE";
        } else if ((windDirection > 56.25) && (windDirection < 78.75)) {
            return "ENE";
        } else if ((windDirection > 78.75) && (windDirection < 101.25)) {
            return "E";
        } else if ((windDirection > 101.25) && (windDirection < 123.75)) {
            return "ESE";
        } else if ((windDirection > 123.75) && (windDirection < 146.25)) {
            return "SE";
        } else if ((windDirection > 146.25) && (windDirection < 168.75)) {
            return "SSE";
        } else if ((windDirection > 168.75) && (windDirection < 191.25)) {
            return "S";
        } else if ((windDirection > 191.25) && (windDirection < 213.75)) {
            return "SSW";
        } else if ((windDirection > 213.75) && (windDirection < 236.25)) {
            return "SW";
        } else if ((windDirection > 236.25) && (windDirection < 258.75)) {
            return "WSW";
        } else if ((windDirection > 258.75) && (windDirection < 281.25)) {
            return "W";
        } else if ((windDirection > 281.25) && (windDirection < 303.75)) {
            return "WNW";
        } else if ((windDirection > 303.75) && (windDirection < 326.25)) {
            return "NW";
        } else if ((windDirection > 326.25) && (windDirection < 348.75)) {
            return "NNW";
        } else {
            return "Unknown";
        }
    }
    public static double convertToWindChill (double temperature, double windSpeed)
    {
        double windChill = 13.12 + 0.6215 * temperature - 11.37*Math.pow(windSpeed,0.16) + 0.3965*temperature*Math.pow(windSpeed,0.16);
        return toTwoDecimalPlaces(windChill);
    }
    private static double toTwoDecimalPlaces(double num){
        return (int) (num *100 ) /100.0;
    }
}
