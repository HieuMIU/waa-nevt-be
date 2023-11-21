package nevt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import nevt.common.constants.Role;
import nevt.models.account.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {

    private final static String jwtSecretKey = "Szg0cyw04z6Z66V0WD3GLgIaZrIbia7UnvxZRKJj9agDPnGyunkLmkxU0dEWbeTV";

    private final static int jwtExpirationMs = 3600000;

    protected static String generateAdminToken(){
        User adminUser = new User("Thang","Nguyen","admin@gmail.com", "$2a$10$.M4AFcr93oPl8SLoID7n0OU29uLHXhiJrLR1N0ppIlfMoKHNhiQW2", Role.ROLE_ADMIN);
        return generateToken(new HashMap<>(), adminUser);
    }

    protected static String generateEmployeeToken(){
        User employeeUser = new User("Hieu","Tran","emp@gmail.com", "$2a$10$Af2YoERjkgz4EHlTeOarq.o97JmAF4CSjVGgpMjpdgqgGa1g6s.lm", Role.ROLE_EMPLOYEE);
        return generateToken(new HashMap<>(), employeeUser);
    }

    protected static String generateUserToken(){
        User user = new User("Dinh Thang","Nguyen","dnguyen@miu.edu", "$2a$10$mGdxU.osneoLi23d6XLGquZTfhiUJrcem7Vmu5lNDmF18BX3sXfwG", Role.ROLE_USER);
        return generateToken(new HashMap<>(), user);
    }

    private static String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private static Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}