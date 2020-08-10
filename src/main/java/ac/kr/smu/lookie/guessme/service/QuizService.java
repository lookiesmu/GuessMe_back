package ac.kr.smu.lookie.guessme.service;

import ac.kr.smu.lookie.guessme.domain.Quiz;
import ac.kr.smu.lookie.guessme.domain.Score;
import ac.kr.smu.lookie.guessme.domain.User;
import ac.kr.smu.lookie.guessme.domain.UserQuiz;
import ac.kr.smu.lookie.guessme.repository.QuizRepository;
import ac.kr.smu.lookie.guessme.repository.UserQuizRepository;
import ac.kr.smu.lookie.guessme.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final QuizRepository quizRepo;
    private final UserRepository userRepo;
    private final UserQuizRepository userQuizRepo;

    public List<UserQuiz> getQuiz(String nickname) {
        User user = userRepo.findByNickname(nickname).orElse(null);
        if (user == null)
            return null;
        List<UserQuiz> userQuizList = userQuizRepo.findByUser(user);
        return userQuizList;
    }

    public void solveQuiz(int score, String nickname){
        User user = userRepo.findByNickname(nickname).get();
        Score saveScore= new Score();

    }
}
