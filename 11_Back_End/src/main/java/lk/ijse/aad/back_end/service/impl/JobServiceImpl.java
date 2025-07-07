package lk.ijse.aad.back_end.service.impl;

import lk.ijse.aad.back_end.dto.JobDTO;
import lk.ijse.aad.back_end.entity.Job;
import lk.ijse.aad.back_end.repository.JobRepository;
import lk.ijse.aad.back_end.service.JobService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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

        jobRepository.save(modelMapper.map(jobDTO, Job.class));
    }

}
