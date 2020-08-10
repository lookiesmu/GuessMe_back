package ac.kr.smu.lookie.guessme.repository;

import ac.kr.smu.lookie.guessme.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByNickname(String nickname);


}
