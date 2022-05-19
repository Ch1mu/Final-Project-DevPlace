package FinalProject.MvcMessages.Models.Adapters;

import FinalProject.MvcMessages.Models.Language;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAdapter {


        @NotEmpty
        private String username;
        @NotEmpty
        private String password;
        @NotEmpty
        private String dni;
        @NotEmpty
        private String firstName;
        @NotEmpty
        private String lastName;
        @NotEmpty
        private Language language;

}
