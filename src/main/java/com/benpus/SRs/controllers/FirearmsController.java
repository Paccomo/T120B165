package com.benpus.SRs.controllers;

import com.benpus.SRs.DTOs.CompanyDTO;
import com.benpus.SRs.DTOs.FirearmDTO;
import com.benpus.SRs.models.*;
import com.benpus.SRs.repositories.FirearmRepository;
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
@RequestMapping("api/v1/firearms")
public class FirearmsController {
    private final FirearmRepository firearmRepository;
    public FirearmsController(FirearmRepository firearmRepository){ this.firearmRepository = firearmRepository; }
    record NewFirearmRequest(String manufacturer, String model, String caliber, String picture, String price, String fk_shootingRange){}
    @Autowired
    private ShootingRangeRepository shootingRangeRepository;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<FirearmDTO>> getFirearms() {
        List<Firearm> firearms = firearmRepository.findAll();
        List<FirearmDTO> firearmsToShow = firearms.stream().map(firearm -> modelMapper.map(firearm, FirearmDTO.class)).toList();
        return ResponseEntity.ok(firearmsToShow);
    }

    @GetMapping("/{firearmId}")
    public ResponseEntity<Object> getUser(@PathVariable("firearmId") Integer ID){
        Optional<Firearm> firearmQuery = firearmRepository.findById(ID);
        if (firearmQuery.isEmpty()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Firearm with specified ID was not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } else {
            FirearmDTO firearmToShow = modelMapper.map(firearmQuery.get(), FirearmDTO.class);
            return ResponseEntity.ok(firearmToShow);
        }
    }

    @DeleteMapping("/{firearmId}")
    public ResponseEntity<Object> deleteFirearm(@PathVariable("firearmId") Integer ID)
    {
        Optional<Firearm> firearmQuery = firearmRepository.findById(ID);
        if (firearmQuery.isEmpty()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Firearm with specified ID was not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        firearmRepository.deleteById(ID);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Object> addFirearm(@RequestBody NewFirearmRequest request) {
        String errorMsg = "", manufacturer, model, caliber, picture, price, fk_range;

        manufacturer = request.manufacturer;
        model = request.model;
        caliber = request.caliber;
        picture = request.picture;
        price = request.price;
        fk_range = request.fk_shootingRange;

        errorMsg += this.validateManufacturer(manufacturer);
        errorMsg += this.validateModel(model);
        errorMsg += this.validateCaliber(caliber);
        errorMsg += this.validatePrice(price);
        errorMsg += this.validateFkRange(fk_range);


        if (!errorMsg.isEmpty()){
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("message", errorMsg);
            return ResponseEntity.unprocessableEntity().body(responseBody);
        }

        Integer fk = Integer.parseInt(fk_range);

        Optional<ShootingRange> rangeQuery = shootingRangeRepository.findById(fk);
        ShootingRange range = rangeQuery.get();

        Firearm firearm = new Firearm();
        firearm.setManufacturer(manufacturer);
        firearm.setModel(model);
        firearm.setCaliber(caliber);
        firearm.setPicture(picture);
        firearm.setPrice(Double.parseDouble(price));
        firearm.setFk_shootingRange(range);

        firearm = firearmRepository.save(firearm);
        Integer id = firearm.getId();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        FirearmDTO firearmToShow = modelMapper.map(firearm, FirearmDTO.class);
        return ResponseEntity.created(location).body(firearmToShow);
    }

    @PatchMapping("/{firearmId}")
    public ResponseEntity<Object> editFirearm(@PathVariable("firearmId") Integer ID, @RequestBody NewFirearmRequest request){
        Optional<Firearm> firearmQuery = firearmRepository.findById(ID);
        if (firearmQuery.isEmpty()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Firearm with specified ID was not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        Firearm firearm = firearmQuery.get();

        String errorMsg = "", manufacturer, model, caliber, picture, price, fk_range;

        manufacturer = request.manufacturer;
        model = request.model;
        caliber = request.caliber;
        picture = request.picture;
        price = request.price;
        fk_range = request.fk_shootingRange;

        errorMsg += this.validateNullableManufacturer(manufacturer);
        errorMsg += this.validateNullableCaliber(caliber);
        errorMsg += this.validateNullablePrice(price);
        errorMsg += this.validateNullableFkRange(fk_range);

        if (!errorMsg.isEmpty()){
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("message", errorMsg);
            return ResponseEntity.unprocessableEntity().body(responseBody);
        }

        if (manufacturer != null && !manufacturer.isEmpty()) {
            firearm.setManufacturer(manufacturer);
        }
        if (model != null && !model.isEmpty()) {
            firearm.setModel(model);
        }
        if (caliber != null && !caliber.isEmpty()) {
            firearm.setCaliber(caliber);
        }
        if (picture != null && !picture.isEmpty()) {
            firearm.setPicture(picture);
        }
        if (price != null && !price.isEmpty()) {
            firearm.setPrice(Double.parseDouble(price));
        }
        if (fk_range != null && !fk_range.isEmpty()) {
            Optional<ShootingRange> rangeQuery = shootingRangeRepository.findById(Integer.parseInt(fk_range));
            firearm.setFk_shootingRange(rangeQuery.get());
        }
        firearm = firearmRepository.save(firearm);
        FirearmDTO firearmToShow = modelMapper.map(firearm, FirearmDTO.class);
        return ResponseEntity.ok(firearmToShow);
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

    private String validateNullablePrice(String price) {
        if (price == null || price.isEmpty() || Pattern.matches("^\\d+(.\\d{1,2})?$", price)){
            return "";
        }
        return "Price must be a number. Either natural or positive with no more than two numbers after period `.`; ";
    }

    private String validateNullableCaliber(String caliber) {
        if (caliber == null || caliber.isEmpty() || caliber.matches(".*\\d.*")) {
            return "";
        }
        return  "Provided caliber is invalid; ";
    }

    private String validateNullableManufacturer(String manufacturer) {
        if (manufacturer == null || manufacturer.isEmpty() || Pattern.matches("^[a-zA-Zą&čęėįšųūžĄČĘĖĮŠŲŪŽ .\\d-]+$", manufacturer)) {
            return "";
        }
        return "Provided manufacturer is invalid; ";
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

    private String validatePrice(String price) {
        if (price == null || price.isEmpty()) {
            return  "Price is required; ";
        } else if (!Pattern.matches("^\\d+(.\\d{1,2})?$", price)){
            return  "Price must be a number. Either natural or positive with no more than two numbers after period `.`; ";
        }
        return "";
    }

    private String validateCaliber(String caliber) {
        if (caliber == null || caliber.isEmpty()) {
            return  "Firearm manufacturer is required; ";
        } else if (!caliber.matches(".*\\d.*")){
            return  "Provided caliber is invalid; ";
        }
        return "";
    }

    private String validateModel(String model) {
        if (model == null || model.isEmpty()) {
            return  "Firearm model is required; ";
        }
        return "";
    }

    private String validateManufacturer(String manufacturer) {
        if (manufacturer == null || manufacturer.isEmpty()) {
            return  "Firearm manufacturer is required; ";
        } else if (!Pattern.matches("^[a-zA-Ząčęėįšųū&žĄČĘĖĮŠŲŪŽ .\\d-]+$", manufacturer)){
            return  "Provided manufacturer is invalid; ";
        }
        return "";
    }

}
