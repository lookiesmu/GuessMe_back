package ac.kr.smu.lookie.guessme.repository;

import ac.kr.smu.lookie.guessme.domain.Quiz;
import ac.kr.smu.lookie.guessme.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz,Long> {
    @Query("SELECT uq.answer FROM UserQuiz uq JOIN fetch Quiz q ON uq.quiz.quizId=q.quizId WHERE uq.user=:user")
    public List<Quiz> findBy(@Param("user") User user);
}
