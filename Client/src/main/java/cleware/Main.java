package cleware;

public class Main {

  public static Worker worker = new Worker();
  private static Thread thread = new Thread(worker);

  public static void main(String[] args) throws InterruptedException {
    worker.setThread(thread);
    thread.start();
    thread.join();
  }
}
