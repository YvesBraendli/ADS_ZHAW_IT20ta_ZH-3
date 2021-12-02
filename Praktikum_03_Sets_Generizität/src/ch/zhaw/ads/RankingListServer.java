package ch.zhaw.ads;

import java.util.*;

public class RankingListServer implements CommandExecutor {

    public List<Competitor> createList(String rankingText) {
    	List<Competitor> competitorList = new LinkedList<>();
        String[] lines = rankingText.split("\n");
        for (String line : lines) {
            String name = line.split(";")[0];
            String time = line.split(";")[1];
            competitorList.add(new Competitor(0, name,  time));
        }
        return competitorList;
    }

    public String createSortedText(List<Competitor> competitorList) {
    	Collections.sort(competitorList);
        int rank = 1;
        StringBuilder sb = new StringBuilder();
        for (Competitor c : competitorList) {
            c.setRank(rank++);
            sb.append(c +"\n");
        }
        return sb.toString();
    }

    public String createNameList(List<Competitor> competitorList) {
    	StringBuilder sb = new StringBuilder();
        for (Competitor c : competitorList) {
            sb.append(c +"\n");
        }
        return sb.toString();
    }

    public String execute(String rankingList) throws Exception {
        List<Competitor> competitorList = createList(rankingList);
        return "Rangliste\n" + createSortedText(competitorList);
    }

}