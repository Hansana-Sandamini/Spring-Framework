package lk.ijse.aad.back_end.service.impl;

import lk.ijse.aad.back_end.dto.JobDTO;
import lk.ijse.aad.back_end.entity.Job;
import lk.ijse.aad.back_end.exceptions.ResourceNotFound;
import lk.ijse.aad.back_end.repository.JobRepository;
import lk.ijse.aad.back_end.service.JobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final ModelMapper modelMapper;

    @Override
    public void saveJob(JobDTO jobDTO) {
        // save job

//        Job job = new Job();
//        job.setJobTitle(jobDTO.getJobTitle());
//        job.setCompany(jobDTO.getCompany());
//        job.setLocation(jobDTO.getLocation());
//        job.setType(jobDTO.getType());
//        job.setJobDescription(jobDTO.getJobDescription());
//        jobRepository.save(job);

        // less boilerplate codes

        if(jobDTO == null) {
            log.error("Job cannot be null");
            throw new IllegalArgumentException("JobDTO cannot be null");
        }
        jobRepository.save(modelMapper.map(jobDTO, Job.class));
    }

    @Override
    public void updateJob(JobDTO jobDTO) {
        if (jobDTO==null||jobDTO.getId()==null){
            throw new IllegalArgumentException("Job Id cannot be null");
        }
        jobRepository.findById(jobDTO.getId()).orElseThrow(
                ()->new ResourceNotFound("Job Not Found"));
        jobRepository.save(modelMapper.map(jobDTO, Job.class));
    }

    @Override
    public List<JobDTO> getAllJobs() {
        List<Job> jobs = jobRepository.findAll();
        if (jobs.isEmpty()){
            throw new ResourceNotFound("No Job Found");
        }
        return modelMapper.map(jobs, new TypeToken<List<JobDTO>>(){}.getType());
    }

    @Override
    public void changeJobStatus(String jobId) {
        if (jobId==null){
            throw new IllegalArgumentException("Job Id cannot be null");
        }
        jobRepository.updateJobByStatus(jobId);
    }

    @Override
    public List<JobDTO> searchJobByKeyword(String keyword) {
        List<Job> keywords = jobRepository.findJobByJobTitleContainingIgnoreCase(keyword);
        if (keyword==null){
            throw new IllegalArgumentException("Keyword cannot be null");
        }
        if (keywords.isEmpty()){
            throw new ResourceNotFound("No Job Found");
        }
        return modelMapper.map(keywords, new TypeToken<List<JobDTO>>(){}.getType());
    }

    @Override
    public void deleteJob(String jobId) {
        jobRepository.deleteById(Integer.parseInt(jobId));
    }

    @Override
    public Page<JobDTO> getAllJobsPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Job> jobPage = jobRepository.findAll(pageable);
        return jobPage.map(job -> modelMapper.map(job, JobDTO.class));
    }

    @Override
    public Page<JobDTO> searchJobsPaginated(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Job> jobPage = jobRepository.findByJobTitleContainingIgnoreCase(keyword, pageable);
        return jobPage.map(job -> modelMapper.map(job, JobDTO.class));
    }

}
