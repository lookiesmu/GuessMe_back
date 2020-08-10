package ac.kr.smu.lookie.guessme.repository;

import ac.kr.smu.lookie.guessme.domain.Score;
import ac.kr.smu.lookie.guessme.domain.ScoreId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<Score, ScoreId> {
}
