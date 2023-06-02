package it.polimi.ingsw.view.gui.connectioninterface;

import it.polimi.ingsw.view.gui.*;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.util.ResourceBundle;

import static it.polimi.ingsw.view.gui.MyShelfieAlertCreator.*;

public class ConnectionPageController extends MyShelfieController {

    @FXML
    private MyShelfieButton submitButton;

    @FXML
    private StackPane rootPane;

    @FXML
    private StackPane backgroundColorHover;

    @FXML
    private StackPane gridStackContainer;

    @FXML
    private GridPane contentGrid;

    @FXML
    private Label connectionLabel;

    @FXML
    private ChoiceBox<String> protocolBox;

    @FXML
    private String SocketChoice;

    @FXML
    private String RMIChoice;

    @FXML
    private Label serverLabel;

    @FXML
    private TextField serverAddressField;

    @FXML
    private Label portLabel;

    @FXML
    private TextField portNumberField;

    private String chosenConnection = null;
    private String serverAddress = null;
    private String portNumber = null;

    private final PseudoClass errorClass = PseudoClass.getPseudoClass("error");

    @Override
    public void onGameStateChangedNotified() {

    }

    @Override
    public void onExceptionNotified() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setProtocolBoxPseudoClassState();
        setServerAddressPseudoClassState();
        setPortNumberPseudoClassState();
    }

    @FXML
    private void submitButtonClicked(MouseEvent mouseEvent) {
        if(chosenConnection == null) {
            protocolBox.setValue("Missing connection protocol!");
            protocolBox.pseudoClassStateChanged(errorClass, true);
        }

        serverAddress = serverAddressField.getText();

        if(serverAddress == null || serverAddress.equals("")) {
            serverAddressField.pseudoClassStateChanged(errorClass, true);
            serverAddressField.setPromptText("Missing Server Address!");
        }

        portNumber = portNumberField.getText();

        if(portNumber == null || portNumber.equals("")) {
            portNumberField.pseudoClassStateChanged(errorClass, true);
            portNumberField.setPromptText("Missing Port Number!");
        }

        if(chosenConnection != null && !serverAddress.equals("") && !portNumber.equals("")) {
            GUILauncher guiLauncher;

            try {
                guiLauncher = (GUILauncher) getMyShelfieApplicationLauncher();
                GUI gui = guiLauncher.getGUI();

                if(chosenConnection.equals("Remote Methode Invocation"))
                    gui.getLogicController().chosenRMI(Integer.parseInt(portNumber), serverAddress);
                else if(chosenConnection.equals("Socket"))
                    gui.getLogicController().chosenSocket(Integer.parseInt(portNumber), serverAddress);

                guiLauncher.goToLoginPage();

            }catch (ClassCastException e) {
                displayErrorAlert(e);
            } catch (NotBoundException | IOException | ClassNotFoundException e) {
                displayErrorAlert("Can't establish connection with server", "Connection Error");
            }
        }
    }

    @FXML
    private void textFieldKeyPressed(@NotNull KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ESCAPE) {
            rootPane.requestFocus();
        }

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if((keyEvent.getSource()).equals(serverAddressField) && portNumberField.getText().isEmpty()) {
                portNumberField.requestFocus();
            }

            if((keyEvent.getSource()).equals(portNumber) && serverAddressField.getText().isEmpty()) {
                serverAddressField.requestFocus();
            }

            if(((keyEvent.getSource()).equals(serverAddressField) && !portNumberField.getText().isEmpty()) ||
                    ((keyEvent.getSource()).equals(portNumber) && !serverAddressField.getText().isEmpty())) {
                submitButton.requestFocus();
            }
        }
    }

    private void setProtocolBoxPseudoClassState() {
        protocolBox.getSelectionModel().selectedItemProperty().addListener(
                (selected, oldString, newString) -> {
                    chosenConnection = newString;
                    protocolBox.pseudoClassStateChanged(errorClass, false);
                }
        );
    }

    private void setServerAddressPseudoClassState() {
        serverAddressField.textProperty().addListener(event -> {
            serverAddressField.pseudoClassStateChanged(errorClass, serverAddressField.getText().isEmpty());
        });
    }

    private void setPortNumberPseudoClassState() {
        portNumberField.textProperty().addListener(event -> {
            portNumberField.pseudoClassStateChanged(errorClass, verifyPortNumber());

            submitButton.setDisable(verifyPortNumber());
        });
    }

    public void connectionSubmitted(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.ENTER)
            submitButtonClicked(null);
    }

    private boolean verifyPortNumber() {
        return portNumberField.getText().isEmpty() || invalidNumberInput();
    }

    private boolean invalidNumberInput() {
        if(portNumberField.getText().isEmpty())
            return true;

        try {
            Integer.parseInt(portNumberField.getText());
            return false;
        } catch (NumberFormatException nfe) {
            return true;
        }
    }
}
