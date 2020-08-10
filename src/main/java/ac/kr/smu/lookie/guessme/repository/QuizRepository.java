package ac.kr.smu.lookie.guessme.repository;

import ac.kr.smu.lookie.guessme.domain.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz,Long> {

}
