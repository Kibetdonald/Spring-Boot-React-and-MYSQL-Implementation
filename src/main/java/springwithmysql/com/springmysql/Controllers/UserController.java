package springwithmysql.com.springmysql.Controllers;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springwithmysql.com.springmysql.Models.Users;
import springwithmysql.com.springmysql.Repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserRepository userRepo;

    @GetMapping("/getUsers")
    public ResponseEntity<List<Users>> getAllUsers(){
        return ResponseEntity.ok(userRepo.findAll());
    }

    @PostMapping("/addUser")
   public Users addUsers(@RequestBody Users user){
        return userRepo.save(user);
    }

}
