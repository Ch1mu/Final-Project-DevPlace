package FinalProject.MvcMessages.Controllers.Repositories;


import FinalProject.MvcMessages.Models.Account.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<MyUser, Long> {
    public MyUser getByUsername(String username);
}
