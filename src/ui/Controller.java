package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import todo.TodoItem;
import todo.TodoList;

import java.io.*;
import java.sql.Date;
import java.time.LocalDate;

public class Controller {

    private int numTodo;

    private TodoList todoList;

    @FXML
    private Pane mainPane;

    @FXML
    private Button addButton;

    @FXML
    private GridPane todoGrid;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private TextField title;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextArea description;

    @FXML
    private void addTodo() {
        TodoItem item = new TodoItem(title.getText(), Date.valueOf(datePicker.getValue()),description.getText());
        todoList.add(item);
        addTodo(item);

    }

    private void addTodo(TodoItem item) {
        Pane added = createGridContent(item);
        todoGrid.getRowConstraints().add(numTodo,new RowConstraints(added.getPrefHeight()));
        todoGrid.add(added,0,numTodo);
        numTodo++;

        title.setText("");
        description.setText("");
        datePicker.setValue(LocalDate.now());
        datePicker.getEditor().setText(LocalDate.now().toString());

        System.out.println(todoList);
        serialize();

    }

    private void onDoneButton(ActionEvent event) {
        TodoItem gridContent = (TodoItem) ((Button)(event.getSource())).getParent();
        ((GridPane)gridContent.getParent()).getChildren().remove(gridContent);
        todoList.remove(gridContent);
        serialize();
    }

    private Pane createGridContent(TodoItem item) {

        item.setPadding(new Insets(5,5,5,5));
        Label title = new Label(item.getTitle());
        title.setStyle("-fx-font-weight: bold");
        item.add(title,0,0);

        Label desc = new Label(item.getDesc());
        desc.setWrapText(true);
        item.add(desc,0,1);

        Label date = new Label(item.getDate().toString());
        date.setStyle("-fx-font-style: italic");
        item.add(date,0,2);

        Button done  = new Button("Done");
        done.setOnAction(event -> onDoneButton(event));
        done.setPadding(new Insets(2,2,2,2));
        item.add(done,0,3);

        return item;
    }

    private void serialize() {
        try {
            FileOutputStream fileOut = new FileOutputStream("list.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(todoList);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in list.ser");
        } catch (IOException e) {

        }
    }

    private boolean deserialize() {
        try {
            FileInputStream fileIn = new FileInputStream("list.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            todoList = (TodoList) in.readObject();
            in.close();
            fileIn.close();
            return true;
        } catch (IOException i) {
            i.printStackTrace();
            return false;
        } catch (ClassNotFoundException c) {
            System.out.println("List class not found");
            c.printStackTrace();
            return false;
        }
    }

    @FXML
    public void initialize() {
        todoGrid.setGridLinesVisible(true);
        numTodo = 0;
        todoList = new TodoList();
        if (deserialize()) {
            for (TodoItem item:todoList.getList()) {
                addTodo(item);
            }
        }
        datePicker.setValue(LocalDate.now());
        datePicker.getEditor().setText(LocalDate.now().toString());
    }
}
