package JunHyeong.fitnessService.repository;

import JunHyeong.fitnessService.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PartnerMatchingRepository extends JpaRepository<PartnerMatching, Long> {

    List<PartnerMatching> findAllByUser1(Long user_id);
    List<PartnerMatching> findAllByUser2(Long user_id);

    @Query("select m from PartnerMatching m where m.user1 = :user1 and m.user2 = :user2")
    Optional<PartnerMatching> findByUser1AndUser2(@Param("user1") Long user1, @Param("user2") Long user2);


}
