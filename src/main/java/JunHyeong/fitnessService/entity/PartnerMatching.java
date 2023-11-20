package JunHyeong.fitnessService.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class PartnerMatching {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column
    @ManyToOne
    @JoinColumn(name = "match_user_id")
    private PartnerUser matchUser;


}
