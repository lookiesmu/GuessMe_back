package ac.kr.smu.lookie.guessme.service;

import ac.kr.smu.lookie.guessme.config.security.JwtTokenProvider;
import ac.kr.smu.lookie.guessme.domain.Score;
import ac.kr.smu.lookie.guessme.domain.User;
import ac.kr.smu.lookie.guessme.exception.CNicknameSigninFailedException;
import ac.kr.smu.lookie.guessme.repository.ScoreRepository;
import ac.kr.smu.lookie.guessme.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

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

    public Map<String, Boolean> checkDuplicateNickname(String nickname) {//닉네임 중복 검사
        Map<String, Boolean> returnJson = new HashMap<>();
        if (userRepo.findByNickname(nickname).orElse(null) == null)
            returnJson.put("success",true);
        else
            returnJson.put("success",false);
        return returnJson;
    }

    public List<Score> getScoreList(User examiner) {
        List<Score> scores = scoreRepo.findByExaminerOrderByScoreDesc(examiner);
        return scores;
    }

    public Map<String, Boolean> saveUser(User user) {
        Map<String, Boolean> returnJson = new HashMap<>();
        User result = User.builder()
                .nickname(user.getNickname())
                .password(passwordEncoder.encode(user.getPassword()))
                .roles(Collections.singletonList("ROLE_USER"))
                .build();
        if(userRepo.save(result) != null)
            returnJson.put("success",true);
        else returnJson.put("success",false);
        return returnJson;
    }


}
