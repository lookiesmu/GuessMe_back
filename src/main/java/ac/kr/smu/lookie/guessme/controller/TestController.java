package ac.kr.smu.lookie.guessme.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {
    @GetMapping
    public ResponseEntity<?> getTestUrl(){
        Map<String, String> json = new HashMap<>();
        json.put("success","test");

        return new ResponseEntity<>(json, HttpStatus.OK);
    }
}
