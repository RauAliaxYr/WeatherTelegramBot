import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class Weather {

    public static String getWeather(String message, Model model) throws IOException {

        URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" +message+"&units=metric&appid=3cc28e8a4febbbdb535d7232da1c9bb1");


        Scanner scanner = new Scanner((InputStream) url.getContent());
        String resulte = "";
        while (scanner.hasNext()){
            resulte += scanner.nextLine();
        }

        JSONObject object = new JSONObject(resulte);
        model.setName(object.getString("name"));


        JSONObject main = object.getJSONObject("main");
        model.setTemp(main.getDouble("temp"));

        JSONObject wind = object.getJSONObject("wind");
        model.setWindSpeed(wind.getDouble("speed"));



        JSONArray array = object.getJSONArray("weather");
        for (int i = 0; i < array.length();i++){
            JSONObject obj = array.getJSONObject(i);
            model.setPic((String) obj.get("icon"));
            model.setWeatherMain((String) obj.get("main"));
        }

        return "Город: " +model.getName()+ "\n"
                + "Температура: " + model.getTemp() + " C\n"
                + "Скорость ветра: " +model.getWindSpeed()+ " m/c\n"
                + model.getWeatherMain()+ "\n"
                + "http://openweathermap.org/img/wn/"+model.getPic()+"@2x.png" ;
    }
}
