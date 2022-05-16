package FinalProject.ApiMessageSystem.Models;

import javax.persistence.*;

@Entity
public class PersonPerChat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "idChat")
    private Chat chat;

    @ManyToOne
    @JoinColumn(name = "username")
    private UserPerson user;


}
