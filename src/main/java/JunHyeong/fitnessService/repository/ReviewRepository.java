package JunHyeong.fitnessService.repository;

import JunHyeong.fitnessService.entity.Customer;
import JunHyeong.fitnessService.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByCustomer(Customer customer);
}
