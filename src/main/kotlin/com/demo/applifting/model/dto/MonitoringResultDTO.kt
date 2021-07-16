package com.demo.applifting.model.dto

import java.time.LocalDateTime

data class MonitoringResultDTO(
    val monitoringResultId: Long?,
    val dateOfCheck: LocalDateTime,
    val returnedHttpStatusCode: Int,
    val returnedPayload: String,
    val monitoredEndpointId: Long,
)
