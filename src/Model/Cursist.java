package Model;
import java.sql.Date;

public class Cursist {
   private String email;
   private String name;
   private Date birthDay;
   private String sex;
   private String adress;
   private String country;

    public Cursist(){

    }

    public Cursist(String email, String name, Date birthDay, String sex, String adress, String country){
    this.email = email;
    this.name = name;
    this.birthDay = birthDay;
    this.sex = sex;
    this.adress = adress;
    this.country = country;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return this.name;
    }

    public void setLastName(String name) {
        this.name = name;
    }

    public Date getBirthDay() {
        return this.birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
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
