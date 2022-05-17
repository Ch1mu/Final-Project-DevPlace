package FinalProject.ApiMessageSystem.Repositories;

import FinalProject.ApiMessageSystem.Models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query(value = "SELECT m.id_message, m.content, m.username, m.date FROM message m inner join message_per_chat mpc on mpc.id_chat = :chatId where m.username = :username  GROUP BY m.id_message ORDER BY date asc", nativeQuery = true)
  public List<Message> getByUserAndChat(@Param("username") String username, @Param("chatId") long chatId);

    @Query(value = "SELECT m.id_message, m.content, m.username, m.date FROM message m inner join message_per_chat mpc on mpc.id_chat = :chatId  GROUP BY m.id_message ORDER BY date asc", nativeQuery = true)
    public List<Message> getByChat(@Param("chatId") long chatId);
}
