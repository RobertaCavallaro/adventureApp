package adventureApp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class TrailAnalyzer implements Filterable, Searchable {

    private List<TrailData> trails;

    // Constructor to initialize trails list
    public TrailAnalyzer(List<TrailData> trails) {
        this.trails = trails;
    }

    // Method to sort trails by specified criteria
    public List<TrailData> sortTrails(String criteria) {
        if ("length".equalsIgnoreCase(criteria)) {
            trails.sort(Comparator.comparing(TrailData::getLength));
        } else if ("difficulty".equalsIgnoreCase(criteria)) {
            trails.sort(Comparator.comparing(TrailData::getDifficulty));
        }
        return trails;
    }

  
    @Override
    public List<TrailData> filterByDifficulty(String difficulty) {
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
        List<TrailData> filteredTrails = new ArrayList<>();
        for (TrailData trail : trails) {
            if (trail.getLength() >= minLength && trail.getLength() <= maxLength) {
                filteredTrails.add(trail);
            }
        }
        return filteredTrails;
    }
}
