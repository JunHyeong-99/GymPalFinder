package JunHyeong.fitnessService.entity;

import JunHyeong.fitnessService.dto.PtPostResponseDto;
import JunHyeong.fitnessService.dto.ReviewResponseDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String body;

    private int point;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    public ReviewResponseDto toResponsePostDto() {
        return ReviewResponseDto.builder()
                .review_id(this.id)
                .body(this.body)
                .point(this.point)
                .build();
    }


}
