//package config;
//
//import com.naveen.userreg.filter.JwtAuthenticationFilter;
//import com.naveen.userreg.services.authservices.UserDetailsServiceImp;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.csrf.CsrfFilter;
//
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SpringBootSecurityConfiguration  {
//
//    private final UserDetailsServiceImp userDetailsServiceImp;
//
//    private final JwtAuthenticationFilter jwtAuthenticationFilter;
//
//
//    @Bean
//    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        return http
//
//                       .csrf(AbstractHttpConfigurer::disable) // this is the problem -> it should disable, even though I made it disable, it is still giving error like not disabled
//                       .authorizeHttpRequests(
//                               req -> req.requestMatchers("/**")
//                                              .permitAll()
//                                              .anyRequest()
//                                              .authenticated()
//                       )
//                       .userDetailsService(userDetailsServiceImp)
//                       .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                       .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
//                       .addFilterAfter(jwtAuthenticationFilter,CsrfFilter.class)
//                       .build();
//
//
//    }
//
//
//
//}
