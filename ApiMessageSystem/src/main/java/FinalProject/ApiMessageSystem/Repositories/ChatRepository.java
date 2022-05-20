package FinalProject.ApiMessageSystem.Repositories;

import FinalProject.ApiMessageSystem.Models.Chat;
import FinalProject.ApiMessageSystem.Models.PersonPerChat;
import FinalProject.ApiMessageSystem.Models.UserPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

    public Chat getById(long id);
    @Query(value = "SELECT c.id_chat, c.name,c.is_group FROM chat c inner join person_per_chat ppc on ppc.id_chat = c.id_chat where ppc.username = :username GROUP BY c.id_chat", nativeQuery = true)
    public List<Chat> getChatPerUser(@Param("username") String username);

}
