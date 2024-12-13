package adventureApp;

public interface Filterable
{
    TrailData[] filterByDifficulty(String difficulty);
    TrailData[] filterByLength(double minLength, double maxLength);
    
 //Methods to update map images based on difficulty level
    void showBeginnerMap();
    void showIntermediateMap();
    void showExpertMap();
}