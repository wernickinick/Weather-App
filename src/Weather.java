import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Weather {

    private static String APIKEY = "cad7df9f68142b76a36246b3cc571f8a";

    public static  String Temperature(String City){
        try {
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + City + "&appid=" + APIKEY);
            HttpURLConnection Connection = (HttpURLConnection) url.openConnection();
            Connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(Connection.getInputStream()));
            String Response = "";
            String Line;
            while((Line = reader.readLine()) != null){
                Response += Line;
            }
            reader.close();

            JSONObject jsonObject = (JSONObject) JSONValue.parse(Response);
            JSONObject MainObj = (JSONObject) jsonObject.get("main");
            JSONObject SecObj = (JSONObject) jsonObject.get("sys");

            double TemperatureKelvin = (double) MainObj.get("temp");
            long Humidity = (long) MainObj.get("humidity");
            double TemperatureFarenhieght = (TemperatureKelvin - 273.15) * 9/5 + 32;

            JSONArray WeatherArray = (JSONArray) jsonObject.get("weather");
            JSONObject Weather = (JSONObject) WeatherArray.get(0);
            String Description = (String) Weather.get("description");

            String Country = (String) SecObj.get("country");

            return
                    Math.round(TemperatureFarenhieght) + "°F";
        } catch (Exception e){
            return "Failed To Get Weather!";
        }

    }

    public static  String WeatherData(String City){
        try {
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + City + "&appid=" + APIKEY);
            HttpURLConnection Connection = (HttpURLConnection) url.openConnection();
            Connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(Connection.getInputStream()));
            String Response = "";
            String Line;
            while((Line = reader.readLine()) != null){
                Response += Line;
            }
            reader.close();

            JSONObject jsonObject = (JSONObject) JSONValue.parse(Response);
            JSONObject MainObj = (JSONObject) jsonObject.get("main");
            JSONObject SecObj = (JSONObject) jsonObject.get("sys");

            double TemperatureKelvin = (double) MainObj.get("temp");
            long Humidity = (long) MainObj.get("humidity");
            double TemperatureFarenhieght = (TemperatureKelvin - 273.15) * 9/5 + 32;

            JSONArray WeatherArray = (JSONArray) jsonObject.get("weather");
            JSONObject Weather = (JSONObject) WeatherArray.get(0);
            String Description = (String) Weather.get("description");

            String Country = (String) SecObj.get("country");

            return
                    Description;
        } catch (Exception e){
            return "";
        }

    }

    public static void main(String [ ] args){

        int Width = 500;
        int Height = 800;

        JFrame Weather = new JFrame("Weather");
        Weather.setBounds(0,0,Width,Height);
        Weather.setResizable(false);
        Weather.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Weather.setLayout(null);
        Weather.setLocationRelativeTo(null);

        JPanel WeatherPanel = new JPanel();
        WeatherPanel.setBounds(0,0,Width,Height);
        WeatherPanel.setBackground(Color.LIGHT_GRAY);
        WeatherPanel.setLayout(null);
        Weather.add(WeatherPanel);

        JLabel Heading = new JLabel("Enter City Name",SwingConstants.CENTER);
        Heading.setBounds(0,0,500,70);
        Heading.setFont(new Font("Ariel",Font.PLAIN,40));
        WeatherPanel.add(Heading);

        JTextField SearchBar = new JTextField("",SwingConstants.CENTER);
        SearchBar.setBounds(100,70,300,50);
        SearchBar.setFont(new Font("Ariel",Font.BOLD,30));
        WeatherPanel.add(SearchBar);

        JButton SearchButton = new JButton("🔍");
        SearchButton.setBounds(400,70,50,50);
        SearchButton.setFont(new Font("Ariel",Font.BOLD,30));
        WeatherPanel.add(SearchButton);

        JLabel Degrees = new JLabel("",SwingConstants.CENTER);
        Degrees.setBounds(0,200,500,100);
        Degrees.setFont(new Font("ariel",Font.BOLD,40));
        WeatherPanel.add(Degrees);

        JLabel description = new JLabel("",SwingConstants.CENTER);
        description.setBounds(0,300,500,100);
        description.setFont(new Font("ariel",Font.BOLD,40));
        WeatherPanel.add(description);

        JLabel city = new JLabel("",SwingConstants.CENTER);
        city.setBounds(100,120,300,100);
        city.setFont(new Font("ariel",Font.BOLD,40));
        WeatherPanel.add(city);

        SearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String City = SearchBar.getText();
                String Weather = Temperature(City);
                String Description = WeatherData(City);
                Degrees.setText(Weather);
                city.setText(City.toUpperCase());
                description.setText(Description.toUpperCase());
                if(City != null){
                    SearchBar.setText("");
                }
            }
        });


        Weather.setVisible(true);
    }
}
