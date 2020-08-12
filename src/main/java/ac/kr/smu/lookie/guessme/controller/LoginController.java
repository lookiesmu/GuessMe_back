package ac.kr.smu.lookie.guessme.controller;

import ac.kr.smu.lookie.guessme.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;



@RequiredArgsConstructor
@RestController
@RequestMapping
public class LoginController {

    private final LoginService loginService;

    @PostMapping(value = "/login") //로그인 (+ 에러처리)
    public ResponseEntity<?> login(@RequestBody Map<String, String> map) {
        Map<String, String> body = loginService.checkLogin(map.get("nickname"), map.get("password"));
        MultiValueMap<String,String> header = new LinkedMultiValueMap<>();
        if(body.containsKey("nickname")){//만약 로그인 성공이라면 헤더에 토큰 넣어서 반환
            header.add("X-AUTH-TOKEN", body.get("token"));
            body.remove("token");
        }
        return new ResponseEntity<>(body,header,HttpStatus.OK);
    }

}
