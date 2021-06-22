package sa.heroz.blockpartypls.until;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class Leaderboard {
  public static int[] getTop(Map<Integer, String> map) {
    int[] a = top10Array(map);
    int b = 0;
    if (a.length > 10)
      b = a.length - 10; 
    int z = 0;
    for (int i = b; i < a.length; i++)
      z++; 
    int[] y = new int[z];
    for (int j = b; j < a.length; j++)
      y[j - b] = a[j]; 
    return y;
  }
  
  private static int[] top10Array(Map<Integer, String> map) {
    Map<Integer, Integer> sortMap = new HashMap<>();
    for (int i = 0; i < map.size(); i++) {
      LinkedList<Integer> keys = new LinkedList<>(map.keySet());
      int x = ((Integer)keys.get(i)).intValue();
      sortMap.put(Integer.valueOf(i), Integer.valueOf(x));
    } 
    int[] a = new int[sortMap.size()];
    for (Iterator<Integer> iterator = sortMap.keySet().iterator(); iterator.hasNext(); ) {
      int j = ((Integer)iterator.next()).intValue();
      int b = ((Integer)sortMap.get(Integer.valueOf(j))).intValue();
      a[j] = b;
    } 
    Arrays.sort(a);
    return a;
  }
}
