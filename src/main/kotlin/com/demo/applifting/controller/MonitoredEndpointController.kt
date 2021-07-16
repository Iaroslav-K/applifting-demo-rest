package com.demo.applifting.controller

import com.demo.applifting.model.dto.MonitoredEndpointDTO
import com.demo.applifting.service.MonitoredEndpointService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/monitored-endpoints")
class MonitoredEndpointController(
    private val monitoredEndpointService: MonitoredEndpointService
) {

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    fun getMonitoredEndpoints() = monitoredEndpointService.getMonitoredEndpoints()

    @GetMapping("/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    fun getMonitoredEndpointById(
        @PathVariable id: Long,
    ) = monitoredEndpointService.getMonitoredEndpointById(id)

    @DeleteMapping("/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteMonitoredEndpointById(
        @PathVariable id: Long,
    ) = monitoredEndpointService.deleteMonitoredEndpointById(id)

    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    fun createMonitoredEndpoint(
        @RequestBody dto: MonitoredEndpointDTO
    ) = monitoredEndpointService.createNewMonitoredEndpoint(dto)

    @PutMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    fun updateMonitoredEndpoint(
        @RequestBody dto: MonitoredEndpointDTO
    ) = monitoredEndpointService.updateMonitoredEndpoint(dto)
}
