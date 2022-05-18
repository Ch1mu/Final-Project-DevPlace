package FinalProject.MvcMessages.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonPerChat {

    private long id;
    private Chat chat;
    private UserPerson user;

    public PersonPerChat(Chat chat, UserPerson user) {
        this.chat = chat;
        this.user = user;
    }
}
