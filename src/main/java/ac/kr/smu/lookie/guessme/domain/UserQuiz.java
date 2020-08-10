package ac.kr.smu.lookie.guessme.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@IdClass(UserQuizId.class)
public class UserQuiz
{
    @Id
    @OneToOne
    @JoinColumn
    private User user;

    @Id
    @OneToOne
    @JoinColumn
    private Quiz quiz;

    @Column
    private int answer;


}
