package com.demo.applifting.repository

import com.demo.applifting.model.entity.MonitoredEndpoint
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MonitoredEndpointRepository : CrudRepository<MonitoredEndpoint, Long> {

    fun findAllByOwnerEmail(email: String): List<MonitoredEndpoint>

    fun deleteByMonitoredEndpointIdAndOwnerEmail(monitoredEndpointId: Long, email: String)

    fun findByMonitoredEndpointIdAndOwnerEmail(monitoredEndpointId: Long, email: String): MonitoredEndpoint?

    fun existsByMonitoredEndpointIdAndOwnerEmail(monitoredEndpointId: Long, email: String): Boolean
}
