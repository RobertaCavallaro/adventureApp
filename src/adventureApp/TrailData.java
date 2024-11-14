package adventureApp;

import org.json.JSONArray;

// Test comment
// 
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TrailData
{

    // instance variables
    private String trailName;
    private double length;
    private int elevation;
    private String difficulty;
    private String imagePath; 


    // method for load data from API
    public void loadData()
    {
        try
        {
            String apiUrl = "https://developer.nps.gov/api/v1/parks?stateCode=CA&api_key=mDanSXhlTvpLox44T7m3C0ABi3XDfwBI0oJcE7Dl";
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) 
            {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null)
                {
                    content.append(inputLine);
                }

                in.close();
                conn.disconnect();

                parseData(content.toString());
            } 
            else
            {
                System.out.println("Failure: " + responseCode);
            }
        } 
        catch (Exception e)
        {
            System.out.println("Error fetching data: " + e.getMessage());
        }
    }

    // Method to get list of trails
    public List<TrailData> getTrailData()
    {
        List<TrailData> trails = new ArrayList<>();
        return trails;
    }

    // Method to search a trail by name
    public TrailData searchByName(String name)
    {
        return null;
    }

    // Method to update trail information
    public void updateTrail(TrailData trail) 
    {
    }
    
    public String getImagePath()
    {
        return imagePath;
    }

    // Method to parse JSON data
    private void parseData(String jsonData)
    {
        JSONObject jsonResponse = new JSONObject(jsonData);
        JSONArray dataArray = jsonResponse.getJSONArray("data");

        for (int i = 0; i < dataArray.length(); i++) 
        {
            JSONObject park = dataArray.getJSONObject(i);
            String fullName = park.getString("fullName");
            String description = park.getString("description");

            // Process park data and populate Trail attributes
            System.out.println("Park Name: " + fullName);
            System.out.println("Description: " + description);
        }
    }

	public String getDifficulty()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public String getTrailName()
	{
		return trailName;
	}

	public void setTrailName(String trailName)
	{
		this.trailName = trailName;
	}

	public Double getLength() 
	{
		return length;
	}

	public void setLength(Double length)
	{
		this.length = length;
	}

	public void setDifficulty(String difficulty)
	{
	}

	public Integer getElevation()
	{
		return elevation;
	}

	public void setElevation(Integer elevation) 
	{
		this.elevation = elevation;
	}
}
