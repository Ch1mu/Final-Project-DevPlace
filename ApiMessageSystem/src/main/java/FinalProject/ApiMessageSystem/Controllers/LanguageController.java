package FinalProject.ApiMessageSystem.Controllers;

import FinalProject.ApiMessageSystem.Models.Language;
import FinalProject.ApiMessageSystem.Services.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/languages")
public class LanguageController {
    @Autowired
    private LanguageService ls;

    @GetMapping("/")
    public ResponseEntity<Object> getAll()
    {
        List<Language> lg = ls.getAll();
        if(lg.isEmpty())
        {
            return ResponseEntity.status(204).body("No languages available.");
        }
        else {
            return ResponseEntity.status(200).body(lg);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") long id)
    {
       Language lg = ls.getById(id);
        if(lg == null)
        {
            return ResponseEntity.status(204).body("Error, language not found.");
        }
        else {
            return ResponseEntity.status(200).body(lg);
        }
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<Object> getByName(@PathVariable("name") String name)
    {
        Language lg = ls.getByName(name);
        if(lg == null)
        {
            return ResponseEntity.status(204).body("Error, language not found.");
        }
        else {
            return ResponseEntity.status(200).body(lg);
        }
    }
}
