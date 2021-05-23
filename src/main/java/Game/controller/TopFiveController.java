package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import Game.results.TopPlayer;
import Game.results.TopPlayerDao;

import java.io.IOException;

import java.util.List;

@Slf4j
public class TopFiveController {

    @FXML
    private TableView<TopPlayer> highScoreTable;

    @FXML
    private TableColumn<TopPlayer, String> name;

    @FXML
    private TableColumn<TopPlayer, Integer> numOfGames;


    public void back(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/launch.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
        log.info("Loading launch scene.");
    }


    @FXML
    public void initialize() {
        TopPlayerDao topPlayerDao = TopPlayerDao.getInstance();

        List<TopPlayer> toptenList = topPlayerDao.findBest(5);

        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        numOfGames.setCellValueFactory(new PropertyValueFactory<>("numOfGames"));


        ObservableList<TopPlayer> observableResult = FXCollections.observableArrayList();
        observableResult.addAll(toptenList);

        highScoreTable.setItems(observableResult);
    }

}
