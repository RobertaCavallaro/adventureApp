package adventureApp;

import java.util.List;

public interface Filterable
{
    List<TrailData> filterByDifficulty(String difficulty);
    List<TrailData> filterByLength(double minLength, double maxLength);
}