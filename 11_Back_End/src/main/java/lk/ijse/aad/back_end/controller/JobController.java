package lk.ijse.aad.back_end.controller;

import lk.ijse.aad.back_end.dto.JobDTO;
import lk.ijse.aad.back_end.service.JobService;
import lk.ijse.aad.back_end.service.impl.JobServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/job")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @PostMapping("create")
    public String createJob(@RequestBody JobDTO jobDTO) {
        jobService.saveJob(jobDTO);
        return "Job Created";
    }

//    @GetMapping("getall")
//    public String getAllJob() {
//        return "Job Retrieved";
//    }
//
//    @PostMapping("update")
//    public String updateJob() {
//        return "Job Updated";
//    }
//
//    @PostMapping("changestatus")
//    public String changeJobStaus() {
//        return "Job Deleted";
//    }
//
//    @GetMapping("search")
//    public String searchJob() {
//        return "Job Searched";
//    }

}
