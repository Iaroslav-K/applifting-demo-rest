package com.demo.applifting.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/")
class LivenessProbeController {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    fun checkLiveness() {
    }
}
