package com.demo.applifting.controller

import com.demo.applifting.service.MonitoringResultService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/monitoring-results")
class MonitoringResultController(
    private val monitoringResultService: MonitoringResultService
) {

    @GetMapping("/by-monitored-endpoint/{monitoredEndpointId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    fun getLastMonitoringResultsByMonitoredEndpointId(
        @PathVariable monitoredEndpointId: Long,
    ) = monitoringResultService.getLastMonitoringResultsByMonitoredEndpointId(monitoredEndpointId)
}
