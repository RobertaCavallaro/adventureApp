package adventureApp;

import java.util.Arrays;
import java.util.Comparator;

public abstract class TrailAnalyzer implements Filterable, Searchable
{

    private TrailData[] trails;

    // Constructor to initialize trails array
    public TrailAnalyzer(TrailData[] trails)
    {
        this.trails = trails;
    }

    // Method to sort trails by specified criteria
    public TrailData[] sortTrails(String criteria)
    {
        try 
        {
            if ("length".equalsIgnoreCase(criteria)) 
            {
                Arrays.sort(trails, Comparator.comparing(TrailData::getLength));
            } else if ("difficulty".equalsIgnoreCase(criteria)) {
                Arrays.sort(trails, Comparator.comparing(TrailData::getDifficulty));
            }
        }
        catch (Exception e) 
        {
            System.out.println("Error during sorting: " + e.getMessage());
        }
        return trails;
    }

    @Override
    public TrailData[] filterByDifficulty(String difficulty)
    {
        // Use stream to filter array
        return Arrays.stream(trails)
                .filter(trail -> trail.getDifficulty().equalsIgnoreCase(difficulty))
                .toArray(TrailData[]::new);
    }

    @Override
    public TrailData[] filterByLength(double minLength, double maxLength) 
    {
        // Use stream to filter array
        return Arrays.stream(trails)
                .filter(trail -> trail.getLength() >= minLength && trail.getLength() <= maxLength)
                .toArray(TrailData[]::new);
    }
}
