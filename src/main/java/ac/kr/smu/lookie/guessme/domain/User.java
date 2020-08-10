package ac.kr.smu.lookie.guessme.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column
    private String nickname;

    @Column
    private String password;

    @Column
    @ColumnDefault("0")
    private int quizCreate;

}
