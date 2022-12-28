package springwithmysql.com.springmysql.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springwithmysql.com.springmysql.Exception.UserNotFoundException;
import springwithmysql.com.springmysql.Models.Users;
import springwithmysql.com.springmysql.Repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserRepository userRepo;

    @GetMapping("/getUsers")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<List<Users>> getAllUsers(){
        return ResponseEntity.ok(userRepo.findAll());
    }

    @PostMapping("/addUser")
    @CrossOrigin("http://localhost:3000")
   public Users addUsers(@RequestBody Users user){
        return userRepo.save(user);
    }


//   Get user by ID
    @GetMapping("/users/{id}")
    @CrossOrigin("http://localhost:3000")
    Users getUserById(@PathVariable Long id){
        return userRepo.findById(id).orElseThrow(()->new UserNotFoundException(id));

    }
//Update users
    @PutMapping("/update/users/{id}")
    @CrossOrigin("http://localhost:3000")
    Users updateUsers(@RequestBody Users newUser, @PathVariable Long id){
        return userRepo.findById(id).map(users -> {
            users.setUsername(newUser.getUsername());
            users.setName(newUser.getName());
            users.setEmail(newUser.getEmail());

            return userRepo.save(users);
        }).orElseThrow(()->new UserNotFoundException(id));
    }
//Delete user method
    @DeleteMapping("/delete/user/{id}")
    @CrossOrigin("http://localhost:3000")
    String deleteUser(@PathVariable Long id){
        if(!userRepo.existsById(id)){
            throw new UserNotFoundException(id);

        }
        userRepo.deleteById(id);
        return "User with id " +id+ " has been deleted sucessfully. ";
    }


}
