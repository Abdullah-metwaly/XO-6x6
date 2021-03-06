package Game.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class LaunchController {

    @FXML
    private TextField playerName1TextField;

    @FXML
    private TextField playerName2TextField;
    @FXML
    private Label errorLabel;

    public void startAction(ActionEvent actionEvent) throws IOException {
        if (playerName1TextField.getText().isEmpty() ||playerName2TextField.getText().isEmpty() ) {
            errorLabel.setText("Inputs invalid");
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/game.fxml"));
            Parent root = fxmlLoader.load();
            fxmlLoader.<GameController>getController().initdata(playerName1TextField.getText(),playerName2TextField.getText());
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            log.info("Player are set to {} and {} ,loading game scene.", playerName1TextField.getText(),playerName2TextField.getText());
        }

    }
}
