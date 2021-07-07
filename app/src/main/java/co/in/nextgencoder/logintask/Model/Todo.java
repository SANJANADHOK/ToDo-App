package co.in.nextgencoder.logintask.Model;

public class Todo {

    private String id;
    private boolean status;
    private String todoTask;

    public Todo() { }

    public Todo(String id, boolean status, String todoTask) {
        this.id = id;
        this.status = status;
        this.todoTask = todoTask;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTodoTask() {
        return todoTask;
    }

    public void setTodoTask(String todoTask) {
        this.todoTask = todoTask;
    }
}

