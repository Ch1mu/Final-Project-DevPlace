package FinalProject.ApiMessageSystem.Repositories;

import FinalProject.ApiMessageSystem.Models.Message;
import FinalProject.ApiMessageSystem.Models.MessagePerChat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessagePerChatRepository extends JpaRepository<MessagePerChat, Long> {
}
