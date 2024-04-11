//package com.naveen.userreg.services.authservices;
//
//
//import com.naveen.userreg.repos.UserRepo;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class UserDetailsServiceImp implements UserDetailsService {
//
//    private final UserRepo userRepo;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return userRepo.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("USER NOT FOUND!"));
//    }
//}
