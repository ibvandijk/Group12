package Model;

import java.util.Date;

public class ContentItem {
int ID;
Date publicationDate;
String Status;

    public ContentItem() {
    }

    public ContentItem(int ID, Date publicationDate, String Status) {
        this.ID = ID;
        this.publicationDate = publicationDate;
        this.Status = Status;
    }

    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Date getPublicationDate() {
        return this.publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getStatus() {
        return this.Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    @Override
    public String toString() {
        return "{" +
            " ID='" + getID() + "'" +
            ", publicationDate='" + getPublicationDate() + "'" +
            ", Status='" + getStatus() + "'" +
            "}";
    }
}

