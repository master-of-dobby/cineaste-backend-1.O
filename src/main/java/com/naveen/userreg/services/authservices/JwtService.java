//package com.naveen.userreg.services.authservices;
//
//import com.naveen.userreg.models.User;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//import javax.crypto.SecretKey;
//
//import java.util.Date;
//import java.util.function.Function;
//
//@Service
//public class JwtService {
//
//    private final String SECRET_KEY = "344639f2481c59c83de4f026dd4b0fa6100621a65f742c978b81f6c3e28d7b21";
//
//    public String extractUsername(String token){
//        return extractClaim(token, Claims::getSubject);
//    }
//
//    public boolean isValid(String token, UserDetails user){
//        String username = extractUsername(token);
//        return (username.equals(user.getUsername())) && !isTokenExpired(token);
//    }
//
//    private boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }
//
//    private Date extractExpiration(String token) {
//        return extractClaim(token, Claims::getExpiration);
//    }
//
//    private <T> T extractClaim(String token, Function<Claims, T> resolver){
//        Claims claims = extractAllClaims(token);
//        return resolver.apply(claims);
//    }
//
//    private Claims extractAllClaims(String token){
//        return Jwts
//                       .parser()
//                       .verifyWith(getSignInKey())
//                       .build()
//                       .parseSignedClaims(token)
//                       .getPayload();
//    }
//
//    public String generateToken(User user){
//        String token = Jwts
//                               .builder()
//                               .subject(user.getUsername())
//                               .issuedAt(new Date(System.currentTimeMillis()))
//                               .expiration(new Date(System.currentTimeMillis() + 24*60*60*1000))
//                               .signWith(getSignInKey())
//                               .compact();
//
//        return token;
//    }
//
//    private SecretKey getSignInKey() {
//        byte[] keyBytes = Decoders.BASE64URL.decode(SECRET_KEY);
//
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
//}
