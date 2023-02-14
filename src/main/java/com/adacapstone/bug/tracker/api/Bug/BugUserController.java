package com.adacapstone.bug.tracker.api.Bug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:3000")
public class BugUserController {
    @Autowired
    private final BugRepository bugRepository;

    @Autowired
    public BugUserController(BugRepository bugRepository) {
        this.bugRepository = bugRepository;
    }
    //Find all bugs
    @GetMapping("/bugs")
    public List<Bug> getAllBugs() {
        return bugRepository.findAll();
    }
    //get one bug by bug id
    @GetMapping("/bugs/{id}")
    public Bug getBugById(@PathVariable("id") Long id) {
        return bugRepository.findByBugId(id);
    }

//    @GetMapping("/bugs/{id}/users")
//    public ResponseEntity<List<User>> getAllUsersByBugId(@PathVariable(value = "id") Long id) {
//        if(!bugRepository.existsById(id)) {
//            throw new ResourceNotFoundException("Bug with id = " + id + " not found.");
//        }
//        List<User> users = userRepository.findUsersByBugId(id);
//        return new ResponseEntity<>(users, HttpStatus.OK);
//    }

    @PostMapping("/bugs")
    @ResponseStatus(HttpStatus.CREATED)
    public Bug addBug(@RequestBody Bug bug) {
        return bugRepository.save(bug);
    }
    @PutMapping("/bugs/{id}")
    public ResponseEntity<Object> updateBug (@RequestBody Bug bug, @PathVariable Long id) {
        Optional<Bug> bugRepo = Optional.ofNullable(bugRepository.findByBugId(id));
        if(!bugRepo.isPresent()) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
        bug.setBugId(id);
        bugRepository.save(bug);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/bugs/{bugId}")
    public void deleteBug (@PathVariable(value ="bugId") Long bugId) {
        bugRepository.deleteById(bugId);
    }



}

