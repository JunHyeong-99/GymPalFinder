package JunHyeong.fitnessService.repository;

import JunHyeong.fitnessService.entity.PartnerUser;
import JunHyeong.fitnessService.entity.PtPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PtPostRepository extends JpaRepository<PtPost, Long> {
}
