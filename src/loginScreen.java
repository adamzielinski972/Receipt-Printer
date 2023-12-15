import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class loginScreen {

  @FXML
  private Text errorMessage;

  @FXML
  private TextField password;

  @FXML
  private TextField username;

  private Stage stage;

  public void setStage(Stage stage) {
    this.stage = stage;
  }

  @FXML
  void handleLogIn(ActionEvent event) {
    String usernameVal = username.getText();
    String passwordVal = password.getText();

    if (isValidUser(usernameVal, passwordVal)) {
      openFormPage();
    } else {
      showErrorMessage("Sorry, wrong credentials.");
    }
  }

  private boolean isValidUser(String username, String password) {
    return username.equals("Adam Zielinski") && password.equals("123");
  }

  private void openFormPage() {
    try {
      Parent root = FXMLLoader.load(getClass().getResource("formPage.fxml"));
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.setTitle("Form");
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void showErrorMessage(String message) {
    errorMessage.setText(message);
  }
}
