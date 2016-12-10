package ge.edu.tsu.controller;

import ge.edu.tsu.model.algorithm.AIAlgorithm;
import ge.edu.tsu.model.algorithm.AlphaBetaAlgorithm;
import ge.edu.tsu.model.algorithm.MinimaxAlgorithm;
import ge.edu.tsu.model.heuristicfunctions.HeuristicFunction;
import ge.edu.tsu.model.heuristicfunctions.OpenAndClose;
import ge.edu.tsu.model.heuristicfunctions.UseOpenAndCloseHeuristic;
import ge.edu.tsu.model.possibletables.AllAdjecentPossibleTable;
import ge.edu.tsu.model.possibletables.NoMoreTableException;
import ge.edu.tsu.model.possibletables.PossibleTable;
import ge.edu.tsu.model.table.Table;
import ge.edu.tsu.model.table.WinInfo;
import ge.edu.tsu.view.Runner;
import java.awt.Point;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PlayFXMLController implements Initializable {

    //*************************** ალგორითმის პარამეტრები ***************************
    HeuristicFunction heuristic = new UseOpenAndCloseHeuristic();
    PossibleTable possibleTable = new AllAdjecentPossibleTable();
    private AIAlgorithm ai = new MinimaxAlgorithm(3);
    //*********************************************************************************

    private final int gridSize = 15;

    private HashMap<Button, Point> buttonToPointHashMap = new HashMap<Button, Point>();
    private HashMap<String, Button> pointToButtonhashMap = new HashMap<String, Button>();

    @FXML
    private GridPane gridPane;

    @FXML
    private Label titleLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Table table = new Table(gridSize);
        Runner.currTable = table;
        ArrayList<ColumnConstraints> columnConstraints = new ArrayList<>();
        for (int i = 0; i < gridSize; i++) {
            ColumnConstraints c = new ColumnConstraints();
            c.setHgrow(Priority.SOMETIMES);
            columnConstraints.add(c);
        }
        gridPane.getColumnConstraints().setAll(columnConstraints);

        ArrayList<RowConstraints> rowConstraints = new ArrayList<>();
        for (int i = 0; i < gridSize; i++) {
            RowConstraints r = new RowConstraints();
            r.setVgrow(Priority.SOMETIMES);
            rowConstraints.add(r);
        }
        gridPane.getRowConstraints().setAll(rowConstraints);

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                Button btn = new Button("  ");
                btn.setFont(new Font(21));
                btn.setMaxHeight(10000);
                btn.setMaxWidth(10000);
                gridPane.add(btn, j, i);
                buttonToPointHashMap.put(btn, new Point(i, j));
                pointToButtonhashMap.put(i + "_" + j, btn);
                if (Runner.onePlayer) {
                    btn.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent event) {
                            actionOnButtonsOnePlayer(event);
                        }
                    });
                } else {
                    btn.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent event) {
                            actionOnButtonsTwoPlayer(event);
                        }
                    });
                }
            }
        }

        if (Runner.onePlayer) {
            if (Runner.firstPlayerTurn) {
                if (table.makeTurn(gridSize / 2, gridSize / 2, (byte) 1)) {
                    Button btn = pointToButtonhashMap.get(gridSize / 2 + "_" + gridSize / 2);
                    btn.setText("X");
                    btn.setStyle("-fx-text-fill:green;");
                    Runner.firstPlayerTurn = false;
                    titleLabel.setText("0-ს სვლაა");
                } else {
                    System.out.println("ნწ X");
                }
            } else {
                titleLabel.setText("0-ს სვლაა");
            }
        }

    }

    public void actionOnButtonsTwoPlayer(ActionEvent event) {
        Table table = Runner.currTable;
        Button btn = (Button) event.getSource();
        Point point = buttonToPointHashMap.get(btn);
        if (Runner.firstPlayerTurn) {
            if (table.makeTurn(point.x, point.y, (byte) 1)) {
                btn.setText("X");
                btn.setStyle("-fx-text-fill:green;");
                Runner.firstPlayerTurn = false;
                titleLabel.setText("0-ს სვლაა");
            } else {
                System.out.println("ნწ X");
            }
        } else if (table.makeTurn(point.x, point.y, (byte) 2)) {
            btn.setText("0");
            btn.setStyle("-fx-text-fill:blue;");
            Runner.firstPlayerTurn = true;
            titleLabel.setText("X-ს სვლაა");
        } else {
            System.out.println("ნწ 0");
        }

        WinInfo info = table.win();
        if (info != null) {
            endGame(info);
        }
    }

    public void actionOnButtonsOnePlayer(ActionEvent event) {
        Table table = Runner.currTable;
        Button btn = (Button) event.getSource();
        Point point = buttonToPointHashMap.get(btn);
        if (Runner.firstPlayerTurn) {
            return;
        } else {
            if (table.makeTurn(point.x, point.y, (byte) 2)) {
                btn.setText("0");
                btn.setStyle("-fx-text-fill:blue;");
                Runner.firstPlayerTurn = true;
            } else {
                System.out.println("ნწ 0");
                return;
            }
            WinInfo info = table.win();
            if (info != null) {
                endGame(info);
            } else {

                if (heuristic.heuristic(1, table) == Integer.MAX_VALUE) {
                    for (int i = 0; i < gridSize; i++) {
                        for (int j = 0; j < gridSize; j++) {
                            if (table.getTable()[i][j] == 0) {
                                Table t = table.copyTable();
                                t.makeTurn(i, j, (byte) 1);
                                if (t.win() != null) {
                                    point = new Point(i, j);
                                }
                            }
                        }
                    }
                } else {
                    ai.reload();
                    double d = ai.algorithm(table, 0, possibleTable, heuristic);
                    if (d >= 5000) {
                        heuristic.getCoeficient().setDeffencePercentage(30);
                    } else if (d <= 8000) {
                        heuristic.getCoeficient().setDeffencePercentage(60);
                    }
                    System.out.println(d);

                    point = table.getBestTurn();
                }
                btn = pointToButtonhashMap.get(point.x + "_" + point.y);
                if (table.makeTurn(point.x, point.y, (byte) 1)) {
                    btn.setText("X");
                    btn.setStyle("-fx-text-fill:green;");
                    Runner.firstPlayerTurn = false;
                } else {
                    System.out.println("ნწ X");
                }
            }
        }

        WinInfo info = table.win();
        if (info != null) {
            endGame(info);
        }
    }

    public void endGame(WinInfo info) {
        //OpenAndClose oc1 = OpenAndClose.getOpenAndClose(Runner.currTable, 1);
        //OpenAndClose oc2 = OpenAndClose.getOpenAndClose(Runner.currTable, 2);
        Point point = info.getStart();
        for (int i = 0; i < 5; i++) {
            Button btn = pointToButtonhashMap.get(point.x + "_" + point.y);
            btn.setStyle("-fx-text-fill: red;");
            switch (info.getWinType()) {
                case HORIZONTAL:
                    point.y++;
                    break;
                case VERTICAL:
                    point.x++;
                    break;
                case DIAGONAL1:
                    point.x++;
                    point.y++;
                    break;
                case DIAGONAL2:
                    point.x--;
                    point.y++;
                    break;
            }
        }
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.setScene(new Scene(VBoxBuilder.create().
                children(new Text("Winner - " + info.getWinner())).
                alignment(Pos.CENTER).padding(new Insets(15, 15, 15, 15)).build()));
        dialogStage.show();
        //Alert alert = new Alert(Alert.AlertType.INFORMATION);
        //alert.setContentText();
        //alert.showAndWait();

        Runner.currTable = new Table(gridSize);
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                pointToButtonhashMap.get(i + "_" + j).setText("  ");
            }
        }
        titleLabel.setText("X-ს სვლაა");
        Runner.firstPlayerTurn = true;

    }

}
