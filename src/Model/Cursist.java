package Model;
import java.sql.Date;

public class Cursist {
   private String email;
   private String firstName;
   private String lastName;
   private Date birthDate;
   private String sex;
   private String adress;
   private String country;

   public Cursist(String email, String firstName, String lastName, Date birthDate, String sex, String adress, String country){
     email = this.email;
     firstName = this.firstName;
     lastName = this. lastName;
     birthDate = this.birthDate;
     sex = this.sex;
     adress = this.adress;
     country = this.adress;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAdress() {
        return this.adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
