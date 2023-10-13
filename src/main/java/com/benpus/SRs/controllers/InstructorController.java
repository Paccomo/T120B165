package com.benpus.SRs.controllers;

import com.benpus.SRs.DTOs.FirearmDTO;
import com.benpus.SRs.DTOs.InstructorDTO;
import com.benpus.SRs.models.Firearm;
import com.benpus.SRs.models.Instructor;
import com.benpus.SRs.models.ShootingRange;
import com.benpus.SRs.repositories.InstructorRepository;
import com.benpus.SRs.repositories.ShootingRangeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

@RestController
@RequestMapping("api/v1/instructors")
public class InstructorController {
    private final InstructorRepository instructorRepository;
    public InstructorController(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }
    record NewInstructorRequest(String name, String surname, String fk_shootingRange){}
    @Autowired
    private ShootingRangeRepository shootingRangeRepository;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<InstructorDTO>> getInstructors() {
        List<Instructor> instructors = instructorRepository.findAll();
        List<InstructorDTO> instructorsToShow = instructors.stream().map(instructor -> modelMapper.map(instructor, InstructorDTO.class)).toList();
        return ResponseEntity.ok(instructorsToShow);
    }

    @GetMapping("/{instructorId}")
    public ResponseEntity<Object> getInstructor(@PathVariable("instructorId") Integer ID){
        Optional<Instructor> instructorQuery = instructorRepository.findById(ID);
        if (instructorQuery.isEmpty()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Instructor with specified ID was not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } else {
            InstructorDTO instructorToShow = modelMapper.map(instructorQuery.get(), InstructorDTO.class);
            return ResponseEntity.ok(instructorToShow);
        }
    }

    @DeleteMapping("/{instructorId}")
    public ResponseEntity<Object> deleteInstructor(@PathVariable("instructorId") Integer ID)
    {
        Optional<Instructor> instructorQuery = instructorRepository.findById(ID);
        if (instructorQuery.isEmpty()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Instructor with specified ID was not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        instructorRepository.deleteById(ID);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Object> addInstructor(@RequestBody NewInstructorRequest request) {
        String errorMsg = "", name, surname, fk_range;

        name = request.name;
        surname = request.surname;
        fk_range = request.fk_shootingRange;

        errorMsg += this.validateSurname(surname);
        errorMsg += this.validateName(name);
        errorMsg += this.validateFkRange(fk_range);


        if (!errorMsg.isEmpty()){
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("message", errorMsg);
            return ResponseEntity.unprocessableEntity().body(responseBody);
        }

        Integer fk = Integer.parseInt(fk_range);

        Optional<ShootingRange> rangeQuery = shootingRangeRepository.findById(fk);
        ShootingRange range = rangeQuery.get();

        Instructor instructor = new Instructor();
        instructor.setName(name);
        instructor.setSurname(surname);
        instructor.setFk_shootingRange(range);

        instructor = instructorRepository.save(instructor);
        Integer id = instructor.getId();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        InstructorDTO instructorToShow = modelMapper.map(instructor, InstructorDTO.class);
        return ResponseEntity.created(location).body(instructorToShow);
    }

    @PatchMapping("/{instructorId}")
    public ResponseEntity<Object> editInstructor(@PathVariable("instructorId") Integer ID, @RequestBody NewInstructorRequest request){
        Optional<Instructor> instructorQuery = instructorRepository.findById(ID);
        if (instructorQuery.isEmpty()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Instructor with specified ID was not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        Instructor instructor = instructorQuery.get();

        String errorMsg = "", name, surname, fk_range;

        name = request.name;
        surname = request.surname;
        fk_range = request.fk_shootingRange;

        errorMsg += this.validateNullableSurname(surname);
        errorMsg += this.validateNullableName(name);
        errorMsg += this.validateNullableFkRange(fk_range);


        if (!errorMsg.isEmpty()){
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("message", errorMsg);
            return ResponseEntity.unprocessableEntity().body(responseBody);
        }

        if (name != null && !name.isEmpty()) {
            instructor.setName(name);
        }
        if (surname != null && !surname.isEmpty()) {
            instructor.setSurname(surname);
        }
        if (fk_range != null && !fk_range.isEmpty()) {
            Optional<ShootingRange> rangeQuery = shootingRangeRepository.findById(Integer.parseInt(fk_range));
            instructor.setFk_shootingRange(rangeQuery.get());
        }
        instructor = instructorRepository.save(instructor);
        InstructorDTO instructorToShow = modelMapper.map(instructor, InstructorDTO.class);
        return ResponseEntity.ok(instructorToShow);
    }

    private String validateFkRange(String fk_range) {
        if (fk_range == null || fk_range.isEmpty()) {
            return  "Shooting range ID is required; ";
        } else if (!Pattern.matches("^\\d+$", fk_range)){
            return  "Shooting range ID must be a natural number; ";
        }
        Optional<ShootingRange> rangeQuery = shootingRangeRepository.findById(Integer.parseInt(fk_range));
        if (rangeQuery.isEmpty()) {
            return "Shooting range with provided ID was not found";
        }
        return "";
    }

    private String validateNullableFkRange(String fk_range) {
        if (fk_range == null || fk_range.isEmpty()) {
            return  "";
        } else if (Pattern.matches("^\\d+$", fk_range)){
            Optional<ShootingRange> rangeQuery = shootingRangeRepository.findById(Integer.parseInt(fk_range));
            if (rangeQuery.isEmpty()) {
                return "Shooting range with provided ID was not found; ";
            } else {
                return "";
            }
        }
        return "Shooting range ID must be a natural number; ";
    }

    private String validateName(String name) {
        if (name == null || name.isEmpty()) {
            return  "Instructor name is required; ";
        } else if (!Pattern.matches("^[a-zA-ZąčęėįšųūžĄČĘĖĮŠŲŪŽ ]+$", name)){
            return  "Provided name is invalid; ";
        }
        return "";
    }

    private String validateSurname(String surname) {
        if (surname == null || surname.isEmpty()) {
            return  "Instructor surname is required; ";
        } else if (!Pattern.matches("^[a-zA-ZąčęėįšųūžĄČĘĖĮŠŲŪŽ-]+$", surname)){
            return  "Provided surname is invalid; ";
        }
        return "";
    }
    private String validateNullableSurname(String surname) {
        if (surname == null || surname.isEmpty() || Pattern.matches("^[a-zA-ZąčęėįšųūžĄČĘĖĮŠŲŪŽ-]+$", surname)){
            return "";
        } else {
            return  "Provided surname is invalid; ";
        }
    }

    private String validateNullableName(String name) {
        if (name == null || name.isEmpty() || Pattern.matches("^[a-zA-ZąčęėįšųūžĄČĘĖĮŠŲŪŽ ]+$", name)){
            return "";
        } else {
            return  "Provided name is invalid; ";
        }
    }

}
