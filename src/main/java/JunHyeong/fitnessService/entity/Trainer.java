package JunHyeong.fitnessService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @Column
    private String password;
    @Column
    private String name;

    @Column
    private String phoneNumber;

    @Column
    @OneToMany(mappedBy = "ptTrainer")
    private List<PtMatching> myPtList = new ArrayList<>();

    @Column
    @OneToOne
    @JoinColumn(name = "post_id")
    private PtPost myPtPost;
}
