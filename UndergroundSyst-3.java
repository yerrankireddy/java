
import java.util.*;

class UndergroundSystem {

    HashMap<Integer, Pair> checkIns;

    HashMap<String, double[]> journeys;

    public UndergroundSystem() {
        checkIns = new HashMap<>();
        journeys = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        checkIns.put(id, new Pair(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        Pair start = checkIns.get(id);

        int travelTime = t - start.time;
        String route = start.station + "#" + stationName;

        if (!journeys.containsKey(route)) {
            journeys.put(route, new double[] { 0, 0 });
        }

        journeys.get(route)[0] += travelTime;
        journeys.get(route)[1]++;

        checkIns.remove(id);
    }

    public double getAverageTime(String startStation, String endStation) {
        String route = startStation + "#" + endStation;

        double[] data = journeys.get(route);

        return data[0] / data[1];
    }

    class Pair {
        String station;
        int time;

        Pair(String station, int time) {
            this.station = station;
            this.time = time;
        }
    }
}
