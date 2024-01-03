package com.example.beetlewar.beetlewar.controller;


import com.example.beetlewar.beetlewar.config.security.JwtService;
import com.example.beetlewar.beetlewar.dto.AuthRequest;
import com.example.beetlewar.beetlewar.dto.RegResponse;
import com.example.beetlewar.beetlewar.model.AppUser;
import com.example.beetlewar.beetlewar.model.Role;
import com.example.beetlewar.beetlewar.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth") @Slf4j
public class AuthController {
    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest request){
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        var user =userRepository.findByUserName(request.getUsername()).orElseThrow();
        var token=jwtService.generateToken(user);
        log.info("the auth = {}",auth);
        return new ResponseEntity<>(token, HttpStatus.OK) ;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody AuthRequest request){
        if (userRepository.existsByUserName(request.getUsername())){
            return new ResponseEntity<>("username is already taken ", HttpStatus.BAD_REQUEST);
        }
        var user= AppUser.builder()
                .userName(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ADMIN)
                .build();
        userRepository.save(user);
        var token=jwtService.generateToken(user);

        RegResponse res=RegResponse.builder()
                .userName(user.getUsername()).token(token).build();


        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
