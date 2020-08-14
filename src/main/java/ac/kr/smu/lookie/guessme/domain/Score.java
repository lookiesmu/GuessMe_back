package ac.kr.smu.lookie.guessme.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;


import javax.persistence.*;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@IdClass(ScoreId.class)
@Builder
@ToString
public class Score {
    @Id
    @JoinColumn(name="examiner")
    @OneToOne
    @JsonIgnore
    private User examiner;

    @Id
    @JoinColumn(name="answerer")
    @OneToOne
    private User answerer;

    @Column
    private int score;

}
