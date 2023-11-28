package JunHyeong.fitnessService.entity;

import JunHyeong.fitnessService.dto.PtPostResponseDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PtPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "myPtPost")
    private Trainer ptTrainer;

    @Column
    private String title;

    @Column
    private int price;

    @Column
    private String description;

    public PtPostResponseDto toResponsePostDto() {
        return PtPostResponseDto.builder()
                .post_id(this.getId())
                .price(this.getPrice())
                .title(this.getTitle())
                .build();
    }

}
