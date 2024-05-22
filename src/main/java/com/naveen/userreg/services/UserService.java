package com.naveen.userreg.services;

import com.naveen.userreg.models.AuthModels.Login;
import com.naveen.userreg.models.AuthModels.Role;
import com.naveen.userreg.models.User;
import com.naveen.userreg.repos.UserRepo;
import com.naveen.userreg.util.EmailUtil;
import com.naveen.userreg.util.OtpUtil;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private OtpUtil otpUtil;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EmailUtil emailUtil;

    public String addUser(User user) throws MessagingException {
        String otp = otpUtil.generateOtp();
        try {
            emailUtil.sendOtpEmail(user.getEmail(), otp);
        } catch (MessagingException e) {
            throw new RuntimeException("Unable to send OTP, please Try again :)");
        }
        User u = new User();
        u.setFirstName(user.getFirstName());
        u.setLastName(user.getLastName());
        u.setEmail(user.getEmail());
        u.setOtp(otp);
        u.setPassword(user.getPassword());
        u.setLocation(user.getLocation());
        u.setOtpGeneratedTime(LocalDateTime.now());
        u.setRole(user.getRole());


        userRepo.save(u);
        return "User Registration Successfully";
    }

    public String verifyAccount(String email, String otp) {
        User user = userRepo.findByEmail(email).orElseThrow(() ->
                                                                    new RuntimeException("User NOT FOUND with this email : " + email));

        if (user.getOtp().equals(otp) && Duration.between(user.getOtpGeneratedTime(), LocalDateTime.now()).getSeconds() < (100 * 60)) {
            user.setActive(true);
            userRepo.save(user);
            return "OTP verification is successfully done!";
        }

        return "Please Regenerate OPT and Try again!";

    }

    public String regenerateOtp(String email) {
        User user = userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("User NOT FOUND with this email : " + email));
        String otp = otpUtil.generateOtp();

        try {
            emailUtil.sendOtpEmail(user.getEmail(), otp);
        } catch (MessagingException e) {
            throw new RuntimeException("Unable to send OTP, please Try again :)");
        }

        user.setOtp(otp);
        user.setOtpGeneratedTime(LocalDateTime.now());
        userRepo.save(user);

        return "Email Sent.. Please verify your account within a minute";

    }

    public String login(Login login) {
        User user = userRepo.findByEmail(login.getEmail()).orElseThrow(() ->
                                                                               new RuntimeException("User NOT FOUND with this email : " + login.getEmail()));

        if (!login.getPassword().equals(user.getPassword())) {
            return "Password is Incorrect!";
        } else if (!user.isActive()) {
            return "Your account is not verified!";
        }

        return "Login successful";
    }

    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    public boolean getUser(Long id) {
        return userRepo.existsById(id);
    }

    public User getUserById(Long id){

        return userRepo.findById(id).orElseThrow();
    }

    public User updateUser(Long id, User user) {

        User u = userRepo.findById(id).orElseThrow(() -> new RuntimeException("USER NOT FOUND!"));

//        userRepo.delete(userRepo.findById(id).orElseThrow());

        u.setRole(Role.valueOf(user.getRole().toString()));
        u.setEmail(user.getEmail());
        u.setLastName(user.getLastName());
        u.setFirstName(user.getFirstName());
        u.setActive(user.isActive());
        u.setLocation(user.getLocation());


        return userRepo.save(u);

    }
}
