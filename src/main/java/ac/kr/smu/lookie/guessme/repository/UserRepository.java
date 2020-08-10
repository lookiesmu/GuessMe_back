package ac.kr.smu.lookie.guessme.repository;

import ac.kr.smu.lookie.guessme.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByNickname(String nickname);
    public Optional<User> findById(Long userId);

}
