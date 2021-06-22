package sa.heroz.blockpartypls.threads;

class Boolean {
  private boolean value = false;
  
  Boolean(boolean value) {
    set(value);
  }
  
  boolean get() {
    return this.value;
  }
  
  void set(boolean value) {
    this.value = value;
  }
}
