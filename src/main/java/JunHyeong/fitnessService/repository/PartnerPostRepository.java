package JunHyeong.fitnessService.repository;

import JunHyeong.fitnessService.entity.PartnerPost;
import JunHyeong.fitnessService.entity.PtPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PartnerPostRepository extends JpaRepository<PartnerPost, Long> {

    List<PartnerPost> findAllByWantGender(String wantGender);
//    @Transactional
//    @Query(value = "select from partner_post where gender = :gender", nativeQuery = true)
//    List<PartnerPost> findAllByWantGender(@Param("gender") String gender);
}
