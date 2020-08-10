package ac.kr.smu.lookie.guessme.controller;

import ac.kr.smu.lookie.guessme.config.security.JwtTokenProvider;
import ac.kr.smu.lookie.guessme.domain.User;
import ac.kr.smu.lookie.guessme.exception.CNicknameSigninFailedException;
import ac.kr.smu.lookie.guessme.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Collections;

@EnableWebMvc
@RequiredArgsConstructor
@RestController
@RequestMapping
public class LoginController {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;


    @PostMapping(value = "/login") //login
    public String login(@RequestParam String nickname,
                        @RequestParam String password){
        User user = userRepository.findByNickname(nickname).orElseThrow(CNicknameSigninFailedException::new);
        if(!passwordEncoder.matches(password, user.getPassword()))
            throw new CNicknameSigninFailedException();

        return jwtTokenProvider.createToken(String.valueOf(user.getUserId()),user.getRoles());
    }


}
