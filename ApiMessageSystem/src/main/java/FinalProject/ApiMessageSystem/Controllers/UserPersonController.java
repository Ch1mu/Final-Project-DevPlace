package FinalProject.ApiMessageSystem.Controllers;

import FinalProject.ApiMessageSystem.Models.UserPerson;
import FinalProject.ApiMessageSystem.Services.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/persons")
public class UserPersonController {

    @Autowired
    PersonService ps;

    @GetMapping("/")
    public ResponseEntity<Object> getAll(){
        List<UserPerson> userPersons = ps.getAll();
        if(userPersons.isEmpty()){
            return ResponseEntity.status(204).body("No user found");
        }
        else {
            return ResponseEntity.status(200).body(userPersons);
        }
    }
    @GetMapping("/dni/{dni}")
    public ResponseEntity<Object> getByDni(@PathVariable("dni") String dni)
    {
        try{
            UserPerson uP = ps.getByDni(dni);
            return ResponseEntity.status(200).body(uP);
        }
        catch (Exception e)
        {
            return ResponseEntity.status(204).body(null);
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<Object> getByUsername(@PathVariable("username") String username){
        UserPerson up = ps.getByUsername(username);
        if (up == null)
            return ResponseEntity.status(204).body("No user found");
        else
            return ResponseEntity.status(200).body(up);
    }


    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody @Valid UserPerson up){
        boolean flag = ps.save(up);
        if(flag)
            return ResponseEntity.status(200).body("Success.");
        else
            return ResponseEntity.status(400).body("Error.");
    }

    @PutMapping("/update/{username}")
    public ResponseEntity<Object> update(@RequestBody @Valid UserPerson up, @PathVariable("username") String username) {
        UserPerson person = ps.update(up, username);
        if (person == null)
            return ResponseEntity.status(204).body("No user found");
        else
            return ResponseEntity.status(200).body(person);

    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<Object> delete(@PathVariable("username") String username){
        try{
            boolean flag = ps.delete(username);
            if(flag){
                return ResponseEntity.status(200).body("Success.");
            } else {
                return ResponseEntity.status(204).body("No username found with the specified Username.");
            }
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error.");
        }
    }




}