package ac.kr.smu.lookie.guessme.controller;

import ac.kr.smu.lookie.guessme.domain.Score;
import ac.kr.smu.lookie.guessme.domain.User;
import ac.kr.smu.lookie.guessme.repository.UserRepository;
import ac.kr.smu.lookie.guessme.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EnableWebMvc
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;


    @PostMapping //회원가입
    public ResponseEntity<?> signUp(@RequestParam String nickname,
                                 @RequestParam String password){
        userRepository.save(User.builder()
                .nickname(nickname)
                .password(passwordEncoder.encode(password))
                .roles(Collections.singletonList("ROLE_USER"))
                .build());
        return ResponseEntity.ok("{}"); //확인하기
    }

    @GetMapping("/rank")
    public Map<String, Object> ranking(@AuthenticationPrincipal User user){
        List<Score> list = userService.getScoreList(user);
        Map<String, Object> map = new HashMap<>();
        map.put("ranking",list);
        return map;
    }

}
