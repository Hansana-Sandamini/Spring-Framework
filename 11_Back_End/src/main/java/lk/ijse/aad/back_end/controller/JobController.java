package lk.ijse.aad.back_end.controller;

import jakarta.validation.Valid;
import lk.ijse.aad.back_end.dto.JobDTO;
import lk.ijse.aad.back_end.service.JobService;
import lk.ijse.aad.back_end.util.APIResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/job")
@RequiredArgsConstructor
@CrossOrigin
@Slf4j
public class JobController {

    private final JobService jobService;
//    private static final Logger logger = LoggerFactory.getLogger(JobController.class);

    @PostMapping("create")
    public ResponseEntity<APIResponse<String>> createJob(@RequestBody @Valid JobDTO jobDTO) {
//        logger.info("Job Created Successfully");
//        logger.debug("Job Details : {} ", jobDTO);
//        logger.error("Job Creation Error");
//        logger.trace("Job Creation Trace");
//        logger.warn("Job Creation Warning");
        log.info("Job Created Successfully");   // Business logic related information
        log.debug("Job Details : {} ", jobDTO);  // Details of debugging information
        log.error("Job Creation Error");    // System errors or failers
        log.trace("Job Creation Trace");    // Data tracing
        log.warn("Job Creation Warning");   // Potential problems

        jobService.saveJob(jobDTO);
        return new ResponseEntity(new APIResponse<>(201, "Job Created Successfully", null),
                HttpStatus.CREATED);
    }

    @GetMapping("getall")
    public ResponseEntity<APIResponse<List<JobDTO>>> getAllJob() {
        List<JobDTO> jobDTOS = jobService.getAllJobs();
        return ResponseEntity.ok(new APIResponse<>(200,"Job List Fetched Successfully", jobDTOS));
    }

    @PutMapping("update")
    public ResponseEntity<APIResponse<String>> updateJob(@RequestBody @Valid JobDTO job) {
        jobService.updateJob(job);
        return ResponseEntity.ok(new APIResponse<>(200, "Job Updated Successfully", null));
    }

    @PatchMapping("changestatus/{id}")
    public ResponseEntity<APIResponse<String>> changeJobStatus(@PathVariable("id") String jobId){
        System.out.println(jobId);
        jobService.changeJobStatus(jobId);
        return ResponseEntity.ok(new APIResponse<>(200, "Job Status Changed Successfully", null));
    }

    @GetMapping("search/{keyword}")
    public ResponseEntity<APIResponse<List<JobDTO>>> searchJob(@PathVariable("keyword") String keyword) {
        List<JobDTO> jobDTOS = jobService.searchJobByKeyword(keyword);
        return ResponseEntity.ok(new APIResponse<>(200,"Job Searched Successfully", jobDTOS));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<APIResponse<String>> deleteJob(@PathVariable("id") String jobId) {
        jobService.deleteJob(jobId);
        return ResponseEntity.ok(new APIResponse<>(200, "Job Deleted Successfully", null));
    }

    @GetMapping("getall/paginated")
    public Page<JobDTO> getAllJobsPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return (Page<JobDTO>) jobService.getAllJobsPaginated(page, size);
    }

    @GetMapping("search/paginated/{keyword}")
    public Page<JobDTO> searchJobsPaginated(
            @PathVariable String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return jobService.searchJobsPaginated(keyword, page, size);
    }

}
