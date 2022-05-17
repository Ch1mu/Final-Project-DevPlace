package FinalProject.ApiMessageSystem.Models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MessagePerChat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idMpC;

    @ManyToOne
    @JoinColumn(name = "idChat")
    private Chat chat;

    @OneToOne(cascade = CascadeType.REMOVE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "idMessage")
    private Message message;

    public MessagePerChat(Chat chat, Message message) {
        this.chat = chat;
        this.message = message;
    }
}
