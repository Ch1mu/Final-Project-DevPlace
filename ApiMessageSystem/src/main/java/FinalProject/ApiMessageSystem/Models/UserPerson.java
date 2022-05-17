package FinalProject.ApiMessageSystem.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserPerson {

    @Id
    private String dni;
    @NotEmpty
    private String username;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
}
