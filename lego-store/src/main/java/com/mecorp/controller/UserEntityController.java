package com.mecorp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mecorp.exception.GeneralException;
import com.mecorp.exception.NotFoundException;
import com.mecorp.facade.UserEntityFacade;
import com.mecorp.model.UserEntity;
import com.mecorp.utils.JwtTokenUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import static com.mecorp.utils.JwtTokenUtil.HEADER_STRING;
import static com.mecorp.utils.JwtTokenUtil.TOKEN_PREFIX;

@RestController
@RequestMapping("/users")
public class UserEntityController {
    private final UserEntityFacade userEntityFacade;

    private final JwtTokenUtil jwtTokenUtil;
    public UserEntityController(UserEntityFacade userEntityFacade, JwtTokenUtil jwtTokenUtil) {
        this.userEntityFacade = userEntityFacade;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<String> doLogin() throws GeneralException, NotFoundException, JsonProcessingException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            throw new GeneralException("Something went wrong when trying to login");
        }

        UserEntity userEntity = this.userEntityFacade.findByEmail(authentication.getName());

        if (userEntity == null) {
            throw new NotFoundException("User not found");
        }

        String jwtToken = this.jwtTokenUtil.generateToken(userEntity);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HEADER_STRING, TOKEN_PREFIX + " " + jwtToken);

        return new ResponseEntity<>("Logged in", httpHeaders, HttpStatus.ACCEPTED);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> doLogout() {
        SecurityContextHolder.clearContext();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HEADER_STRING, "");

        return new ResponseEntity<>("Logged out", httpHeaders, HttpStatus.OK);
    }
}
