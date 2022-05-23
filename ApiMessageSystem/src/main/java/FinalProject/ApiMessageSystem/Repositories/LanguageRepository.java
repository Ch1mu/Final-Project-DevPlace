package FinalProject.ApiMessageSystem.Repositories;

import FinalProject.ApiMessageSystem.Models.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {

    public Language getByName(String name);

}
