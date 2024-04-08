package com.naveen.userreg.controllers;

import com.naveen.userreg.models.AuthenticationResponse;
import com.naveen.userreg.models.Login;
import com.naveen.userreg.models.User;
import com.naveen.userreg.services.AuthenticationService;
import com.naveen.userreg.services.UserService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
//@RequestMapping("user")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class AppController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService authenticationService;


    @GetMapping("hello")
    public String greet(){
        return "Hello, World!";
    }

    @GetMapping("csrf-token")
    public CsrfToken getCSRF(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @PostMapping("register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody User user) throws MessagingException {
        return ResponseEntity.ok(authenticationService.register(user));
    }

//    @PutMapping("/verify-account")
//    public ResponseEntity<String> verifyAccount(@RequestParam String email, @RequestParam String otp){
//        return new ResponseEntity<>(userService.verifyAccount(email, otp), HttpStatus.OK);
//    }

    @PostMapping("login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody User request){
        return ResponseEntity.ok(authenticationService.authenticationResponse(request));

    }


    @PutMapping("verify-account")
    public ResponseEntity<String> verifyAccount(@RequestParam String email, @RequestParam String otp) {
        return new ResponseEntity<>(userService.verifyAccount(email, otp), HttpStatus.OK);
    }

    @GetMapping("/redirect")
    public String getRedirect(){
        return "Redirecting to Home Page";
    }

    @PutMapping("/regenerate-otp")
    public ResponseEntity<String> regenerateOtp(@RequestParam String email){
        return new ResponseEntity<>(userService.regenerateOtp(email), HttpStatus.OK);
    }

//    @PutMapping("/login")
//    public ResponseEntity<String> login(@RequestBody Login login){
//        return new ResponseEntity<>(userService.login(login), HttpStatus.OK);
//    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUser();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
    }


}
