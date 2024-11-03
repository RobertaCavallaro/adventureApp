package adventureApp;


import java.util.ArrayList;
import java.util.List;

public class TrailAnalyzer implements Filterable, Searchable
{

	
    //Array of trails 
    private List<TrailData> trails;

    // Constructor to initialize trails list
    public TrailAnalyzer(List<TrailData> trails)
    {
        this.trails = trails;
    }

    // Method to sort trails
    public List<TrailData> sortTrails(String criteria)
    {
        return trails;
    }

	@Override
	public List<TrailData> search(String query)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TrailData> filterByDifficulty(String difficulty) {
		// TODO Auto-generated method stub
		List<TrailData> filteredTrails = new ArrayList<>();
        for (TrailData trail : trails) {
            if (trail.getDifficulty().equalsIgnoreCase(difficulty)) {
                filteredTrails.add(trail);
            }
        }
        return filteredTrails;
        }

	@Override
	public List<TrailData> filterByLength(double minLength, double maxLength) {
		// TODO Auto-generated method stub
	    List<TrailData> filteredTrails = new ArrayList<>();
        for (TrailData trail : trails) {
            if (trail.getLength() >= minLength && trail.getLength() <= maxLength) {
                filteredTrails.add(trail);
            }
        }
        return filteredTrails;
    }
	

}

