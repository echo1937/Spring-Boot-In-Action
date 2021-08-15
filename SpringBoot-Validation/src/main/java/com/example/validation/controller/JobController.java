package com.example.validation.controller;


import com.example.validation.entity.Job;
import com.example.validation.common.Result;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/job")
@Validated
public class JobController {
    @PostMapping
    public Result add(@RequestBody @Valid Job job) {
        return Result.success();
    }
}
