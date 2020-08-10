package ac.kr.smu.lookie.guessme.service;

import ac.kr.smu.lookie.guessme.domain.Quiz;
import ac.kr.smu.lookie.guessme.domain.Score;
import ac.kr.smu.lookie.guessme.domain.User;
import ac.kr.smu.lookie.guessme.domain.UserQuiz;
import ac.kr.smu.lookie.guessme.repository.QuizRepository;
import ac.kr.smu.lookie.guessme.repository.ScoreRepository;
import ac.kr.smu.lookie.guessme.repository.UserQuizRepository;
import ac.kr.smu.lookie.guessme.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuizService {
    private final QuizRepository quizRepo;
    private final UserRepository userRepo;
    private final UserQuizRepository userQuizRepo;
    private final ScoreRepository scoreRepo;

    public List<Quiz> getQuiz(){
        List<Quiz> quizList = new ArrayList<>();
        Long[] quizIdList=new Long[5];
        long count = quizRepo.count();//DB에 저장된 퀴즈 개수

        for(int i=0; i<5; i++){//랜덤하게 quizId 추출
            quizIdList[i]=Long.valueOf((long)(Math.random()*count));
            for(int q=0; q<i; q++){
                if(quizIdList[i]==quizIdList[q]){
                    i--;
                    break;
                }
            }
        }

        for(int i=0; i<5; i++)
            quizList.add(quizRepo.findById(quizIdList[i]).get());
        
        return quizList;
    }

    public List<UserQuiz> getQuizOfUser(String nickname) {
        User user = userRepo.findByNickname(nickname).orElse(null);
        if (user == null)
            return null;
        List<UserQuiz> userQuizList = userQuizRepo.findByUser(user);
        return userQuizList;
    }

    public void solveQuiz(int score, String nickname){
        User examiner = userRepo.findByNickname(nickname).get();//출제자
        User answerer = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();//문제를 푼 사람
        scoreRepo.save(Score.builder().answerer(answerer).examiner(examiner).score(score).build());
    }
    public void register(List<Quiz> quizList){
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        quizList.forEach(q ->
           userQuizRepo.save(UserQuiz.builder().quiz(q).user(user).answer(q.getAnswer()).build()));
    }
    public void delete(){
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userQuizRepo.deleteByUser(user);
    }

}
