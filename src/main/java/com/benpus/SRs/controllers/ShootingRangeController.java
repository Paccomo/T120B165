package com.benpus.SRs.controllers;

import com.benpus.SRs.DTOs.ShootingRangeDTO;
import com.benpus.SRs.models.*;
import com.benpus.SRs.repositories.BusinessHoursRepository;
import com.benpus.SRs.repositories.CompanyRepository;
import com.benpus.SRs.repositories.ShootingRangeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

@RestController
@RequestMapping("api/v1/shootingRanges")
public class ShootingRangeController {
    private final ShootingRangeRepository shootingRangeRepository;
    public ShootingRangeController(ShootingRangeRepository shootingRangeRepository) {
        this.shootingRangeRepository = shootingRangeRepository;
    }
    record NewShootingRangeRequest(String address, String city, String isOutdoor, String price, String maxShooters, String fk_company, String tueOpen, String tueClose, String wedOpen, String wedClose, String thurOpen, String thurClose, String friOpen, String friClose, String satOpen, String satClose, String sunOpen, String sunClose, String monOpen, String monClose){}
    @Autowired
    private BusinessHoursRepository  businessHoursRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private ModelMapper modelMapper;
    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping
    public ResponseEntity<List<ShootingRangeDTO>> getShootingRanges() {
        List<ShootingRange> ranges = shootingRangeRepository.findAll();
        List<ShootingRangeDTO> rangesToShow = ranges.stream().map(range -> modelMapper.map(range, ShootingRangeDTO.class)).toList();
        return ResponseEntity.ok(rangesToShow);
    }

    @DeleteMapping("/{rangeId}")
    public ResponseEntity<Object> deleteUser(@PathVariable("rangeId") Integer ID)
    {
        Optional<ShootingRange> rangeQuery = shootingRangeRepository.findById(ID);
        if (rangeQuery.isEmpty()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Shooting range with specified ID was not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        shootingRangeRepository.deleteById(ID);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{rangeId}")
    public ResponseEntity<Object> getShootingRange(@PathVariable("rangeId") Integer ID){
        Optional<ShootingRange> rangeQuery = shootingRangeRepository.findById(ID);
        if (rangeQuery.isEmpty()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Shooting range with specified ID was not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } else {
            ShootingRangeDTO rangeToShow = modelMapper.map(rangeQuery.get(), ShootingRangeDTO.class);
            return ResponseEntity.ok(rangeToShow);
        }
    }

    @PostMapping
    public ResponseEntity<Object> addShootingRanges(@RequestBody NewShootingRangeRequest request) {
        String errorMsg = "", address, city, isOutdoor, price, maxShooters, fk_company, monOpen, monClose, tueOpen, tueClose, wedOpen, wedClose, thurOpen, thurClose, friOpen, friClose, satOpen, satClose, sunOpen, sunClose;

        address =  request.address;
        city = request.city;
        isOutdoor = request.isOutdoor;
        price = request.price;
        maxShooters = request.maxShooters;
        fk_company = request.fk_company;
        monOpen = request.monOpen;
        monClose = request.monClose;
        tueOpen = request.tueOpen;
        tueClose = request.tueClose;
        wedOpen = request.wedOpen;
        wedClose = request.wedClose;
        thurOpen = request.thurOpen;
        thurClose = request.thurClose;
        friOpen = request.friOpen;
        friClose = request.friClose;
        satOpen = request.satOpen;
        satClose = request.satClose;
        sunOpen = request.sunOpen;
        sunClose = request.sunClose;

        errorMsg += this.validateNullableTime(monOpen, monClose, "Monday ");
        errorMsg += this.validateNullableTime(tueOpen, tueClose, "Tuesday ");
        errorMsg += this.validateNullableTime(wedOpen, wedClose, "Wednesday ");
        errorMsg += this.validateNullableTime(thurOpen, thurClose, "Thursday ");
        errorMsg += this.validateNullableTime(friOpen, friClose, "Friday ");
        errorMsg += this.validateNullableTime(satOpen, satClose, "Saturday ");
        errorMsg += this.validateNullableTime(sunOpen, sunClose, "Sunday ");
        errorMsg += this.validateAddress(address);
        errorMsg += this.validateCity(city);
        errorMsg += this.validateMaxShooters(maxShooters);
        errorMsg += this.validateFkCompany(fk_company);
        errorMsg += this.validatePrice(price);

        if (!errorMsg.isEmpty()){
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("message", errorMsg);
            return ResponseEntity.unprocessableEntity().body(responseBody);
        }

        String jpql = "SELECT p FROM BusinessHours p WHERE p.monOpen = :monOpen AND p.monClose = :monClose AND p.tueOpen = :tueOpen AND p.tueClose = :tueClose AND p.wedOpen = :wedOpen AND p.wedClose = :wedClose AND p.thurOpen = :thurOpen AND p.thurClose = :thurClose AND p.friOpen = :friOpen AND p.friClose = :friClose AND p.satOpen = :satOpen AND p.satClose = :satClose AND p.sunOpen = :sunOpen AND p.sunClose = :sunClose";
        TypedQuery<BusinessHours> query = entityManager.createQuery(jpql, BusinessHours.class);
        query.setParameter("monOpen", monOpen == null || monOpen.isEmpty()? null : LocalTime.parse(monOpen));
        query.setParameter("monClose", monClose == null || monClose.isEmpty()? null : LocalTime.parse(monClose));
        query.setParameter("tueOpen", tueOpen == null || tueOpen.isEmpty()? null : LocalTime.parse(tueOpen));
        query.setParameter("tueClose", tueClose == null || tueClose.isEmpty()? null : LocalTime.parse(tueClose));
        query.setParameter("wedOpen", wedOpen == null || wedOpen.isEmpty()? null : LocalTime.parse(wedOpen));
        query.setParameter("wedClose", wedClose == null || wedClose.isEmpty()? null : LocalTime.parse(wedClose));
        query.setParameter("thurOpen", thurOpen == null || thurOpen.isEmpty()? null : LocalTime.parse(thurOpen));
        query.setParameter("thurClose", thurClose == null || thurClose.isEmpty()? null : LocalTime.parse(thurClose));
        query.setParameter("friOpen", friOpen == null || friOpen.isEmpty()? null : LocalTime.parse(friOpen));
        query.setParameter("friClose", friClose == null || friClose.isEmpty()? null : LocalTime.parse(friClose));
        query.setParameter("satOpen", satOpen == null || satOpen.isEmpty()? null : LocalTime.parse(satOpen));
        query.setParameter("satClose", satClose == null || satClose.isEmpty()? null : LocalTime.parse(satClose));
        query.setParameter("sunOpen", sunOpen == null || sunOpen.isEmpty()? null : LocalTime.parse(sunOpen));
        query.setParameter("sunClose", sunClose == null || sunClose.isEmpty()? null : LocalTime.parse(sunClose));
        List<BusinessHours> timetable = query.getResultList();

        int fkHours;
        if (timetable.isEmpty()) {
            BusinessHours hours = new BusinessHours();
            hours.setMonOpen(monOpen == null || monOpen.isEmpty()? null : LocalTime.parse(monOpen));
            hours.setMonClose(monClose == null || monClose.isEmpty()? null : LocalTime.parse(monClose));
            hours.setTueOpen(tueOpen == null || tueOpen.isEmpty()? null : LocalTime.parse(tueOpen));
            hours.setTueClose(tueClose == null || tueClose.isEmpty()? null : LocalTime.parse(tueClose));
            hours.setWedOpen(wedOpen == null || wedOpen.isEmpty()? null : LocalTime.parse(wedOpen));
            hours.setWedClose(wedClose == null || wedClose.isEmpty()? null : LocalTime.parse(wedClose));
            hours.setThurOpen(thurOpen == null || thurOpen.isEmpty()? null : LocalTime.parse(thurOpen));
            hours.setThurClose(thurClose == null || thurClose.isEmpty()? null : LocalTime.parse(thurClose));
            hours.setFriOpen(friOpen == null || friOpen.isEmpty()? null : LocalTime.parse(friOpen));
            hours.setFriClose(friClose == null || friClose.isEmpty()? null : LocalTime.parse(friClose));
            hours.setSatOpen(satOpen == null || satOpen.isEmpty()? null : LocalTime.parse(satOpen));
            hours.setSatClose(satClose == null || satClose.isEmpty()? null : LocalTime.parse(satClose));
            hours.setSunOpen(sunOpen == null || sunOpen.isEmpty()? null : LocalTime.parse(sunOpen));
            hours.setSunClose(sunClose == null || sunClose.isEmpty()? null : LocalTime.parse(sunClose));
            hours = businessHoursRepository.save(hours);
            fkHours = hours.getId();
        } else {
            fkHours = timetable.get(0).getId();
        }

        ShootingRange shootingRange = new ShootingRange();
        shootingRange.setAddress(address);
        shootingRange.setCity(city);
        shootingRange.setMaxShooters(Integer.parseInt(maxShooters));
        shootingRange.setOutdoor(Boolean.parseBoolean(isOutdoor));
        shootingRange.setPrice(Double.parseDouble(price));
        shootingRange.setFk_company(companyRepository.findById(Integer.parseInt(fk_company)).get());
        shootingRange.setFk_hours(businessHoursRepository.findById(fkHours).get());

        shootingRange = shootingRangeRepository.save(shootingRange);
        Integer id = shootingRange.getId();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        ShootingRangeDTO rangeToShow = modelMapper.map(shootingRange, ShootingRangeDTO.class);
        return ResponseEntity.created(location).body(rangeToShow);
    }

    //TODO delete unused hours, fix hours DB, optional manufacturers for firearms, list requests
    @PutMapping("/{rangeId}")
    public ResponseEntity<Object> editShootingRange(@PathVariable("rangeId") Integer ID, @RequestBody NewShootingRangeRequest request){
        Optional<ShootingRange> rangeQuery = shootingRangeRepository.findById(ID);
        if (rangeQuery.isEmpty()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Shooting range with specified ID was not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        ShootingRange shootingRange = rangeQuery.get();

        String errorMsg = "", address, city, isOutdoor, price, maxShooters, fk_company, monOpen, monClose, tueOpen, tueClose, wedOpen, wedClose, thurOpen, thurClose, friOpen, friClose, satOpen, satClose, sunOpen, sunClose;

        address =  request.address;
        city = request.city;
        isOutdoor = request.isOutdoor;
        price = request.price;
        maxShooters = request.maxShooters;
        fk_company = request.fk_company;
        monOpen = request.monOpen;
        monClose = request.monClose;
        tueOpen = request.tueOpen;
        tueClose = request.tueClose;
        wedOpen = request.wedOpen;
        wedClose = request.wedClose;
        thurOpen = request.thurOpen;
        thurClose = request.thurClose;
        friOpen = request.friOpen;
        friClose = request.friClose;
        satOpen = request.satOpen;
        satClose = request.satClose;
        sunOpen = request.sunOpen;
        sunClose = request.sunClose;

        errorMsg += this.validateNullableTime(monOpen, monClose, "Monday ");
        errorMsg += this.validateNullableTime(tueOpen, tueClose, "Tuesday ");
        errorMsg += this.validateNullableTime(wedOpen, wedClose, "Wednesday ");
        errorMsg += this.validateNullableTime(thurOpen, thurClose, "Thursday ");
        errorMsg += this.validateNullableTime(friOpen, friClose, "Friday ");
        errorMsg += this.validateNullableTime(satOpen, satClose, "Saturday ");
        errorMsg += this.validateNullableTime(sunOpen, sunClose, "Sunday ");
        errorMsg += this.validateNullableAddress(address);
        errorMsg += this.validateNullableCity(city);
        errorMsg += this.validateNullableMaxShooters(maxShooters);
        errorMsg += this.validateNullableFkCompany(fk_company);
        errorMsg += this.validateNullablePrice(price);

        if (!errorMsg.isEmpty()){
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("message", errorMsg);
            return ResponseEntity.unprocessableEntity().body(responseBody);
        }

        BusinessHours oldTimetable = shootingRange.getFk_hours();
        BusinessHours newTimetable = new BusinessHours();
        if (monOpen != null) {
            newTimetable.setMonOpen(monOpen.isEmpty()? null : LocalTime.parse(monOpen));
        } else {
            newTimetable.setMonOpen(oldTimetable.getMonOpen());
        }
        if (monClose != null) {
            newTimetable.setMonClose(monClose.isEmpty()? null : LocalTime.parse(monClose));
        } else {
            newTimetable.setMonClose(oldTimetable.getMonClose());
        }
        if (tueOpen != null) {
            newTimetable.setTueOpen(tueOpen.isEmpty()? null : LocalTime.parse(tueOpen));
        } else {
            newTimetable.setTueOpen(oldTimetable.getTueOpen());
        }
        if (tueClose != null) {
            newTimetable.setTueClose(tueClose.isEmpty()? null : LocalTime.parse(tueClose));
        } else {
            newTimetable.setTueClose(oldTimetable.getTueClose());
        }
        if (wedOpen != null) {
            newTimetable.setWedOpen(wedOpen.isEmpty()? null : LocalTime.parse(wedOpen));
        } else {
            newTimetable.setWedOpen(oldTimetable.getWedOpen());
        }
        if (wedClose != null) {
            newTimetable.setWedClose(wedClose.isEmpty()? null : LocalTime.parse(wedClose));
        } else {
            newTimetable.setWedClose(oldTimetable.getWedClose());
        }
        if (thurOpen != null) {
            newTimetable.setThurOpen(thurOpen.isEmpty()? null : LocalTime.parse(thurOpen));
        } else {
            newTimetable.setThurOpen(oldTimetable.getThurOpen());
        }
        if (thurClose != null) {
            newTimetable.setThurClose(thurClose.isEmpty()? null : LocalTime.parse(thurClose));
        } else {
            newTimetable.setThurClose(oldTimetable.getThurClose());
        }
        if (friOpen != null) {
            newTimetable.setFriOpen(friOpen.isEmpty()? null : LocalTime.parse(friOpen));
        } else {
            newTimetable.setFriOpen(oldTimetable.getFriOpen());
        }
        if (friClose != null) {
            newTimetable.setFriClose(friClose.isEmpty()? null : LocalTime.parse(friClose));
        } else {
            newTimetable.setFriClose(oldTimetable.getFriClose());
        }
        if (satOpen != null) {
            newTimetable.setSatOpen(satOpen.isEmpty()? null : LocalTime.parse(satOpen));
        } else {
            newTimetable.setSatOpen(oldTimetable.getSatOpen());
        }
        if (satClose != null) {
            newTimetable.setSatClose(satClose.isEmpty()? null : LocalTime.parse(satClose));
        } else {
            newTimetable.setSatClose(oldTimetable.getSatClose());
        }
        if (sunOpen != null) {
            newTimetable.setSunOpen(sunOpen.isEmpty()? null : LocalTime.parse(sunOpen));
        } else {
            newTimetable.setSunOpen(oldTimetable.getSunOpen());
        }
        if (sunClose != null) {
            newTimetable.setSunClose(sunClose.isEmpty()? null : LocalTime.parse(sunClose));
        } else {
            newTimetable.setSunClose(oldTimetable.getSunClose());
        }

        if (!newTimetable.equals(oldTimetable)){
            String jpql = "SELECT p FROM BusinessHours p WHERE p.monOpen = :monOpen AND p.monClose = :monClose AND p.tueOpen = :tueOpen AND p.tueClose = :tueClose AND p.wedOpen = :wedOpen AND p.wedClose = :wedClose AND p.thurOpen = :thurOpen AND p.thurClose = :thurClose AND p.friOpen = :friOpen AND p.friClose = :friClose AND p.satOpen = :satOpen AND p.satClose = :satClose AND p.sunOpen = :sunOpen AND p.sunClose = :sunClose";
            TypedQuery<BusinessHours> query = entityManager.createQuery(jpql, BusinessHours.class);
            query.setParameter("monOpen", newTimetable.getMonOpen() == null ? null : newTimetable.getMonOpen());
            query.setParameter("monClose", newTimetable.getMonClose() == null ? null : newTimetable.getMonClose());
            query.setParameter("tueOpen", newTimetable.getTueOpen() == null ? null : newTimetable.getTueOpen());
            query.setParameter("tueClose", newTimetable.getTueClose() == null ? null : newTimetable.getTueClose());
            query.setParameter("wedOpen", newTimetable.getWedOpen() == null ? null : newTimetable.getWedOpen());
            query.setParameter("wedClose", newTimetable.getWedClose() == null ? null : newTimetable.getWedClose());
            query.setParameter("thurOpen", newTimetable.getThurOpen() == null ? null : newTimetable.getThurOpen());
            query.setParameter("thurClose", newTimetable.getThurClose() == null ? null : newTimetable.getThurClose());
            query.setParameter("friOpen", newTimetable.getFriOpen() == null ? null : newTimetable.getFriOpen());
            query.setParameter("friClose", newTimetable.getFriClose() == null ? null : newTimetable.getFriClose());
            query.setParameter("satOpen", newTimetable.getSatOpen() == null ? null : newTimetable.getSatOpen());
            query.setParameter("satClose", newTimetable.getSatClose() == null ? null : newTimetable.getSatClose());
            query.setParameter("sunOpen", newTimetable.getSunOpen() == null ? null : newTimetable.getSunOpen());
            query.setParameter("sunClose", newTimetable.getSunClose() == null ? null : newTimetable.getSunClose());
            List<BusinessHours> timetable = query.getResultList();

            int fkHours;
            if (timetable.isEmpty()) {
                BusinessHours hours = new BusinessHours();
                hours.setMonOpen(newTimetable.getMonOpen() == null ? null : newTimetable.getMonOpen());
                hours.setMonClose(newTimetable.getMonClose() == null ? null : newTimetable.getMonClose());
                hours.setTueOpen(newTimetable.getTueOpen() == null ? null : newTimetable.getTueOpen());
                hours.setTueClose(newTimetable.getTueClose() == null ? null : newTimetable.getTueClose());
                hours.setWedOpen(newTimetable.getWedOpen() == null ? null : newTimetable.getWedOpen());
                hours.setWedClose(newTimetable.getWedClose() == null ? null : newTimetable.getWedClose());
                hours.setThurOpen(newTimetable.getThurOpen() == null ? null : newTimetable.getThurOpen());
                hours.setThurClose(newTimetable.getThurClose() == null ? null : newTimetable.getThurClose());
                hours.setFriOpen(newTimetable.getFriOpen() == null ? null : newTimetable.getFriOpen());
                hours.setFriClose(newTimetable.getFriClose() == null ? null : newTimetable.getFriClose());
                hours.setSatOpen(newTimetable.getSatOpen() == null ? null : newTimetable.getSatOpen());
                hours.setSatClose(newTimetable.getSatClose() == null ? null : newTimetable.getSatClose());
                hours.setSunOpen(newTimetable.getSunOpen() == null ? null : newTimetable.getSunOpen());
                hours.setSunClose(newTimetable.getSunClose() == null ? null : newTimetable.getSunClose());
                hours = businessHoursRepository.save(hours);
                fkHours = hours.getId();
            } else {
                fkHours = timetable.get(0).getId();
            }
            shootingRange.setFk_hours(businessHoursRepository.findById(fkHours).get());
        }

        if (address != null && !address.isEmpty()){
            shootingRange.setAddress(address);
        }
        if (city != null && !city.isEmpty()){
            shootingRange.setCity(city);
        }
        if (isOutdoor != null && !isOutdoor.isEmpty()) {
            shootingRange.setOutdoor(Boolean.parseBoolean(isOutdoor));
        }
        if (price != null && !price.isEmpty()) {
            shootingRange.setPrice(Double.parseDouble(price));
        }
        if (maxShooters != null && !maxShooters.isEmpty()) {
            shootingRange.setMaxShooters(Integer.parseInt(maxShooters));
        }
        if (fk_company != null && !fk_company.isEmpty()) {
            Optional<Company> companyQuery = companyRepository.findById(Integer.parseInt(fk_company));
            shootingRange.setFk_company(companyQuery.get());
        }
        shootingRange = shootingRangeRepository.save(shootingRange);
        ShootingRangeDTO rangeToShow = modelMapper.map(shootingRange, ShootingRangeDTO.class);
        return ResponseEntity.ok(rangeToShow);
    }

    private String validateNullableAddress(String address) {
        if (address == null || address.isEmpty() || Pattern.matches("^[a-zA-ZąčęėįšųūžĄČĘĖĮŠŲŪŽ .\\d-]+$", address)) {
            return "";
        }
        return  "Provided address is invalid; ";
    }

    private String validateNullableCity(String city) {
        if (city == null || city.isEmpty() || Pattern.matches("^[a-zA-ZąčęėįšųūžĄČĘĖĮŠŲŪŽ ]+$", city)) {
            return "";
        }
        return  "Provided city name is invalid; ";
    }

    private String validateNullableFkCompany(String fk_company) {
        if (fk_company == null || fk_company.isEmpty()) {
            return  "";
        } else if (Pattern.matches("^\\d+$", fk_company)){
            Optional<Company> companyQuery = companyRepository.findById(Integer.parseInt(fk_company));
            if (companyQuery.isEmpty()) {
                return "Company with provided ID was not found; ";
            } else if (!companyQuery.get().getApproved()) {
                return "Only approved companies can own shooting ranges; ";
            } else {
                return "";
            }
        }
        return  "Company ID must be a natural number; ";
    }

    private String validateNullableMaxShooters(String maxShooters) {
        if (maxShooters == null || maxShooters.isEmpty() || Pattern.matches("^\\d+$", maxShooters)) {
            return "";
        }
        return  "Maximum amount of shooters at once field must be a natural number; ";
    }

    private String validateNullablePrice(String price) {
        if (price == null || price.isEmpty() || Pattern.matches("^\\d+(.\\d{1,2})?$", price)) {
            return "";
        }
        return  "Price must be a number. Either natural or positive with no more than two numbers after period `.`; ";
    }

    private String validatePrice(String price) {
        if (price == null || price.isEmpty()) {
            return  "Price is required; ";
        } else if (!Pattern.matches("^\\d+(.\\d{1,2})?$", price)){
            return  "Price must be a number. Either natural or positive with no more than two numbers after period `.`; ";
        }
        return "";
    }

    private String validateFkCompany(String fk_company) {
        if (fk_company == null || fk_company.isEmpty()) {
            return  "Company ID is required; ";
        } else if (!Pattern.matches("^\\d+$", fk_company)){
            return  "Company ID must be a natural number; ";
        }
        Optional<Company> companyQuery = companyRepository.findById(Integer.parseInt(fk_company));
        if (companyQuery.isEmpty()) {
            return "Company with provided ID was not found; ";
        } else if (!companyQuery.get().getApproved()) {
            return "Only approved companies can own shooting ranges; ";
        }
        return "";
    }

    private String validateMaxShooters(String maxShooters) {
        if (maxShooters == null || maxShooters.isEmpty()) {
            return  "'Maximum amount of shooters at once' field is required; ";
        } else if (!Pattern.matches("^\\d+$", maxShooters)){
            return  "Maximum amount of shooters at once field must be a natural number; ";
        }
        return "";
    }

    private String validateNullableTime(String open, String close, String textStart) {
        if ((open == null || open.isEmpty()) && (close == null || close.isEmpty())) {
            return "";
        } else if (open != null && !open.isEmpty() && close != null && !close.isEmpty()) {
            if (!Pattern.matches("^(\\d|0\\d|1\\d|2[0-3]):[0-5]\\d$", open)){
                return textStart + "opening hours are formed incorrectly. Time must be entered as 'HH:MM' 24-hour format; ";
            } else if (!Pattern.matches("^(\\d|0\\d|1\\d|2[0-3]):[0-5]\\d$", close)) {
                return textStart + "closing hours are formed incorrectly. Time must be entered as 'HH:MM' 24-hour format; ";
            } else {
                LocalTime start, end;
                try {
                    start = LocalTime.parse(open);
                } catch (DateTimeParseException ex)  {
                    return textStart + "opening hours are invalid; ";
                }
                try {
                    end = LocalTime.parse(close);
                } catch (DateTimeParseException ex)  {
                    return textStart + "closing hours are invalid; ";
                }
                int comparison = start.compareTo(end);
                if (comparison == 0) {
                    return textStart + "closing hours can't be the same as opening hours; ";
                } else if (comparison > 0) {
                    return textStart + "closing hours can't be earlier than opening hours; ";
                } else {
                    return "";
                }
            }
        } else if (open == null || open.isEmpty()) {
            return textStart + "can't have closing hours without opening hours; ";
        } else {
            return textStart + "can't have opening hours without closing hours; ";
        }
    }

    private String validateCity(String city) {
        if (city == null || city.isEmpty()) {
            return  "City name is required; ";
        } else if (!Pattern.matches("^[a-zA-ZąčęėįšųūžĄČĘĖĮŠŲŪŽ ]+$", city)){
            return  "Provided city name is invalid; ";
        }
        return "";
    }

    private String validateAddress(String address) {
        if (address == null || address.isEmpty()) {
            return  "Shooting range address is required; ";
        } else if (!Pattern.matches("^[a-zA-ZąčęėįšųūžĄČĘĖĮŠŲŪŽ .\\d-]+$", address)){
            return  "Provided address is invalid; ";
        }
        return "";
    }
}
