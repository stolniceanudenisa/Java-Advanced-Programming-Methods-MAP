package domain;

public class User extends Entity{
    private String firstName;
    private String lastName;
    private String mail;
    private int age;
    private String password;

    public User(long id,String firstName, String lastName, String mail, int age, String password) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.age = age;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" + "Id=" + this.id + '\'' +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mail='" + mail + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                '}';
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMail() {
        return mail;
    }

    public int getAge() {
        return age;
    }

    public String getPassword() {
        return password;
    }
}
