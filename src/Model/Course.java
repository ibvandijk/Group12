package Model;

public class Course {

    private String courseName;
    private String subject;
    private String introductionText;
    private int difficulty;

    public Course(String courseName, String subject, String introductionText, int difficulty) {
        this.courseName = courseName;
        this.subject = subject;
        this.introductionText = introductionText;
        this.difficulty = difficulty;
    }


    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getIntroductionText() {
        return introductionText;
    }

    public void setIntroductionText(String introductionText) {
        this.introductionText = introductionText;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}
