package com.demo.applifting.service

import com.demo.applifting.model.dto.MonitoringResultConverter
import com.demo.applifting.model.dto.MonitoringResultDTO
import com.demo.applifting.repository.MonitoredEndpointRepository
import com.demo.applifting.repository.MonitoringResultRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class MonitoringResultService(
    private val monitoringResultRepository: MonitoringResultRepository,
    private val monitoredEndpointRepository: MonitoredEndpointRepository,
    private val monitoringResultConverter: MonitoringResultConverter,
) {

    fun getLastMonitoringResultsByMonitoredEndpointId(monitoredEndpointId: Long): List<MonitoringResultDTO> {
        val userEmail = SecurityContextHolder.getContext().authentication.name
        if (!monitoredEndpointRepository.existsByMonitoredEndpointIdAndOwnerEmail(monitoredEndpointId, userEmail))
            throw NoSuchElementException("Endpoint does not exist or you have no access")
        return monitoringResultRepository
            .findTop10ByMonitoredEndpointMonitoredEndpointId(monitoredEndpointId)
            .map { monitoringResultConverter.convertToDTO(it) }
    }
}
