package FinalProject.MvcMessages.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Timestamp;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    private long idMessage;
    @NotEmpty
    private String content;
    private Timestamp date =  new java.sql.Timestamp(new java.util.Date().getTime());


    private UserPerson up;

    public Message(String content)
    {

        this.content = content;
    }

    public Message(UserPerson up) {
        this.up = up;
    }
}
