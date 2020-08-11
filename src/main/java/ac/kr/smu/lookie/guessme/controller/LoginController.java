package ac.kr.smu.lookie.guessme.controller;

import ac.kr.smu.lookie.guessme.config.security.JwtTokenProvider;
import ac.kr.smu.lookie.guessme.domain.User;
import ac.kr.smu.lookie.guessme.exception.CNicknameSigninFailedException;
import ac.kr.smu.lookie.guessme.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@EnableWebMvc
@RequiredArgsConstructor
@RestController
@RequestMapping
public class LoginController {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;


    @PostMapping(value = "/login") //login
//    public String login(@RequestParam String nickname, @RequestParam String password){
    public String login(@RequestBody Map<String, String> map){
//        User user = User.builder().nickname(map.get("nickname")).password(map.get("password")).build();
        Optional<User> user = userRepository.findByNickname(map.get("nickname"));
                //.orElseThrow(CNicknameSigninFailedException::new));
        if(!passwordEncoder.matches(map.get("password"), user.get().getPassword()))
            throw new CNicknameSigninFailedException();

        return jwtTokenProvider.createToken(String.valueOf(user.get().getUserId()),user.get().getRoles());
    }


}
