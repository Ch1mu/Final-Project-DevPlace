package FinalProject.MvcMessages.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.sql.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private long idMessage;
    @NotEmpty
    private String content;
    @NotEmpty
    private Date date;
    @NotEmpty
    private String username;



}
