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
import java.text.SimpleDateFormat;

public class Weather {

    private static String APIKEY = "cad7df9f68142b76a36246b3cc571f8a";

    //Gets temperature
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

            double TemperatureKelvin = (double) MainObj.get("temp");
            double TemperatureFarenhieght = (TemperatureKelvin - 273.15) * 9/5 + 32;

            return
                    Math.round(TemperatureFarenhieght) + "°F";
        } catch (Exception e){
            return "Failed To Get Weather!";
        }

    }

    //description of temperature
    public static  String Decription(String City){
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

            JSONArray WeatherArray = (JSONArray) jsonObject.get("weather");
            JSONObject Weather = (JSONObject) WeatherArray.get(0);
            String Description = (String) Weather.get("description");

            return
                    Description;
        } catch (Exception e){
            return "";
        }

    }

    //Gets city or states country
    public static  String Country(String City){
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
            JSONObject SecObj = (JSONObject) jsonObject.get("sys");

            String Country = (String) SecObj.get("country");

            return
                    Country;
        } catch (Exception e){
            return "Check Name";
        }

    }

    //Grabs the Condition Icon Code
    public static  String icon(String City){
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

            JSONArray WeatherArray = (JSONArray) jsonObject.get("weather");
            JSONObject Weather = (JSONObject) WeatherArray.get(0);

            String icon = (String) Weather.get("icon");

            return
                    icon;
        } catch (Exception e){
            return "error";
        }

    }

//Next 5 day forecast
    public static  String forecast(String City){
        try {
            URL url = new URL("https://api.openweathermap.org/data/2.5/forecast?q=" + City + "&appid=" + APIKEY);
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

            JSONArray forecastArray = (JSONArray) jsonObject.get("list");
            JSONObject day1 = (JSONObject) forecastArray.get(5);
            JSONObject day2 = (JSONObject) forecastArray.get(13);
            JSONObject day3 = (JSONObject) forecastArray.get(21);
            JSONObject day4 = (JSONObject) forecastArray.get(29);
            JSONObject day5 = (JSONObject) forecastArray.get(37);

            JSONObject main1 = (JSONObject) day1.get("main");
            double kelvinone = (double) main1.get("temp");
            double Farenheight1 = Math.round((kelvinone - 273.15) * 9/5 + 32);

            JSONObject main2 = (JSONObject) day2.get("main");
            double kelvintwo = (double) main2.get("temp");
            double Farenheight2 = Math.round((kelvintwo - 273.15) * 9/5 + 32);

            JSONObject main3 = (JSONObject) day3.get("main");
            double kelvinthree = (double) main3.get("temp");
            double Farenheight3 = Math.round((kelvinthree - 273.15) * 9/5 + 32);

            JSONObject main4 = (JSONObject) day4.get("main");
            double kelvinfour = (double) main4.get("temp");
            double Farenheight4 = Math.round((kelvinfour - 273.15) * 9/5 + 32);

            JSONObject main5 = (JSONObject) day5.get("main");
            double kelvinfive = (double) main5.get("temp");
            double Farenheight5 = Math.round((kelvinfive - 273.15) * 9/5 + 32);

            String date1 = (String) day1.get("dt_txt");
            String date2 = (String) day2.get("dt_txt");
            String date3 = (String) day3.get("dt_txt");
            String date4 = (String) day4.get("dt_txt");
            String date5 = (String) day5.get("dt_txt");

            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat outputFormat = new SimpleDateFormat("EEE");

            String nextday = outputFormat.format(inputFormat.parse(date1));
            String nextday2 = outputFormat.format(inputFormat.parse(date2));
            String nextday3 = outputFormat.format(inputFormat.parse(date3));
            String nextday4 = outputFormat.format(inputFormat.parse(date4));
            String nextday5 = outputFormat.format(inputFormat.parse(date5));


            return
                    nextday + ": " + Farenheight1 + "°F | "+ nextday2 + ": " + Farenheight2 + "°F | " + nextday3 + ": " + Farenheight3 + "°F | "
                            + nextday4 + ": " + Farenheight4 + "°F | "+ nextday5 + ": " + Farenheight5 + "°F";
        } catch (Exception e){
            return "Failed To Get Weather!";
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
        description.setBounds(0,400,500,100);
        description.setFont(new Font("ariel",Font.BOLD,40));
        WeatherPanel.add(description);

        JLabel city = new JLabel("",SwingConstants.CENTER);
        city.setBounds(100,120,300,100);
        city.setFont(new Font("ariel",Font.BOLD,40));
        WeatherPanel.add(city);

        JLabel country = new JLabel("",SwingConstants.CENTER);
        country.setBounds(0,250,500,100);
        country.setFont(new Font("ariel",Font.BOLD,40));
        WeatherPanel.add(country);

        JLabel weatherLogo = new JLabel(new ImageIcon(""));
        weatherLogo.setBounds(150,320,200,100);
        WeatherPanel.add(weatherLogo);

        JLabel forecast = new JLabel("",SwingConstants.CENTER);
        forecast.setBounds(0,680,500,100);
        forecast.setFont(new Font("Ariel",Font.BOLD,15));
        WeatherPanel.add(forecast);

        JLabel tag = new JLabel(new ImageIcon("src/Icons/Awesome-Inc-Logo.png"));
        tag.setBounds(50,350,400,500);
        WeatherPanel.add(tag);

        JLabel FIO = new JLabel("FIO TASK",SwingConstants.CENTER);
        FIO.setBounds(50,200,400,100);
        FIO.setFont(new Font("Ariel",Font.BOLD,50));
        WeatherPanel.add(FIO);

        JLabel start = new JLabel("Enter City/State to Start",SwingConstants.CENTER);
        start.setBounds(0,250,500,100);
        start.setFont(new Font("Ariel",Font.BOLD,35));
        WeatherPanel.add(start);

        JLabel Thisweek = new JLabel("This Week",SwingConstants.CENTER);
        Thisweek.setBounds(150,625,200,100);
        Thisweek.setFont(new Font("Ariel",Font.BOLD,35));
        WeatherPanel.add(Thisweek);
        Thisweek.setVisible(false);



        SearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String City = SearchBar.getText();
                String Weather = Temperature(City);
                String Description = Decription(City);
                String Country = Country(City);
                String Icon = icon(City);
                String Forecast = forecast(City);
                Degrees.setText(Weather);
                city.setText(City.toUpperCase());
                description.setText(Description.toUpperCase());
                country.setText("Country: " + Country.toUpperCase());
                ImageIcon icon = new ImageIcon("src/Icons/"+ Icon + ".png");
                forecast.setText(Forecast);
                weatherLogo.setIcon(icon);
                System.out.println(Forecast);
                tag.setVisible(false);
                WeatherPanel.remove(FIO);
                WeatherPanel.remove(start);
                Thisweek.setVisible(true);
                if(City != null){
                    SearchBar.setText("");
                }
            }
        });


        Weather.setVisible(true);
    }
}
