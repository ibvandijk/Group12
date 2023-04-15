package Model;

public class WebCast extends ContentItem{
String title;
String description;
String speaker;
String organisation;
float progress;

    public WebCast(String title, String description, String speaker, String organisation, float progress) {
        this.title = title;
        this.description = description;
        this.speaker = speaker;
        this.organisation = organisation;
        this.progress = progress;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpeaker() {
        return this.speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public String getOrganisation() {
        return this.organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public float getProgress() {
        return this.progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }

}
