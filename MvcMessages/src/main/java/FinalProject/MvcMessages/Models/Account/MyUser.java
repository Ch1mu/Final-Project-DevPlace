package FinalProject.MvcMessages.Models.Account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = {"username"}))
@NoArgsConstructor
@AllArgsConstructor
public class MyUser {


    @Id
    private String username;
    @NotEmpty
    private String password;

    @ManyToOne()
    @JoinColumn(name = "role_id")
    private Role role;

    //DEFAULT USER CONSTRUCTOR

}
