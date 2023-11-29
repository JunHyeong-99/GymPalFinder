package JunHyeong.fitnessService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartnerMatching {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column
    private Long user1;

    @Column
    private Long user2;

}
