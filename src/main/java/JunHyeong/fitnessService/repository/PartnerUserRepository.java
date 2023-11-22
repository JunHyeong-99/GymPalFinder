package JunHyeong.fitnessService.repository;

import JunHyeong.fitnessService.entity.Customer;
import JunHyeong.fitnessService.entity.PartnerUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PartnerUserRepository extends JpaRepository<PartnerUser, Long> {
    Optional<PartnerUser> findByEmail(String email);
}
