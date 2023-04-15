package Model;

public class Course {

    private String courseName;
    private String subject;
    private String introductionText;
    private int difficulty;
    private int numStudentsWithFullProgress = 0;

    public Course(String courseName, String subject, String introductionText, int difficulty,
            int numStudentsWithFullProgress) {
        this.courseName = courseName;
        this.subject = subject;
        this.introductionText = introductionText;
        this.difficulty = difficulty;
        this.numStudentsWithFullProgress = numStudentsWithFullProgress;
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

    public int getNumStudentsWithFullProgress() {
        return this.numStudentsWithFullProgress;
    }

    public void setNumStudentsWithFullProgress(int numStudentsWithFullProgress) {
        this.numStudentsWithFullProgress = numStudentsWithFullProgress;
    }
}
