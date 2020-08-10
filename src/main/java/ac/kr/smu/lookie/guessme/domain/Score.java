package ac.kr.smu.lookie.guessme.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@IdClass(ScoreId.class)
@Builder
public class Score {
    @Id
    @JoinColumn
    @OneToOne
    private User examiner;

    @Id
    @JoinColumn
    @OneToOne
    private User answerer;

    @Column
    private int score;
}
