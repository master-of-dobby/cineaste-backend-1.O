package com.naveen.userreg.util;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
public class EmailUtil {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendOtpEmail(String email, String otp) throws MessagingException {
//        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//        simpleMailMessage.setTo(email);
//        simpleMailMessage.setSubject("Verify OTP - CINEASTE");
//        simpleMailMessage.setText("Hello! Welcome to Cineaste! Your OTP is " + otp);

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo(email);

        String emailVerificationLink = ServletUriComponentsBuilder.fromCurrentContextPath()
                                               .path("/user/verify-account")
                                               .queryParam("email", email)
                                               .queryParam("otp", otp)
                                               .toUriString();

        String linkTo = String.format("http://localhost:3000/redirect/%s/%s", email, otp);
        String emailContent = String.format("<div><a href=\"%s\" target=\"_blank\">Click Link to Verify</a></div>", linkTo);

        mimeMessageHelper.setSubject("Verify OTP - CINEASTE");
        mimeMessageHelper.setText(emailContent, true);

        javaMailSender.send(mimeMessage);

    }
}
