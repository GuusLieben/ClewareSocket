package cleware.gui;

import cleware.*;
import javafx.fxml.*;
import javafx.scene.control.*;

import java.net.*;
import java.util.*;

public class UIController implements Initializable {

  public Button toggleRed;
  public Button toggleYellow;
  public Button toggleGreen;

  @FXML
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    setToggle(toggleRed, "RED");
    setToggle(toggleYellow, "YELLOW");
    setToggle(toggleGreen, "GREEN");
  }

  private void setToggle(Button button, String led) {
    button.setText(led + ": " + Main.client.readState(led, true));
    button.setOnAction(
        e -> {
          Main.client.write(led);
          button.setText(led + ": " + Main.client.readState(led, false));
        });
  }
}
