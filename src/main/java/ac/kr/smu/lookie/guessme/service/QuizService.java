package ac.kr.smu.lookie.guessme.service;

import ac.kr.smu.lookie.guessme.config.security.JwtTokenProvider;
import ac.kr.smu.lookie.guessme.domain.Quiz;
import ac.kr.smu.lookie.guessme.domain.Score;
import ac.kr.smu.lookie.guessme.domain.User;
import ac.kr.smu.lookie.guessme.domain.UserQuiz;
import ac.kr.smu.lookie.guessme.repository.QuizRepository;
import ac.kr.smu.lookie.guessme.repository.ScoreRepository;
import ac.kr.smu.lookie.guessme.repository.UserQuizRepository;
import ac.kr.smu.lookie.guessme.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final QuizRepository quizRepo;
    private final UserRepository userRepo;
    private final UserQuizRepository userQuizRepo;
    private final ScoreRepository scoreRepo;
    private final JwtTokenProvider provider;

    public List<UserQuiz> getQuiz(String nickname) {
        User user = userRepo.findByNickname(nickname).orElse(null);
        if (user == null)
            return null;
        List<UserQuiz> userQuizList = userQuizRepo.findByUser(user);
        return userQuizList;
    }

    public void solveQuiz(int score, String nickname){
        User examiner = userRepo.findByNickname(nickname).get();
        User answerer = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        scoreRepo.save(Score.builder().answerer(answerer).examiner(examiner).score(score).build());
    }
}
