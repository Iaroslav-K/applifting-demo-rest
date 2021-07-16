package com.demo.applifting.repository

import com.demo.applifting.model.entity.MonitoringResult
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MonitoringResultRepository : CrudRepository<MonitoringResult, Long> {

    fun findTop10ByMonitoredEndpointMonitoredEndpointId(monitoredEndpointId: Long): List<MonitoringResult>
}
