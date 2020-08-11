package ac.kr.smu.lookie.guessme.service;

import ac.kr.smu.lookie.guessme.domain.Score;
import ac.kr.smu.lookie.guessme.domain.User;
import ac.kr.smu.lookie.guessme.repository.ScoreRepository;
import ac.kr.smu.lookie.guessme.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepo;
    private final ScoreRepository scoreRepo;
    private final PasswordEncoder passwordEncoder;

    public void changeQuizCreate(int quizCreate) {//사용자의 퀴즈 생성 여부 변경
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user.setQuizCreate(quizCreate);
        userRepo.save(user);
    }

    public Map<String, String> checkDuplicateNickname(String nickname) {//닉네임 중복 검사
        Map<String, String> returnJson = new HashMap<>();
        if (userRepo.findByNickname(nickname).orElse(null) == null)
            returnJson.put("success","true");
        else
            returnJson.put("success","false");
        return returnJson;
    }

    public List<Score> getScoreList(User examiner) {
        List<Score> scores = scoreRepo.findByExaminerOrderByScoreDesc(examiner);
        User answerer = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return scores;
    }

    public void saveUser(User user) {
        userRepo.save(user);
    }
}
