package adventureApp;

import java.util.List;

public interface Filterable
{
    List<TrailData> filterByDifficulty(String difficulty);
    List<TrailData> filterByLength(double minLength, double maxLength);
    
 //Methods to update map images based on difficulty level
    void showBeginnerMap();
    void showIntermediateMap();
    void showExpertMap();
}