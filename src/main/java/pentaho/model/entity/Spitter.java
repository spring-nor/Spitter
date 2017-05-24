package pentaho.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GenerationType;


/**
 * Created by norman on 26/03/17.
 */


@Entity
//@Table(name = "SPITTER")
public class Spitter {

    public Spitter(Long id, String username, String password, String fullName,
                   String email, boolean updateByEmail) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.updateByEmail = updateByEmail;
    }

    public Spitter(Spitter spitter) {
        this.username = spitter.getUsername();
        this.password = spitter.getPassword();
        this.firstName = spitter.getFirstName();
        this.lastName   =spitter.getLastName();
        this.fullName = spitter.getFullName();
        this.email = spitter.getEmail();
        this.updateByEmail = spitter.isUpdateByEmail();
    }

    public Spitter() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotNull
//    @Size(min = 5, max = 16, message = "{username.size}")
    @Column(name = "username")
    private String username;

//    @NotNull
//    @Size(min = 5, max = 25, message = "{password.size}")
    @Column(name = "password")
    private String password;

//    @NotNull
//    @Size(min = 2, max = 30, message = "{firstName.size}")
    @Column(name = "firstName")
    private String firstName;

//    @NotNull
//    @Size(min = 2, max = 30, message = "{lastName.size}")
    @Column(name = "lastName")
    private String lastName;

    @Column(name = "fullname")
    private String fullName;

//    @NotNull
//    @Email
    @Column(name = "email")
    private String email;

    @Column(name = "updatebyemail")
    private boolean updateByEmail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isUpdateByEmail() {
        return updateByEmail;
    }

    public void setUpdateByEmail(boolean updateByEmail) {
        this.updateByEmail = updateByEmail;
    }
}

