package FinalProject.ApiMessageSystem.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserPerson {

    @Id
    private String username;
    @NotEmpty
    @Column(unique=true)
    private String dni;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "id")
    private Language language;
}
