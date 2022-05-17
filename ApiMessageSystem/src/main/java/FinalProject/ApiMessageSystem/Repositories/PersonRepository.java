package FinalProject.ApiMessageSystem.Repositories;

import FinalProject.ApiMessageSystem.Models.UserPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<UserPerson, Long> {
    public UserPerson getByUsername(String username);
    public void deleteByDni(String dni);
}