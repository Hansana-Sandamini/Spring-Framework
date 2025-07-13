package lk.ijse.aad.back_end.service.impl;

import lk.ijse.aad.back_end.dto.JobDTO;
import lk.ijse.aad.back_end.entity.Job;
import lk.ijse.aad.back_end.repository.JobRepository;
import lk.ijse.aad.back_end.service.JobService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
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

        jobRepository.save(modelMapper.map(jobDTO, Job.class));
    }

    @Override
    public void updateJob(Job job) {
        jobRepository.save(modelMapper.map(job, Job.class));
    }

    @Override
    public List<JobDTO> getAllJobs() {
        List<Job> jobs = jobRepository.findAll();
        return modelMapper.map(jobs, new TypeToken<List<JobDTO>>(){}.getType());
    }

    @Override
    public void changeJobStatus(String jobId) {
        jobRepository.updateJobByStatus(jobId);
    }

    @Override
    public List<JobDTO> searchJobByKeyword(String keyword) {
        List<Job> keywords = jobRepository.findJobByJobTitleContainingIgnoreCase(keyword);
        return modelMapper.map(keywords, new TypeToken<List<JobDTO>>(){}.getType());
    }

    @Override
    public void deleteJob(String jobId) {
        jobRepository.deleteById(Integer.parseInt(jobId));
    }

}
