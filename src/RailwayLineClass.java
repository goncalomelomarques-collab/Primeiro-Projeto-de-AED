import dataStructures.ArrayClass;
import dataStructures.TwoWayIterator;
import dataStructures.TwoWayList;
import dataStructures.exceptions.*;

    /**
     * Work carried out by: Gonçalo Melo Marques 74277
     * 2026/10/09
     */

public class RailwayLineClass implements RailwayLine {

    private TwoWayList<String> railwayLine;
    private static final int CAPACITY = 1000;


    public RailwayLineClass(){
        this.railwayLine = new ArrayClass<>(CAPACITY);

    }

    /**
     * Adds a station to the end of the railway line.
     *
     * @param stationName name of the station to be added
     * @pre stationName != null
     * @pre the railway line is not full (number of stations < CAPACITY)
     */
    @Override
    public void addStation(String stationName){
        railwayLine.addLast(stationName);
    }

    /**
     * Removes a station from the railway line.
     * The stations after the removed one keep their relative order.
     *
     * @param stationName name of the station to be removed
     * @throws NoSuchElementException if the station does not belong to the railway line
     * @pre stationName != null
     */
    @Override
    public void removeStation(String stationName) throws NoSuchElementException {
        int pos = railwayLine.indexOf(stationName);
        if (pos == -1)
            throw new NoSuchElementException();
        railwayLine.remove(pos);
    }

    /**
     * Checks whether a station belongs to the railway line.
     *
     * @param stationName name of the station to be searched
     * @return true iff the station belongs to the railway line
     * @pre stationName != null
     */
    @Override
    public boolean hasStation(String stationName) {
        return railwayLine.indexOf(stationName) != -1;
    }

    /**
     * Checks whether the railway line has no stations.
     *
     * @return true iff the railway line is empty
     */
    @Override
    public boolean isEmpty(){return railwayLine.isEmpty();}

    /**
     * Returns a two-way iterator over the stations of the railway line,
     * from the first to the last station.
     *
     * @return two-way iterator over the stations
     */
    @Override
    public TwoWayIterator<String> railwayLineIterator() {
        return railwayLine.twoWayiterator();
    }



}
