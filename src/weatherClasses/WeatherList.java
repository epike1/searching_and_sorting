package weatherClasses;

import java.util.ArrayList;
public class WeatherList {
    private ArrayList<WeatherData> weatherDataList = new ArrayList<WeatherData>();

    public WeatherList() {

    }

    public ArrayList<WeatherData> getWeatherDataList() {
        return weatherDataList;
    }

    public void addWeatherData(WeatherData weatherData) {
        weatherDataList.add(weatherData);
    }

    public WeatherData getWeatherData(int index) {
        return weatherDataList.get(index);
    }

    public void sortByDay() {

    }

    public void sortByTemp() {

    }
    public void sortByRain() {

    }

    public void displayList() {

        System.out.println("Weather for the week:");
        for (WeatherData i : weatherDataList) {
            System.out.println(i);
        }
    }
}
