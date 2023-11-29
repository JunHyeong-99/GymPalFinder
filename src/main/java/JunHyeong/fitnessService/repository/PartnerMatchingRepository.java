package JunHyeong.fitnessService.repository;

import JunHyeong.fitnessService.entity.Customer;
import JunHyeong.fitnessService.entity.PartnerMatching;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnerMatchingRepository extends JpaRepository<PartnerMatching, Long> {
}
