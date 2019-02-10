package cleware;

import javafx.application.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;
import org.slf4j.*;

import java.util.*;

public class Worker extends Application implements Runnable {

  private static final Logger LOGGER = LoggerFactory.getLogger(Worker.class);
  private Thread thread;

  void setThread(Thread thread) {
    this.thread = thread;
  }

  public void run() {
    System.out.println("1");

    LOGGER.debug("<<Initializing on thread>>");
    LOGGER.debug("Thread:\t\t{}", thread.getName());
    LOGGER.debug("ThreadGroup:\t{}", thread.getThreadGroup().getName());
    LOGGER.debug("ThreadId:\t\t{}\n", thread.getId());

    launch();
  }

  @Override
  public void start(Stage stage) throws Exception {
    int[] size = {320, 500};

    LOGGER.debug("<<Loading stage>>\n");

    Parent root =
        FXMLLoader.load(
            Objects.requireNonNull(getClass().getClassLoader().getResource("cleware.fxml")));
    stage.setTitle("Cleware USB-Ampel 902579");
    stage.setScene(new Scene(root, size[0], size[1]));
    stage.setMinWidth(size[0]);
    stage.setMinHeight(size[1]);
    stage.setMaxWidth(size[0]);
    stage.setMaxHeight(size[1]);

    stage.show();

    System.out.println();
    LOGGER.debug("Initialized Stage:\t{}\n", stage.getTitle());
  }

  @Override
  public void stop() {
    Main.client.close();
  }
}
