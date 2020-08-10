package ac.kr.smu.lookie.guessme.controller;

import ac.kr.smu.lookie.guessme.domain.Quiz;
import ac.kr.smu.lookie.guessme.domain.UserQuiz;
import ac.kr.smu.lookie.guessme.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/quizzes")
public class QuizController {
    private final QuizService quizService;

    @GetMapping("/{nickname}")
    public ResponseEntity<?> getQuiz(@PathParam("nickname") String nickname){
        List<UserQuiz> userQuizList = quizService.getQuiz(nickname);
        List<Quiz> quizList= new ArrayList<>();
        List<Integer> answerList= new ArrayList<>();
        userQuizList.forEach(uq ->{
            Quiz quiz = uq.getQuiz();
            quiz.setAnswer(uq.getAnswer());
            quizList.add(quiz);
        });
        userQuizList.forEach(uq-> answerList.add(uq.getAnswer()));
        CollectionModel<Quiz> returnQuizList= CollectionModel.of(quizList);
        return new ResponseEntity<>(returnQuizList, HttpStatus.OK);
    }
    @PostMapping("/{nickname}")
    public ResponseEntity<?> postQuiz(@RequestBody int score, @PathParam("nickname") String nickname){
        quizService.solveQuiz(score, nickname);
        return null;
    }
}
