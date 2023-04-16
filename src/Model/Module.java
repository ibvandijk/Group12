package Model;

public class Module extends ContentItem{
    String title;
    String version;
    String description;
    String contactName;
    String contactEmail;
    String trackingID;
    Course course;
    float progress;

    public Module(String title, String version, String description, String contactName, String contactEmail, String trackingID, Course course, float progress) {
        this.title = title;
        this.version = version;
        this.description = description;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
        this.trackingID = trackingID;
        this.course = course;
        this.progress = progress;
    }

    public Module(){}

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactName() {
        return this.contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactEmail() {
        return this.contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getTrackingID() {
        return this.trackingID;
    }

    public void setTrackingID(String trackingID) {
        this.trackingID = trackingID;
    }

    public Course getCourse() {
        return this.course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public float getProgress() {
        return this.progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }
}
