import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class formScreen {

  @FXML
  private Text addressText;

  @FXML
  private TextField bracesVal;

  @FXML
  private Text cityPostalText;

  @FXML
  private TextField crownsVal;

  @FXML
  private TextField date;

  @FXML
  private Label dateLabel;

  @FXML
  private TextField extractionsVal;

  @FXML
  private TextField fillingVal;

  @FXML
  private TextField hygeniestName;

  @FXML
  private TextField implantsVal;

  @FXML
  private Label itemsLabel;

  @FXML
  private Text nameText;

  @FXML
  private TextField patientAddress;

  @FXML
  private TextField patientCityPostal;

  @FXML
  private TextField patientPhone;

  @FXML
  private TextField patientID;

  @FXML
  private Label patientIDLabel;

  @FXML
  private TextField patientName;

  @FXML
  private Text phoneText;

  @FXML
  private Button printReceipt;

  @FXML
  private TextField retainerVal;

  @FXML
  private TextField scalingVal;

  @FXML
  private Label totalLabel;

  @FXML
  private TextField whiteningVal;

  @FXML
  private TextField xrayVal;

  private Stage stage;
  private Scene scene;

  @FXML
  void clearPatientInfo(ActionEvent event) {
    patientID.setText("");
    patientName.setText("");
    patientAddress.setText("");
    patientCityPostal.setText("");
    patientPhone.setText("");
    hygeniestName.setText("");
    date.setText("");
  }

  @FXML
  void clearServices(ActionEvent event) {
    xrayVal.setText("0");
    scalingVal.setText("0");
    fillingVal.setText("0");
    extractionsVal.setText("0");
    implantsVal.setText("0");
    crownsVal.setText("0");
    bracesVal.setText("0");
    retainerVal.setText("0");
    whiteningVal.setText("0");
  }

  @FXML
  void handleLogOut(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.setTitle("Form");
    stage.show();
  }

  @FXML
  void handlePrint(ActionEvent event) {
    populatePatientInfo();
    String receiptText = generateReceiptText();
    String totalsText = generateTotalsText();
    displayReceiptAndTotals(receiptText, totalsText);
  }

  private void populatePatientInfo() {
    patientIDLabel.setText(patientID.getText());
    dateLabel.setText(date.getText());
    nameText.setText(patientName.getText());
    addressText.setText(patientAddress.getText());
    cityPostalText.setText(patientCityPostal.getText());
    phoneText.setText(patientPhone.getText());
  }

  private ArrayList<Integer> getServiceVals() {

    ArrayList<Integer> integerArrayList = new ArrayList<>();

    integerArrayList.add(Integer.parseInt(xrayVal.getText()));
    integerArrayList.add(Integer.parseInt(scalingVal.getText()));
    integerArrayList.add(Integer.parseInt(fillingVal.getText()));
    integerArrayList.add(Integer.parseInt(extractionsVal.getText()));
    integerArrayList.add(Integer.parseInt(implantsVal.getText()));
    integerArrayList.add(Integer.parseInt(crownsVal.getText()));
    integerArrayList.add(Integer.parseInt(bracesVal.getText()));
    integerArrayList.add(Integer.parseInt(retainerVal.getText()));
    integerArrayList.add(Integer.parseInt(whiteningVal.getText()));

    return integerArrayList;

  }

  private String generateReceiptText() {
    String receiptText = "";

    ArrayList<Integer> integerArrayList = getServiceVals();

    if (integerArrayList.get(0) != 0) {
      receiptText += ("X-ray                                                  " + integerArrayList.get(0) + "\n");
    }
    if (integerArrayList.get(1) != 0) {
      receiptText += ("Scaling                                               " + integerArrayList.get(1) + "\n");
    }
    if (integerArrayList.get(2) != 0) {
      receiptText += ("Filling                                                 " + integerArrayList.get(2) + "\n");
    }
    if (integerArrayList.get(3) != 0) {
      receiptText += ("Extractions                                       " + integerArrayList.get(3) + "\n");
    }
    if (integerArrayList.get(4) != 0) {
      receiptText += ("Implants                                            " + integerArrayList.get(4) + "\n");
    }
    if (integerArrayList.get(5) != 0) {
      receiptText += ("Crowns                                              " + integerArrayList.get(5) + "\n");
    }
    if (integerArrayList.get(6) != 0) {
      receiptText += ("Braces                                                " + integerArrayList.get(6) + "\n");
    }
    if (integerArrayList.get(7) != 0) {
      receiptText += ("Retainer                                            " + integerArrayList.get(7) + "\n");
    }
    if (integerArrayList.get(8) != 0) {
      receiptText += ("Whitening                                         " + integerArrayList.get(8) + "\n");
    }

    return receiptText;
  }

  private String generateTotalsText() {

    ArrayList<Integer> integerArrayList = getServiceVals();

    int subTotal = calculateSubTotal(integerArrayList.get(0), integerArrayList.get(1), integerArrayList.get(2),
        integerArrayList.get(3), integerArrayList.get(4),
        integerArrayList.get(5), integerArrayList.get(6), integerArrayList.get(7), integerArrayList.get(8));
    double tax = subTotal * 0.13;

    String totalsText = "Subtotal:                                          $" + subTotal + "\n"
        + "Tax:                                                   $" + String.format("%.2f", tax) + "\n"
        + "Total:                                                $" + String.format("%.2f", subTotal + tax) + "\n";

    return totalsText;
  }

  private int calculateSubTotal(int xray, int scaling, int filling, int extractions, int implants, int crowns,
      int braces, int retainers, int whitening) {
    return (xray * 100) + (scaling * 20) + (filling * 50) + (extractions * 50) + (implants * 100) + (crowns * 100)
        + (braces * 1000) + (retainers * 1000) + (whitening * 200);
  }

  private void displayReceiptAndTotals(String receiptText, String totalsText) {
    itemsLabel.setText(receiptText);
    totalLabel.setText(totalsText);
  }

}
