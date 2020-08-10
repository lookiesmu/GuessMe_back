package ac.kr.smu.lookie.guessme.repository;

import ac.kr.smu.lookie.guessme.domain.User;
import ac.kr.smu.lookie.guessme.domain.UserQuiz;
import ac.kr.smu.lookie.guessme.domain.UserQuizId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserQuizRepository extends JpaRepository<UserQuiz, UserQuizId> {
    public List<UserQuiz> findByUser(User user);
    public void deleteByUser(User user);
}
