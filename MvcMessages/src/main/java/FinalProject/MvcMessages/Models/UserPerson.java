package FinalProject.MvcMessages.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;


import javax.validation.constraints.NotEmpty;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPerson {
    @NotEmpty
    private String username;
    @NotEmpty
    private String dni;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private Language language;

}
