package ac.kr.smu.lookie.guessme.service;

import ac.kr.smu.lookie.guessme.domain.Score;
import ac.kr.smu.lookie.guessme.domain.User;
import ac.kr.smu.lookie.guessme.repository.ScoreRepository;
import ac.kr.smu.lookie.guessme.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepo;
    private final ScoreRepository scoreRepo;

    public void changeQuizCreate(int quizCreate){//사용자의 퀴즈 생성 여부 변경
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user.setQuizCreate(quizCreate);
        userRepo.save(user);
    }

    public List<Score> getScoreList(User examiner){
        List<Score> scores = scoreRepo.findByExaminerOrderByScoreDesc(examiner);
        User answerer = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return scores;
    }

}
