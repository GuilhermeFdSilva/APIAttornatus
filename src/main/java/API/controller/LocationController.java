package API.controller;

import API.entity.Location;
import API.repository.LocationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LocationController {
    @Autowired
    LocationRepo locationRepo;
    @PostMapping("/enderecos")
    public ResponseEntity<Location> save(@RequestBody Location location){
        try {
            return new ResponseEntity<>(locationRepo.save(location), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/enderecos")
    public ResponseEntity<List<Location>> getAllUsers(){
        try{
            List<Location> list = locationRepo.findAll();
            if(list.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/enderecos/{id}")
    public ResponseEntity<HttpStatus> deleteLocation(@PathVariable Long id){
        try{
            Optional<Location> location = locationRepo.findById(id);
            location.ifPresent(value -> locationRepo.delete(value));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
