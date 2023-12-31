package JunHyeong.fitnessService.repository;

import JunHyeong.fitnessService.entity.Customer;
import JunHyeong.fitnessService.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {
    Optional<Trainer> findByEmail(String email);
}
