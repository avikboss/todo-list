package todo;

import java.io.*;
import java.util.ArrayList;

public class TodoList implements Serializable {

    static final long serialVersionUID = 43L;

    private ArrayList<TodoItem> list;

    public TodoList() {
        this.list = new ArrayList<>();
    }

    public void add(TodoItem item) {
        list.add(item);
    }

    public TodoItem remove(TodoItem item) {
        list.remove(item);
        return item;
    }

    public ArrayList<TodoItem> getList() {
        return list;
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
