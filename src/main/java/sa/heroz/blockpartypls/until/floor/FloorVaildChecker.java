package sa.heroz.blockpartypls.until.floor;

public class FloorVaildChecker {
  public static void check(int x1, int x2, String x) throws FloorVaildCheckerException {
    x = x.toUpperCase();
    if (x1 < 0)
      throw new FloorVaildCheckerException(String.valueOf(x) + "1 must be more than 0"); 
    if (x2 < 0)
      throw new FloorVaildCheckerException(String.valueOf(x) + "2 must be more than 0"); 
    if (x1 > x2)
      throw new FloorVaildCheckerException(String.valueOf(x) + "1 must more than " + x + "2"); 
  }
}
