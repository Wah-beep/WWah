public interface Observer {
  
  /** 
  * New information is available to observe.
  * @param newData Data Structure holding information.
  */
  void update(Object newData);
  
  /**
  * Corresponding checkbox is checked (pass true) or unchecked (pass false).
  @param checked pass true or false.
  */
  public void subscribe(boolean checked);
  
}
