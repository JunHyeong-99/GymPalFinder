package JunHyeong.fitnessService.repository;

import JunHyeong.fitnessService.entity.Customer;
import JunHyeong.fitnessService.entity.PartnerMatching;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PartnerMatchingRepository extends JpaRepository<PartnerMatching, Long> {

    List<PartnerMatching> findAllByUser1(Long user_id);
    List<PartnerMatching> findAllByUser2(Long user_id);
}
