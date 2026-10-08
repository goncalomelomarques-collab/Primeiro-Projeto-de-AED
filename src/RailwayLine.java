import dataStructures.TwoWayIterator;

import java.util.NoSuchElementException;

public interface RailwayLine {

    void addStation(String stationName);

    void removeStation(String stationName)throws NoSuchElementException;

    boolean hasStation(String stationName);

    boolean isEmpty();

    TwoWayIterator railwayLineIterator();
}
