import java.util.Date;

public class Task {

    private String name;
    private String description;
    private Boolean completed;
    private Date created;
    private Date until;


    public Task(String name, String description, Boolean completed,  Date until) {
        this.name = name;
        this.description = description;
        this.completed = completed;
        this.created = new Date(System.currentTimeMillis());
        this.until = until;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
