package ac.kr.smu.lookie.guessme.repository;

import ac.kr.smu.lookie.guessme.domain.Score;
import ac.kr.smu.lookie.guessme.domain.ScoreId;
import ac.kr.smu.lookie.guessme.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, ScoreId> {
    public List<Score> findByExaminerOrderByScoreDesc(User examiner);

    public Score findByExaminerAndAnswerer(User examiner, User answerer);
}
