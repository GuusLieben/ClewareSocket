package cleware;

import cleware.driver.*;
import org.slf4j.*;

import java.io.*;
import java.net.*;

public class SocketServer {

  private static final Logger LOGGER = LoggerFactory.getLogger(SocketServer.class);

  public void open() {
    try {
      ServerSocket socket = new ServerSocket(5815);
      Socket service = socket.accept();
      DataInputStream input = new DataInputStream(service.getInputStream());
      DataOutputStream output = new DataOutputStream(service.getOutputStream());

      while (true) {
        String in = input.readUTF();

        if (!in.equals("")) LOGGER.debug("Received request from client [" + in + "]");

        if (in.equals("RED")) Main.worker.controller.toggleLed(Led.RED);
        if (in.equals("YELLOW")) Main.worker.controller.toggleLed(Led.YELLOW);
        if (in.equals("GREEN")) Main.worker.controller.toggleLed(Led.GREEN);

        if (in.contains("?")) {
          in = in.replace("?", "");
          Led led = Led.valueOf(in);
          output.writeBoolean(Main.worker.controller.readState(led) == LedState.OFF);
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
      open();
    }
  }
}
