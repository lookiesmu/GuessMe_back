package ac.kr.smu.lookie.guessme.controller;

import ac.kr.smu.lookie.guessme.domain.Quiz;
import ac.kr.smu.lookie.guessme.domain.UserQuiz;
import ac.kr.smu.lookie.guessme.service.QuizService;
import ac.kr.smu.lookie.guessme.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/quizzes")
public class QuizController {
    private final QuizService quizService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<?> getQuiz() {
        CollectionModel<Quiz> returnQuizList = CollectionModel.of(quizService.getQuiz());
        return new ResponseEntity(returnQuizList, HttpStatus.OK);
    }

    @PostMapping//퀴즈 생성
    @Transactional
    public ResponseEntity<?> postQuiz(@RequestBody List<Quiz> quizList) {
        quizService.register(quizList);
        userService.changeQuizCreate(1);
        return ResponseEntity.ok("{}");
    }

    @GetMapping("/{nickname}")//퀴즈 조회
    public ResponseEntity<?> getQuizOfUser(@PathVariable("nickname") String nickname) {
        List<UserQuiz> userQuizList = quizService.getQuizOfUser(nickname);
        List<Quiz> quizList = new ArrayList<>();
        userQuizList.forEach(uq -> {//퀴즈 내용과 답을 퀴즈 객체로 바인딩
            Quiz quiz = uq.getQuiz();
            quiz.setAnswer(uq.getAnswer());
            quizList.add(quiz);
        });
        CollectionModel<Quiz> returnQuizList = CollectionModel.of(quizList);
        return new ResponseEntity<>(returnQuizList, HttpStatus.OK);
    }

    @PostMapping("/{nickname}")//퀴즈 풀기
    public ResponseEntity<?> solveQuiz(@RequestBody Map<String, String> json, @PathVariable("nickname") String nickname) {
        quizService.solveQuiz(Integer.valueOf(json.get("score")), nickname);
        return ResponseEntity.ok("{}");
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<?> deleteQuiz() {//퀴즈 삭제
        quizService.delete();
        userService.changeQuizCreate(0);
        return ResponseEntity.ok("{}");
    }
}