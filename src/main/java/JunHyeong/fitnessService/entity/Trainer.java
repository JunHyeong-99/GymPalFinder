package JunHyeong.fitnessService.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

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
