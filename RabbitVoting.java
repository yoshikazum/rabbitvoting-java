import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class RabbitVoting {

  public String getWinner(String[] names, String[] votes) {
    System.out.println("*** start ***");
    HashMap<String, Integer> countMap = new HashMap<String, Integer>(names.length);

    for (int j = 0; j < names.length; j++) {
      String voter = names[j];
      String vote = votes[j];
      if (voter.equals(vote)) {
        System.out.println("ignored");
      } else {
        Integer count = countMap.get(vote);
        if (count == null){
          countMap.put(vote, Integer.valueOf(1));
          continue;
        }
        int value = count.intValue();
        value++;
        count = Integer.valueOf(value);
        countMap.put(vote, count);
        System.out.println("putted count:"+count);
      }
    }

    String winner = "";
    Integer maxCount = 0;
    Set<String> storedKey = countMap.keySet();
    for (Iterator iterator = storedKey.iterator(); iterator.hasNext();) {
      String name = (String) iterator.next();
      Integer count = countMap.get(name);
      if(count==null)
        continue;
      
      if (maxCount < count.intValue()) {
        maxCount = count.intValue();
        winner = name;
      }
    }

    System.out.println("maxCount:"+maxCount);
    int winnerCount = 0;
    for (int i = 0; i < names.length; i++) {
      String string = names[i];
      Integer count = countMap.get(string);
      if(count==null){
        continue;
      }
      if (count == maxCount) {
        winnerCount++;
      }
      System.out.println("name:"+string+" count:"+count);
    }
    if (winnerCount > 1) {
      System.out.println("同じ投票数");
      return "";
    }
    return winner;
  }

}
