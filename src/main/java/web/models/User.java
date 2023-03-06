package web.models;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    @NotEmpty(message = "Field must not be empty!.Enter your name! ")
    @Size(min = 2, max = 50, message = "Range from 2 to 50 characters")
    private String firstName;


    @Column(name = "last_name")
    @NotEmpty(message = "Field must not be empty!.Enter your lastName! ")
    @Size(min = 2, max = 50, message = "Range from 2 to 50 characters")
    private String lastName;


    @Column(name = "email")
    @NotEmpty(message = "Field must not be empty!.Enter your email! ")
    @Email(message = "This is not email!")
    private String email;


    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }


    public User() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
