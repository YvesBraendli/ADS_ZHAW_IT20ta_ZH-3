import java.util.*;
import java.text.*;

public class MyCompetitor implements Comparable<MyCompetitor> {
    private String name;
    private String time;
    private int rank;

    public MyCompetitor(int rank, String name, String time)  {
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
    public int compareTo(MyCompetitor o) {
        if (this.rank < o.rank){
            return 1;
        } else if (this.rank == o.rank){
            return 0;
        } else {
            return -1;
        }
    }

    @Override
    public int hashCode() {
        int code = 1;
        code = code * 13 + rank;
        code = code * 17 + name.hashCode();
        return code;
    }

    @Override
    public boolean equals (Object o) {
        if (this.hashCode() == o.hashCode()){
            return true;
        } else {
            return false;
        }
    }
}