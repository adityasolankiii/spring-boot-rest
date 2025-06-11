package com.example.spring_boot_rest.controller;

import com.example.spring_boot_rest.model.JobPost;
import com.example.spring_boot_rest.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller thinks that we are returning the view not the data to solve that
//use @ResponseBody over the method
// if all methods are returning a data  make controller as RestController.
@RestController
@CrossOrigin(origins = {"http://localhost:3000","http://192.168.31.77:3000"})
public class JobRestController {

    @Autowired
    private JobService service;

    @GetMapping(path = {"/","jobPosts"}, produces = {"application/json"})
    public List<JobPost> getAllJobs() {
        return service.getAllJobs();
    }

    @GetMapping("jobPost/{postId}")
    public JobPost getJob(@PathVariable int postId) {
        return service.getJob(postId);
    }

    @PostMapping("jobPost")
    public JobPost addJob(@RequestBody JobPost post) {
        service.addJob(post);
        return service.getJob(post.getPostId());
    }

    @PutMapping("jobPost")
    public JobPost updateJob(@RequestBody JobPost post) {
        service.updateJob(post);
        return service.getJob(post.getPostId());
    }

    @DeleteMapping("jobPost/{postId}")
    public String deleteJob(@PathVariable int postId) {
        service.deletePost(postId);
        return "Deleted";
    }

    @GetMapping("jobPosts/keyword/{keyword}")
    public List<JobPost> searchByKeyword(@PathVariable("keyword") String keyword){
        return service.searchByKeyWord(keyword);
    }

    @GetMapping("load")
    public void load(){
        service.load();
    }
}
