package vn.hoidanit.jobhunter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import vn.hoidanit.jobhunter.domain.request.LoginDTO;
import vn.hoidanit.jobhunter.domain.response.ResLoginDTO;
import vn.hoidanit.jobhunter.util.SecurityUtil;

@RestController
@RequestMapping("/")
public class AuthController {

    private final AuthenticationManagerBuilder authenthicationManagerBuilder;
    private final PasswordEncoder passwordEncoder;
    private final SecurityUtil securityUtil;

    public AuthController(AuthenticationManagerBuilder authenthicationManagerBuilder, PasswordEncoder passwordEncoder, SecurityUtil securityUtil) {
        this.authenthicationManagerBuilder = authenthicationManagerBuilder;
        this.passwordEncoder = passwordEncoder;
        this.securityUtil = securityUtil;
    }
    
    @PostMapping("/login")
    public ResponseEntity<ResLoginDTO> login(@Valid @RequestBody LoginDTO loginDTO){
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword());

        ResLoginDTO resLoginDTO = new ResLoginDTO();

        Authentication authentication = this.authenthicationManagerBuilder.getObject().authenticate(auth);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // create token
        String token = this.securityUtil.createAccessToken(authentication);

        resLoginDTO.setAccessToken(token);

        return ResponseEntity.ok(resLoginDTO);
    }
}
