package ac.kr.smu.lookie.guessme.controller;

import ac.kr.smu.lookie.guessme.domain.Score;
import ac.kr.smu.lookie.guessme.domain.User;
import ac.kr.smu.lookie.guessme.repository.UserRepository;
import ac.kr.smu.lookie.guessme.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    @GetMapping
    public ResponseEntity<?> getUser(@RequestBody Map<String, String> json){//닉네임 중복확인
        return new ResponseEntity<>(userService.checkDuplicateNickname(json.get("nickname")), HttpStatus.OK);
    }

    @PostMapping //회원가입
    public ResponseEntity<?> signUp(@RequestBody Map<String, String> map) {
        User user = User.builder().nickname(map.get("nickname")).password(map.get("password")).build();
        userService.saveUser(user);
        return ResponseEntity.ok("{}");
    }

    @GetMapping("/rank")
    public Map<String, Object> ranking(@AuthenticationPrincipal User user){
        List<Score> list = userService.getScoreList(user);
        Map<String, Object> map = new HashMap<>();
        map.put("ranking",list);
        return map;
    }

}
