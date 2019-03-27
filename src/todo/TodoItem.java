package todo;


import javafx.scene.layout.GridPane;
import java.io.Serializable;
import java.util.Date;

public class TodoItem extends GridPane implements Serializable {

    static final long serialVersionUID = 42L;

    private String title;
    private Date date;
    private String desc;

    public TodoItem(String title, Date date, String desc) {
        this.title = title;
        this.date = date;
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return "TodoItem{" +
                "title='" + title + '\'' +
                ", date=" + date +
                ", desc='" + desc + '\'' +
                '}';
    }
}
