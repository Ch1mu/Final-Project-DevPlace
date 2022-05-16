package FinalProject.ApiMessageSystem.Models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @OneToOne
    @JoinColumn(name = "idMessage")
    private Message message;
}
