package FinalProject.ApiMessageSystem.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.sql.Date;

@Entity
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idMessage;
    @NotEmpty
    private String content;
    private Date date;

    @OneToMany
    @JoinColumn(name = "dni")
    private UserPerson uP;

    public Message(String content)
    {
        this.content = content;
        this.date = new Date(System.currentTimeMillis());
    }

}
