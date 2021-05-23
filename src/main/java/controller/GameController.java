package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import Game.results.GameResult;
import Game.results.GameResultDao;
import Game.results.TopPlayer;
import Game.results.TopPlayerDao;
import Game.state.Player;
import Game.state.GameState;

import java.io.IOException;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.List;

@Slf4j
public class GameController {


    private  int clickedColumn, clickedRow;
    private GameState gameState;


    private int stepCountPlayerOne;
    private int stepCountPlayerTwo;

    private Player playerOne;

    private Player playerTwo;

    private Player winner;
    private List<Image> Images;


    private ZonedDateTime beginGame;

    private GameResultDao gameResultDao;
    private TopPlayerDao topPlayerDao;



    @FXML
    private Label stepsPlayerOne;

    @FXML
    private Label stepsPlayerTwo;

    @FXML
    private Label stepsPlayerTwoL;

    @FXML
    private Label stepsPlayerOneL;

    @FXML
    private GridPane gameGrid;


    @FXML
    private Label messageLabel;

    @FXML
    private Button EndGameButton;



    private void drawGameState() {


        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                ImageView view = (ImageView) gameGrid.getChildren().get(i * 6 + j);
                view.setImage(Images.get(gameState.getTray()[i][j].getStatus()));
            }
        }


    }

    public void initdata(String playerOne , String playerTwo) {
        this.playerOne = new Player(playerOne);
        this.playerTwo = new Player(playerTwo);
        stepsPlayerOneL.setText(this.playerOne.getName() + " Steps");
        stepsPlayerTwoL.setText(this.playerTwo.getName() + " Steps");
    }


    @FXML
    public void initialize() {
        gameResultDao = GameResultDao.getInstance();
        topPlayerDao = TopPlayerDao.getInstance();

        Images = List.of(
                new Image(getClass().getResource("/pictures/empty.png").toExternalForm()),
                new Image(getClass().getResource("/pictures/x.png").toExternalForm()),
                new Image(getClass().getResource("/pictures/o.png").toExternalForm())
                );
        Platform.runLater(this::start);
    }


    public void clickCell(MouseEvent mouseEvent) {

   try {
         clickedColumn = GridPane.getColumnIndex((Node)mouseEvent.getSource());
         clickedRow = GridPane.getRowIndex((Node)mouseEvent.getSource());

   } catch (NullPointerException e) {

       if (!gameState.isGameFinished() && gameState.canPlayerplay(clickedRow, clickedColumn)) {
           gameState.Play(clickedRow, clickedColumn);
           messageLabel.setText("Player " + gameState.getActivePlayer().getName() + " is playing now");
           if (gameState.getActivePlayer().equals(gameState.getPlayerOne())) {
               stepCountPlayerTwo++;
           } else {
               stepCountPlayerOne++;
           }
           stepsPlayerOne.setText(String.valueOf(stepCountPlayerOne));
           stepsPlayerTwo.setText(String.valueOf(stepCountPlayerTwo));
           if (gameState.isGameFinished()) {
               int stepCount;
               if (gameState.getActivePlayer().equals(gameState.getPlayerOne())) {
                   stepCount = stepCountPlayerTwo;
                   winner =playerTwo;
               } else {
                   stepCount = stepCountPlayerOne;
                   winner =playerOne;
               }
               log.info("Player {} won the game in {} steps.", winner.getName(), stepCount);
               messageLabel.setText(" Game is finished (No more cells to be chosen) ... Player "+ winner.getName() + " won the game");

               EndGameButton.setText("Finish");

               gameResultDao.persist(getResult());

             if (topPlayerDao.update(winner.getName())==null) topPlayerDao.persist(new TopPlayer(winner.getName(),1));

           }
       }

       drawGameState();
       }
    }

    public void start() {
        gameState = new GameState(playerOne,playerTwo);
        stepCountPlayerOne = 0;
        stepCountPlayerTwo = 0;
        messageLabel.setText("Player " + this.playerOne.getName()+ " has the first turn");
        drawGameState();
        beginGame = ZonedDateTime.now();
        log.info("Game start.");
    }
    public void resetGame(ActionEvent actionEvent)  {
        log.debug("{} is pressed", ((Button) actionEvent.getSource()).getText());
        log.info("Resetting game...");
        start();
    }

    private GameResult getResult() {

        GameResult result;
        result = GameResult.builder()
                                    .playerOne(gameState.getPlayerOne().getName())
                                    .playerTwo(gameState.getPlayerTwo().getName())
                                    .winner(winner.getName())
                                    .stepsPlayerOne(stepCountPlayerOne)
                                    .stepsPlayerTwo(stepCountPlayerTwo)
                                    .duration(Duration.between(beginGame, ZonedDateTime.now()))
                                    .build();
        return result;
    }

    public void endGame(ActionEvent actionEvent) throws IOException {
        if (!gameState.isGameFinished()) {
            gameResultDao.persist(getResult());
        }

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/highscores.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
        log.info("Finished game, loading Top five scene.");
    }
}
