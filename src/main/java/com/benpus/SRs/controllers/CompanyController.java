package com.benpus.SRs.controllers;

import com.benpus.SRs.DTOs.CompanyDTO;
import com.benpus.SRs.DTOs.FirearmDTO;
import com.benpus.SRs.DTOs.InstructorDTO;
import com.benpus.SRs.models.*;
import com.benpus.SRs.repositories.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;
import java.util.regex.Pattern;

@RestController
@RequestMapping("api/v1/companies")
public class CompanyController {
    private final CompanyRepository companyRepository;
    public CompanyController(CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
    }
    @Autowired
    private FirearmRepository firearmRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ShootingRangeRepository rangeRepository;
    @Autowired
    private InstructorRepository instructorRepository;
    record NewCompanyRequest(String name, String address, String postalCode, String city, String phoneNumber, String email, String website, String isApproved, String fk_user){}
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{companyID}/firearms")
    public ResponseEntity<Object> getCompanyFirearms(@PathVariable("companyID") Integer ID){
        Optional<Company> companyQuery = companyRepository.findById(ID);
        if (companyQuery.isEmpty()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Company with specified ID was not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        List<ShootingRange> shootingRanges = rangeRepository.findByFkCompany(companyQuery.get());
        List<Firearm> firearms = new ArrayList<>();
        for (ShootingRange range : shootingRanges){
            List<Firearm> someFirearms = firearmRepository.findByfkShootingRange(range);
            firearms.addAll(someFirearms);
        }
        List<FirearmDTO> firearmsToShow = firearms.stream().map(firearm -> modelMapper.map(firearm, FirearmDTO.class)).toList();
        return ResponseEntity.ok(firearmsToShow);
    }

    @GetMapping("/{companyID}/instructors")
    public ResponseEntity<Object> getCompanyInstructors(@PathVariable("companyID") Integer ID){
        Optional<Company> companyQuery = companyRepository.findById(ID);
        if (companyQuery.isEmpty()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Company with specified ID was not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        List<ShootingRange> shootingRanges = rangeRepository.findByFkCompany(companyQuery.get());
        List<Instructor> instructors = new ArrayList<>();
        for (ShootingRange range : shootingRanges){
            List<Instructor> someInstructors = instructorRepository.findByfkShootingRange(range);
            instructors.addAll(someInstructors);
        }
        List<InstructorDTO> instructorsToShow = instructors.stream().map(instructor -> modelMapper.map(instructor, InstructorDTO.class)).toList();
        return ResponseEntity.ok(instructorsToShow);
    }

    @GetMapping
    public ResponseEntity<List<CompanyDTO>> getCompanies() {
        List<Company> companies = companyRepository.findAll();
        List<CompanyDTO> companiesToShow = companies.stream().map(company -> modelMapper.map(company, CompanyDTO.class)).toList();
        return ResponseEntity.ok(companiesToShow);
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<Object> getCompany(@PathVariable("companyId") Integer ID){
        Optional<Company> companyQuery = companyRepository.findById(ID);
        if (companyQuery.isEmpty()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Company with specified ID was not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } else {
            CompanyDTO companyToShow = modelMapper.map(companyQuery.get(), CompanyDTO.class);
            return ResponseEntity.ok(companyToShow);
        }
    }

    @PostMapping
    public ResponseEntity<Object> addCompany(@RequestBody NewCompanyRequest request) {
        String errorMsg = "", name, address, postal, city, phone, email, website, fk_user;

        name = request.name;
        email = request.email;
        address =  request.address;
        postal = request.postalCode;
        city = request.city;
        phone = request.phoneNumber;
        website = request.website;
        fk_user = request.fk_user;

        errorMsg += this.validateName(name);
        errorMsg += this.validateAddress(address);
        errorMsg += this.validateNullableEmail(email);
        errorMsg += this.validateNullablePostal(postal);
        errorMsg += this.validateCity(city);
        errorMsg += this.validateNullablePhone(phone);
        errorMsg += this.validateNullableWebsite(website);
        errorMsg += this.validateFkUser(fk_user);

        if (!errorMsg.isEmpty()){
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("message", errorMsg);
            return ResponseEntity.unprocessableEntity().body(responseBody);
        }

        Integer fk = Integer.parseInt(fk_user);

        Optional<User> userQuery = userRepository.findById(fk);
        User user = userQuery.get();

        Company company = new Company();
        company.setName(name);
        company.setAddress(address);
        company.setCity(city);
        company.setApproved(false);
        company.setEmail(email);
        company.setPhoneNumber(phone);
        company.setPostalCode(postal);
        company.setWebsite(website);
        company.setFk_user(user);

        company = companyRepository.save(company);
        Integer id = company.getId();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        CompanyDTO companyToShow = modelMapper.map(company, CompanyDTO.class);
        return ResponseEntity.created(location).body(companyToShow);
    }


    @PatchMapping("/{companyId}")
    public ResponseEntity<Object> editUser(@PathVariable("companyId") Integer ID, @RequestBody NewCompanyRequest request){
        Optional<Company> companyQuery = companyRepository.findById(ID);
        if (companyQuery.isEmpty()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Company with specified ID was not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        Company company = companyQuery.get();

        String errorMsg = "", name, address, postal, city, phone, email, website, fk_user, isApproved;

        name = request.name;
        email = request.email;
        address =  request.address;
        postal = request.postalCode;
        city = request.city;
        phone = request.phoneNumber;
        website = request.website;
        isApproved = request.isApproved;
        fk_user = request.fk_user;

        errorMsg += this.validateNullableAddress(address);
        errorMsg += this.validateNullableEmail(email);
        errorMsg += this.validateNullablePostal(postal);
        errorMsg += this.validateNullableCity(city);
        errorMsg += this.validateNullablePhone(phone);
        errorMsg += this.validateNullableWebsite(website);
        errorMsg += this.validateNullableFkUser(fk_user);

        if (!errorMsg.isEmpty()){
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("message", errorMsg);
            return ResponseEntity.unprocessableEntity().body(responseBody);
        }

        if (name != null && !name.isEmpty()) {
            company.setName(name);
        }
        if (email != null && !email.isEmpty()) {
            company.setEmail(email);
        }
        if (address != null && !address.isEmpty()) {
            company.setAddress(address);
        }
        if (postal != null && !postal.isEmpty()) {
            company.setPostalCode(postal);
        }
        if (city != null && !city.isEmpty()) {
            company.setCity(city);
        }
        if (phone != null && !phone.isEmpty()) {
            company.setPhoneNumber(phone);
        }
        if (website != null && !website.isEmpty()) {
            company.setWebsite(website);
        }
        if (isApproved != null && !isApproved.isEmpty()) {
            company.setApproved(Boolean.parseBoolean(isApproved));
        }
        if (fk_user != null && !fk_user.isEmpty()) {
            Optional<User> userQuery = userRepository.findById(Integer.parseInt(fk_user));
            company.setFk_user(userQuery.get());
        }
        company = companyRepository.save(company);
        CompanyDTO companyToShow = modelMapper.map(company, CompanyDTO.class);
        return ResponseEntity.ok(companyToShow);
    }

    @DeleteMapping("/{companyId}")
    public ResponseEntity<Object> deleteUser(@PathVariable("companyId") Integer ID)
    {
        Optional<Company> companyQuery = companyRepository.findById(ID);
        if (companyQuery.isEmpty()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Company with specified ID was not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        companyRepository.deleteById(ID);
        return ResponseEntity.noContent().build();
    }

    private String validateName(String name) {
        if (name == null || name.isEmpty()) {
            return  "Company name is required; ";
        }
        return "";
    }

    private String validateNullableCity(String city) {
        if (city == null || city.isEmpty() || Pattern.matches("^[a-zA-ZąčęėįšųūžĄČĘĖĮŠŲŪŽ ]+$", city)) {
            return "";
        }
        return "Provided city is invalid; ";
    }

    private String validateNullableFkUser(String fk_user) {
        if (fk_user == null || fk_user.isEmpty()) {
            return  "";
        } else if (Pattern.matches("^\\d+$", fk_user)){
            Optional<User> userQuery = userRepository.findById(Integer.parseInt(fk_user));
            if (userQuery.isEmpty()) {
                return "User with provided ID was not found; ";
            } else if (userQuery.get().getType() ==  UserType.ADMIN) {
                return "Administrators can't be owners of a company; ";
            } else {
                return "";
            }
        }
        return "User ID of company director must be a natural number; ";
    }

    private String validateFkUser(String fk_user) {
        if (fk_user == null || fk_user.isEmpty()) {
            return  "User ID of company director is required; ";
        } else if (!Pattern.matches("^\\d+$", fk_user)){
            return  "User ID of company director must be a natural number; ";
        }
        Optional<User> userQuery = userRepository.findById(Integer.parseInt(fk_user));
        if (userQuery.isEmpty()) {
            return "User with provided ID was not found";
        } else if (userQuery.get().getType() ==  UserType.ADMIN) {
            return "Administrators can't be owners of a company";
        }
        return "";
    }

    private String validateAddress(String address) {
        if (address == null || address.isEmpty()) {
            return  "Company address is required; ";
        } else if (!Pattern.matches("^[a-zA-ZąčęėįšųūžĄČĘĖĮŠŲŪŽ .\\d-]+$", address)){
            return  "Provided address is invalid; ";
        }
        return "";
    }

    private String validateNullableAddress(String address) {
        if (address == null || address.isEmpty() || Pattern.matches("^[a-zA-ZąčęėįšųūžĄČĘĖĮŠŲŪŽ .\\d-]+$", address)) {
            return "";
        }
        return "Provided address is invalid; ";
    }

    private String validateNullableEmail(String email) {
        if (email == null || email.isEmpty() || Pattern.matches("^[-ĄČĘĖĮŠŲŪŽąčęėįšųūž\\w.]+@([\\w-]+\\.)+[\\w-]{2,4}$", email)){
            return "";
        } else {
            return  "Provided company email is invalid; ";
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

    private String validateNullablePostal(String postal) {
        if (postal == null || postal.isEmpty() || Pattern.matches("^LT-\\d\\d\\d\\d\\d$", postal)){
            return "";
        } else if (Pattern.matches("^\\d\\d\\d\\d\\d$", postal)) {
            return "Postal code must start with: 'LT-'";
        } else {
            return  "Provided postal code is invalid; ";
        }
    }

    private String validateNullableWebsite(String website) {
        if (website == null || website.isEmpty() || Pattern.matches("^((ftp|http|https)://)?(www.)?(?!.*(ftp|http|https|www.))[ąčęėįšųūžĄČĘĖĮŠŲŪŽa-zA-Z\\d_-]+(\\.[ąčęėįšųūžĄČĘĖĮŠŲŪŽa-zA-Z]+)+((/)[\\w#]+)*(/\\w+\\?[ąčęėįšųūžĄČĘĖĮŠŲŪŽa-zA-Z\\d_]+=\\w+(&[ąčęėįšųūžĄČĘĖĮŠŲŪŽa-zA-Z\\d_]+=\\w+)*)?/?$", website)){
            return "";
        } else {
            return  "Provided website address is invalid; ";
        }
    }
    private String validateNullablePhone(String phone) {
        if (phone == null || phone.isEmpty() || Pattern.matches("^(8|\\+370)\\d\\d\\d\\d\\d\\d\\d\\d?$", phone)){
            return "";
        } else {
            return  "Provided phone number is invalid; ";
        }
    }
}
