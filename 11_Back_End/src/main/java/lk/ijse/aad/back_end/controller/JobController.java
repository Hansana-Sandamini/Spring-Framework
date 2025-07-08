package lk.ijse.aad.back_end.controller;

import lk.ijse.aad.back_end.dto.JobDTO;
import lk.ijse.aad.back_end.entity.Job;
import lk.ijse.aad.back_end.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/job")
@RequiredArgsConstructor
@CrossOrigin
public class JobController {

    private final JobService jobService;

    @PostMapping("create")
    public String createJob(@RequestBody JobDTO jobDTO) {
        jobService.saveJob(jobDTO);
        return "Job Created";
    }

    @GetMapping("getall")
    public List<JobDTO> getAllJob() {
        return jobService.getAllJobs();
//        return "Job Retrieved";
    }

    @PutMapping("update")
    public String updateJob(@RequestBody Job job) {
        jobService.updateJob(job);
        return "Job Updated";
    }

    @PatchMapping("changestatus/{id}")
    public String changeJobStaus(@PathVariable("id") String jobId) {
        System.out.println(jobId);
        jobService.changeJobStatus(jobId);
        // call service layer
        return "Job Status Updated";
    }

    @GetMapping("search/{keyword}")
    public List<JobDTO> searchJob(@PathVariable("keyword") String keyword) {
//        return "Job Searched";
        return jobService.searchJobByKeyword(keyword);
    }

}
