package FinalProject.ApiMessageSystem.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserPerson {

    @Id
    private String username;
    private String password;


}
