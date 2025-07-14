package lk.ijse.aad.back_end.service;

import lk.ijse.aad.back_end.dto.JobDTO;
import lk.ijse.aad.back_end.entity.Job;
import org.springframework.data.domain.Page;

import java.util.List;

public interface JobService {
    public void saveJob(JobDTO jobDTO);
    public void updateJob(Job job);
    public List<JobDTO> getAllJobs();
    public void changeJobStatus(String jobId);
    public List<JobDTO> searchJobByKeyword(String keyword);
    public void deleteJob(String jobId);
    Page<JobDTO> getAllJobsPaginated(int page, int size);
    Page<JobDTO> searchJobsPaginated(String keyword, int page, int size);
}
