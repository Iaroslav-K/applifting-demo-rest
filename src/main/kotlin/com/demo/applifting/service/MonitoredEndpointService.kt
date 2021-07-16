package com.demo.applifting.service

import com.demo.applifting.model.dto.MonitoredEndpointConverter
import com.demo.applifting.model.dto.MonitoredEndpointDTO
import com.demo.applifting.repository.MonitoredEndpointRepository
import com.demo.applifting.repository.MonitoringResultRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


@Service
class MonitoredEndpointService(
    private val monitoredEndpointRepository: MonitoredEndpointRepository,
    private val monitoringResultRepository: MonitoringResultRepository,
    private val monitoredEndpointConverter: MonitoredEndpointConverter,
) {

    fun getMonitoredEndpoints(): List<MonitoredEndpointDTO> {
        val userEmail = SecurityContextHolder.getContext().authentication.name
        return monitoredEndpointRepository
            .findAllByOwnerEmail(userEmail)
            .map { monitoredEndpointConverter.convertToDTO(it) }
    }

    fun deleteMonitoredEndpointById(id: Long) {
        val userEmail = SecurityContextHolder.getContext().authentication.name
        monitoredEndpointRepository.deleteByMonitoredEndpointIdAndOwnerEmail(id, userEmail)
    }

    fun getMonitoredEndpointById(id: Long): MonitoredEndpointDTO {
        val userEmail = SecurityContextHolder.getContext().authentication.name
        return monitoredEndpointConverter.convertToDTO(
            monitoredEndpointRepository.findByMonitoredEndpointIdAndOwnerEmail(id, userEmail)
                ?: throw NoSuchElementException()
        )
    }

    fun createNewMonitoredEndpoint(dto: MonitoredEndpointDTO): MonitoredEndpointDTO {
        val userEmail = SecurityContextHolder.getContext().authentication.name
        val newMonitoredEndpoint = monitoredEndpointConverter.convertToEntity(
            dto.setDateOfCreation(LocalDateTime.now()),
            userEmail
        )
        monitoredEndpointRepository.save(newMonitoredEndpoint)
        val endpointMonitorer = EndpointMonitorer(
            monitoredEndpointRepository,
            monitoringResultRepository,
            newMonitoredEndpoint.monitoredEndpointId!!
        )
        val executor = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors())
        executor.scheduleAtFixedRate(endpointMonitorer, 0, 1, TimeUnit.SECONDS)
        return monitoredEndpointConverter.convertToDTO(newMonitoredEndpoint)
    }

    fun updateMonitoredEndpoint(dto: MonitoredEndpointDTO): MonitoredEndpointDTO {
        val userEmail = SecurityContextHolder.getContext().authentication.name
        if (dto.monitoredEndpointId == null)
            throw NoSuchElementException()
        val monitoredEndpoint = monitoredEndpointRepository
            .findByMonitoredEndpointIdAndOwnerEmail(dto.monitoredEndpointId, userEmail)
        val changedMonitoredEndpoint = monitoredEndpoint
            ?.setNewName(dto.name) ?: throw NoSuchElementException()
        monitoredEndpointRepository.save(changedMonitoredEndpoint)
        return monitoredEndpointConverter.convertToDTO(changedMonitoredEndpoint)
    }
}
