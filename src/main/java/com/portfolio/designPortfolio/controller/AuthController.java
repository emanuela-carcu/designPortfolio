package com.portfolio.designPortfolio.controller;

import com.portfolio.designPortfolio.model.User;
import com.portfolio.designPortfolio.repo.UserRepo;
import com.portfolio.designPortfolio.security.JwtResponse;
import com.portfolio.designPortfolio.security.LoginModelDTO;
import com.portfolio.designPortfolio.services.CustomUserDetailsService;
import com.portfolio.designPortfolio.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserRepo userRepo;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginModelDTO login) throws Exception {
        authentication(login.getEmail(), login.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(login.getEmail());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return new ResponseEntity<JwtResponse>(new JwtResponse(token, userDetails.getAuthorities().iterator().next().getAuthority()), HttpStatus.OK);
    }

    private void authentication(String email, String password) throws Exception {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        } catch (DisabledException e) {
            throw new Exception("User disabled");
        } catch (BadCredentialsException e) {
            throw new Exception("Bad Credentials");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<User> save(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return new ResponseEntity<User>(userRepo.save(user), HttpStatus.CREATED);
    }

    @PostMapping("/sign-out")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtTokenUtil.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body("You've been signed out!");
    }
}
