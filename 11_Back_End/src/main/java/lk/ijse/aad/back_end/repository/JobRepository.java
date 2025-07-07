package lk.ijse.aad.back_end.repository;

import lk.ijse.aad.back_end.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  //optional
public interface JobRepository extends JpaRepository<Job,Integer> {
}
