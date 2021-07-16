package com.demo.applifting.model.dto

import com.demo.applifting.model.entity.MonitoringResult
import org.springframework.stereotype.Component

@Component
class MonitoringResultConverter {

    fun convertToDTO(entity: MonitoringResult) =
        MonitoringResultDTO(
            entity.monitoringResultId,
            entity.dateOfCheck,
            entity.returnedHttpStatusCode,
            entity.returnedPayload,
            entity.monitoredEndpoint.monitoredEndpointId!!
        )
}
