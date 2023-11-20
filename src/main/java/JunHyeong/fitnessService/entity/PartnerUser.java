package JunHyeong.fitnessService.entity;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class PartnerUser {

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
    @OneToMany(mappedBy = "matchUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PartnerMatching> myMatchList = new ArrayList<>();

}
