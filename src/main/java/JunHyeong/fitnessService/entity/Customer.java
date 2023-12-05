package JunHyeong.fitnessService.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column
    private String password;
    @Column
    private String email;

    @Column
    private String name;

    @Column
    private String phoneNumber;

    @Column
    private String role;

    @OneToMany(mappedBy = "ptCustomer", cascade = CascadeType.ALL)
    private List<PtMatching> myTrainerList = new ArrayList<>();
}
