package FinalProject.ApiMessageSystem.Repositories;

import FinalProject.ApiMessageSystem.Models.PersonPerChat;
import FinalProject.ApiMessageSystem.Models.UserPerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonPerChatRepository extends JpaRepository<PersonPerChat, Long> {
}
