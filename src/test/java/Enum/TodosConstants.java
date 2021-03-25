package Enum;

public enum TodosConstants {

    TodosConstants(1, 1, "delectus aut autem",false);

    private final Integer id;
    private final Integer userId;
    private final String title;
    private final boolean completed;

    TodosConstants(Integer id, Integer userId, String title, boolean completed) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.completed = completed;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public Integer getUserId() {
        return userId;
    }
}
