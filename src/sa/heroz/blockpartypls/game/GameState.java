package sa.heroz.blockpartypls.game;

public enum GameState {
  LOADING("Loading..."),
  WAITING("Waiting..."),
  STARTING("Starting..."),
  IN_GAME("Started!"),
  FINISH("The End!"),
  RESTARTING("Restarting...");
  
  private String title;
  
  GameState(String title) {
    this.title = title;
  }
  
  public String toString() {
    return this.title;
  }
}
