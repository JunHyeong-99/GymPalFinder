package JunHyeong.fitnessService.repository;


import JunHyeong.fitnessService.entity.Customer;
import JunHyeong.fitnessService.entity.PtMatching;
import JunHyeong.fitnessService.entity.PtPost;
import JunHyeong.fitnessService.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PtMatchingRepository extends JpaRepository<PtMatching, Long> {

    @Query("select p from PtMatching p where p.ptTrainer = :trainer and p.ptCustomer = :customer")
    Optional<PtMatching> findByTrainerAndCoustomer(@Param("trainer") Trainer trainer, @Param("customer")Customer customer);

}
