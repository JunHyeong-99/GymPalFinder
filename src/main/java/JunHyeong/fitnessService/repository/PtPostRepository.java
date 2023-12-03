package JunHyeong.fitnessService.repository;

import JunHyeong.fitnessService.entity.PartnerUser;
import JunHyeong.fitnessService.entity.PtPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PtPostRepository extends JpaRepository<PtPost, Long> {
    List<PtPost> findAllByOrderByPrice();
}
