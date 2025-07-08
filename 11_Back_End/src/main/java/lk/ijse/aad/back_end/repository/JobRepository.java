package lk.ijse.aad.back_end.repository;

import jakarta.transaction.Transactional;
import lk.ijse.aad.back_end.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository  //optional
public interface JobRepository extends JpaRepository<Job,Integer> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE job SET status='Deactivated' WHERE id=?1", nativeQuery = true)
    void updateJobByStatus(String jobId);

    List<Job> findJobByJobTitleContainingIgnoreCase(String keyword);
}
