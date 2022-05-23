package FinalProject.ApiMessageSystem.Repositories;

import FinalProject.ApiMessageSystem.Models.PersonPerChat;
import FinalProject.ApiMessageSystem.Models.UserPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<UserPerson, String> {
    public UserPerson getByUsername(String username);
    public void deleteByUsername(String username);

    @Query(value = "SELECT * FROM user_person p WHERE dni = :dni",nativeQuery = true)
    public UserPerson getByDni(@Param("dni") String dni);
}
