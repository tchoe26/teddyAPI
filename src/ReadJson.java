import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


// video to load jar
//https://www.youtube.com/watch?v=QAJ09o3Xl_0

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

// Program for print data in JSON format.
public class ReadJson {


    public ReadJson() throws ParseException {
        String output = "abc";
        String totlaJson="";
        try {

            URL url = new URL(SwingControlDemo.getLinkInput());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {

                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));


            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
               // System.out.println(output);
                totlaJson+=output;
            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONParser parser = new JSONParser();
        //System.out.println(str);
        org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) parser.parse(totlaJson);
        //System.out.println(jsonObject);

        try {
            //basic info
            SwingControlDemo.writeToOutput("name: "+ jsonObject.get("name"));
            SwingControlDemo.writeToOutput("height: "+ jsonObject.get("height"));
            SwingControlDemo.writeToOutput("weight: "+ jsonObject.get("weight"));

            //ability
            org.json.simple.JSONArray abilities = (org.json.simple.JSONArray) jsonObject.get("abilities");
            int n =   abilities.size(); //(msg).length();
            String[] abilityName = new String[n];
            for (int i = 0; i < n; ++i) {
                JSONObject abilityObject = (JSONObject)abilities.get(i);
                JSONObject ability = (JSONObject)abilityObject.get("ability");
                abilityName[i] = (String)ability.get("name");

            }
            SwingControlDemo.writeToOutput("abilities: " + String.join(", ", abilityName));

            //held items
            org.json.simple.JSONArray heldItems = (org.json.simple.JSONArray) jsonObject.get("held_items");
            int heldItemsSize =   heldItems.size(); //(msg).length();
            String[] itemName = new String[heldItemsSize];
            for (int i = 0; i < heldItemsSize; ++i) {
                JSONObject heldItemsObject = (JSONObject)heldItems.get(i);
                JSONObject item = (JSONObject)heldItemsObject.get("item");
                itemName[i] = (String)item.get("name");
            }
            SwingControlDemo.writeToOutput("held items: " + String.join(", ", itemName));

            //moves
            org.json.simple.JSONArray movesArray = (org.json.simple.JSONArray) jsonObject.get("moves");
            int movesSize = movesArray.size(); //(msg).length();
            String[] movesName = new String[movesSize];
            for (int i = 0; i < movesSize; ++i) {
                JSONObject movesObject = (JSONObject)movesArray.get(i);
                JSONObject moves = (JSONObject)movesObject.get("move");
                movesName[i] = (String)moves.get("name");
            }
            SwingControlDemo.writeToOutput("moves: " + String.join(", ", movesName));

            //stats
            org.json.simple.JSONArray statsArray = (org.json.simple.JSONArray) jsonObject.get("stats");
            int statsSize = statsArray.size();
            String[] statsName = new String [statsSize];
            int[] statsValue = new int[statsSize];


            String comma;
            SwingControlDemo.writeToOutput("stats:");
            for (int i=0; i<statsSize; i++) {

                JSONObject statsObject = (JSONObject)statsArray.get(i);
                statsValue[i] = ((Long)statsObject.get("base_stat")).intValue();
                JSONObject statsObject2 = (JSONObject)statsObject.get("stat");
                statsName[i] = (String)statsObject2.get("name");
               /* if (i==statsSize-1) {
                    comma = "";
                } else {
                    comma = ",";
                }
                SwingControlDemo.writeToOutput(statsName[i] + "(" + statsValue[i] + ")" + comma + " ");*/
                SwingControlDemo.writeToOutput(String.join(", ", statsName[i]+"("+statsValue[i]+")"));
            }


            //String height = (String)jsonObject.get("height");



            //System.out.println(jsonObject.get("abilities.1.ability.name"));
        }

        catch (Exception e) {
            e.printStackTrace();
        }

    }


}