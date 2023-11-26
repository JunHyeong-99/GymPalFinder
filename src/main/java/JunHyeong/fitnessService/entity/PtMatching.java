package JunHyeong.fitnessService.entity;

import jakarta.persistence.*;

@Entity
public class PtMatching {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer ptTrainer;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer ptCustomer;


}
