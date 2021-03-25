package Pojo;

public class Todos {
    private Integer id;
    private Integer userId;
    private String title;
    private boolean completed;

    public Todos() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean checkId(Integer id) {
        return this.id.equals(id);
    }

    public boolean checkUserId(Integer userId) {
        return this.userId.equals(userId);
    }

    public boolean checkTitle(String title) {
        return this.title.equals(title);
    }

    public boolean checkCompleted(boolean completed){
        return this.completed == completed;
    }
}
