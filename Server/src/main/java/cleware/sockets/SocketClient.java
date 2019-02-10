package cleware.sockets;

import cleware.*;
import org.slf4j.*;

import java.io.*;
import java.net.*;

public class SocketClient {

  Socket client;
  DataOutputStream output;
  DataInputStream input;

  private static final Logger LOGGER = LoggerFactory.getLogger(SocketClient.class);

  public SocketClient() {
    try {
      LOGGER.debug("<<Opening socket listener>>");
      client = new Socket("192.168.2.122", 5815);
      output = new DataOutputStream(client.getOutputStream());
      input = new DataInputStream(client.getInputStream());
      System.out.println("Output open");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void write(String line) {
    try {
      output.writeUTF(line);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String readState(String led, boolean reversed) {
    try {
      output.writeUTF('?' + led);
      if (reversed) return !input.readBoolean() ? "ON" : "OFF";
      return input.readBoolean() ? "ON" : "OFF";
    } catch (IOException e) {
      e.printStackTrace();
    }
    return "OFF";
  }

  public void close() {
    try {
      output.close();
      input.close();
      client.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
