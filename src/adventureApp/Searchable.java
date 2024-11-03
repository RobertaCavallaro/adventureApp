package adventureApp;

import java.util.List;

public interface Searchable
{
    List<TrailData> search(String query);
}