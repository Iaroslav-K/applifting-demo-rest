package com.demo.applifting.model.dto

import com.demo.applifting.model.entity.MonitoredEndpoint
import com.demo.applifting.repository.UserRepository
import org.springframework.stereotype.Component

@Component
class MonitoredEndpointConverter(
    private val userRepository: UserRepository
) {
    fun convertToDTO(entity: MonitoredEndpoint) =
        MonitoredEndpointDTO(
            entity.monitoredEndpointId,
            entity.name,
            entity.url,
            entity.dateOfCreation,
            entity.dateOfLastCheck,
            entity.monitoredInterval
        )

    fun convertToEntity(dto: MonitoredEndpointDTO, userEmail: String): MonitoredEndpoint =
        MonitoredEndpoint(
            null,
            dto.name,
            dto.url,
            dto.dateOfCreation
                ?: throw IllegalArgumentException("Empty ME creation date"),
            dto.dateOfLastCheck,
            dto.monitoredInterval,
            userRepository.findByEmail(userEmail)
                ?: throw IllegalArgumentException("Owner of ME does not exist")
        )
}
