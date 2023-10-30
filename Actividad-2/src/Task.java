import java.util.Date;

public class Task {

    private String title;
    private String description;
    private Boolean completed;
    private Date created;
    private Date until;


    public Task(String title, String description, Boolean completed,  Date until) {
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.created = new Date(System.currentTimeMillis());
        this.until = until;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUntil() {
        return until;
    }

    public void setUntil(Date until) {
        this.until = until;
    }
}
