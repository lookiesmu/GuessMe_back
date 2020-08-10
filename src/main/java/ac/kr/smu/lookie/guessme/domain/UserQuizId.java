package ac.kr.smu.lookie.guessme.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserQuizId implements Serializable {
    private Long userId;
    private Long quizId;
}