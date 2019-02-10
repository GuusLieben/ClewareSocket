package cleware;

import cleware.driver.*;
import com.sun.corba.se.spi.orbutil.threadpool.*;
import org.slf4j.*;

public class Worker implements Runnable {

  private static final Logger LOGGER = LoggerFactory.getLogger(Worker.class);
  public TrafficLight controller;
  private Thread thread;
  private SocketServer server = new SocketServer();

  void setThread(Thread thread) {
    this.thread = thread;
  }

  public void run() {
    System.out.println("1");

    LOGGER.debug("<<Initializing on thread>>");
    LOGGER.debug("Thread:\t\t{}", thread.getName());
    LOGGER.debug("ThreadGroup:\t{}", thread.getThreadGroup().getName());
    LOGGER.debug("ThreadId:\t\t{}\n", thread.getId());

    controller = TrafficLightFactory.createNewInstance();
    LOGGER.debug("<<Opening Socket :5815>>");
    server.open();
  }
}
