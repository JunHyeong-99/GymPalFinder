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
public class PartnerUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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


    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "post_id")
    private PartnerPost partnerPost;

}
