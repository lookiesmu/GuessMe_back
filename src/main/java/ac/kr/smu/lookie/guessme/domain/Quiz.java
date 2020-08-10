package ac.kr.smu.lookie.guessme.domain;

import lombok.*;

import javax.persistence.*;

@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quizId;

    @Column
    private String content;

    @Transient
    private int answer;
}
