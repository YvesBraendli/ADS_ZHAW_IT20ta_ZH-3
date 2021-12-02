package ch.zhaw.ads;

import java.util.*;
import java.text.*;


public class Competitor implements Comparable<Competitor> { // TODO: Implement {
        private String name;
        private String time;
        private int rank;

        public Competitor(int rank, String name, String time)  {
            this.rank = rank;
            this.name = name;
            this.time = time;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public String getTime() {
            return time;
        }

        public String getName() {
            return name;
        }

        private static long parseTime(String s)  {
            try {
                DateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                Date date = sdf.parse(s);
                return date.getTime();
            } catch (Exception e) {System.err.println(e);}
            return 0;
        }

        private static String timeToString(int time) {
            SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
            return df.format(new Date(time));
        }

        public String toString() {
            return ""+ rank + " "+name+" "+time;
        }

        @Override
        public int compareTo(Competitor o) {
        	long timeOfCompetitor = parseTime(time);
        	long timeOfConcurrent = parseTime(o.getTime());
        	int rankInDirectComparison = 0;
        	if (timeOfCompetitor>timeOfConcurrent) {
        		rankInDirectComparison +=1;
        	}
        	if (timeOfCompetitor<timeOfConcurrent) {
        		rankInDirectComparison -=1;
        	}
            return  rankInDirectComparison;
        }

        @Override
        public boolean equals (Object o) {
        	boolean timeIsEqual = false;
        	long timeOfCompetitor = parseTime(time);
        	long timeOfConcurrent = 0;
        	if (o instanceof Competitor) {
        		timeOfConcurrent = parseTime(((Competitor) o).getTime());
        	} else {
        		return false;
        	}
        	if (timeOfCompetitor==timeOfConcurrent) {
        		timeIsEqual = true;
        	}
            return timeIsEqual;
        }

        @Override
        public int hashCode() {
            // TODO Implement
            return 0;
        }

    }

    class AlphaComparatorCompetitor implements Comparator<Competitor> {

    @Override
    public int compare(Competitor o1, Competitor o2) {
        // TODO Implement
        int c = 0;
        return c;
    }
}
