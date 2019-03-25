package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class Controller {

    private int numTodo;

    @FXML
    private FlowPane mainPane;

    @FXML
    private Button addButton;

    @FXML
    private GridPane todoGrid;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private void addTodo() {
        Label added = Math.random()<0.5 ? new Label("Hello\n\nTest\n\nTest") : new Label("smol hello");
        todoGrid.getRowConstraints().add(numTodo,new RowConstraints(added.getPrefHeight()));
        todoGrid.add(added,0,numTodo);
        numTodo++;
    }

    @FXML
    public void initialize() {
        todoGrid.setGridLinesVisible(true);
        numTodo = 0;
    }
}
