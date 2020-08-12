package ac.kr.smu.lookie.guessme.controller;

import ac.kr.smu.lookie.guessme.service.LoginService;
import ac.kr.smu.lookie.guessme.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Map;


@EnableWebMvc
@RequiredArgsConstructor
@RestController
@RequestMapping
public class LoginController {

    private final LoginService loginService;

    @PostMapping(value = "/login") //로그인 (+ 에러처리)
    public ResponseEntity<?> login(@RequestBody Map<String, String> map){
        return new ResponseEntity<>(loginService.checkLogin(map.get("nickname"), map.get("password")), HttpStatus.OK);
    }

}
