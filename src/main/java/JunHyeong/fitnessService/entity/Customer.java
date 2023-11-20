package JunHyeong.fitnessService.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column
    private String email;

    @Column
    private String name;

    @Column
    private String phoneNumber;

    @Column
    @OneToMany(mappedBy = "ptCustomer")
    private List<PtMatching> myTrainerList = new ArrayList<>();
}
