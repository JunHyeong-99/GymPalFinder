package JunHyeong.fitnessService.entity;

import jakarta.persistence.*;

@Entity
public class PtPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @OneToOne(mappedBy = "myPtPost")
    private Trainer ptTrainer;

//등록하는 거 부터 시작하자
}
