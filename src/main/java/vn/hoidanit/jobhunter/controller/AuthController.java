package vn.hoidanit.jobhunter.controller;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.hoidanit.jobhunter.domain.request.LoginDTO;

@RestController
@RequestMapping("/")
public class AuthController {

    private final AuthenticationManagerBuilder authenthicationManagerBuilder;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManagerBuilder authenthicationManagerBuilder, PasswordEncoder passwordEncoder) {
        this.authenthicationManagerBuilder = authenthicationManagerBuilder;
        this.passwordEncoder = passwordEncoder;
    }
    
    @PostMapping("/login")
    public String login(@RequestBody LoginDTO loginDTO){
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword());

        Authentication authenticatioin = this.authenthicationManagerBuilder.getObject().authenticate(auth);
        return "Login successful";
    }
}
