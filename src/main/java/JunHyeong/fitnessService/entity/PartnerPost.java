package JunHyeong.fitnessService.entity;

import jakarta.persistence.*;

@Entity
public class PartnerPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "register_id")
    private PartnerUser registerUser;

    @Column
    private String description;


}

