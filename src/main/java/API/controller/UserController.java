package API.controller;

import API.antity.Location;
import API.antity.User;
import API.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserRepo userRepo;
    @PostMapping("/usuarios")
    public ResponseEntity<User> save(@RequestBody User user){
        try {
            return new ResponseEntity<>(userRepo.save(user), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/usuarios")
    public ResponseEntity<List<User>> getAllUsers(){
        try{
            List<User> list = userRepo.findAll();
            if(list.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/usuarios/{id}")
    public ResponseEntity<User> getSingleUser(@PathVariable Long id){
        Optional<User> user = userRepo.findById(id);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("usuarios/enderecoMain/{id}")
    public ResponseEntity<User> getUserMainLocation(@PathVariable Long id){
        Optional<User> userReference = userRepo.findById(id);
        User user = new User();
        if (userReference.isPresent()){
            List<Location> collect = userReference.get().getLocations().stream().filter(Location::isPrincipal).toList();
            user.setId(id);
            user.setNome(userReference.get().getNome());
            user.setNascimento(userReference.get().getNascimento());
            user.setLocations(collect);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("/usuarios/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        try{
            return new ResponseEntity<>(userRepo.save(user), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id){
        try {
            Optional<User> user = userRepo.findById(id);
            user.ifPresent(value -> userRepo.delete(value));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
