package vn.hoidanit.jobhunter.util;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class SecurityUtil {

    private final JwtEncoder jwtEncoder;

    public SecurityUtil(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }
    
    @Value("${duy.jwt.base64_secret}")
    private String secretKey;

    @Value("${duy.jwt.expire_length_second}")
    private int accessTokenExpiratioin;

    public static final MacAlgorithm jWT_ALGORITHM = MacAlgorithm.HS512;


    public String createAccessToken(Authentication authentication){

        Instant now = Instant.now();
        Instant validity = now.plus(accessTokenExpiratioin, ChronoUnit.SECONDS);

        // create payload
        JwtClaimsSet claims = JwtClaimsSet.builder()
        .issuedAt(now)
        .expiresAt(validity)
        .subject(authentication.getPrincipal().toString())
        .claim("user", 11111)
        .build();

        JwsHeader jwsHeader = JwsHeader.with(jWT_ALGORITHM).build();
        
        return this.jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).getTokenValue();
    }
}
