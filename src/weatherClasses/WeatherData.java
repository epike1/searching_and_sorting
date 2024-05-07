package weatherClasses;

public class WeatherData {

    private String day;
    private String weatherType;
    private double rainChance;
    private int temperature;

    public WeatherData(String day, String weatherType, double rainChance, int temperature) {

        setDay(day);
        setWeatherType(weatherType);
        setRainChance(rainChance);
        setTemperature(temperature);
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDay() {
        return day;
    }

    public void setWeatherType(String weatherType) {
        this.weatherType = weatherType;
    }

    public String getWeatherType() {
        return weatherType;
    }

    public void setRainChance(double rainChance) {
        this.rainChance = rainChance;
    }

    public double getRainChance() {
        return rainChance;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getTemperature() {
        return temperature;
    }

    public String toString() {
        return String.format("%s: %s, Chance of Rain: %d%%, Temperature: %d degrees C", getDay(), getWeatherType(), (int)(getRainChance() * 100), getTemperature());
    }

}
