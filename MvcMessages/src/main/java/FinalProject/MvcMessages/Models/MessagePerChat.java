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
public class MessagePerChat {

    private long idMpC;
    private Chat chat;
    private Message message;
    public MessagePerChat(Chat chat, Message message) {
        this.chat = chat;
        this.message = message;
    }
}
