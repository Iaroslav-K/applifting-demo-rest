package com.demo.applifting.model.dto

import java.time.LocalDateTime

data class MonitoredEndpointDTO(
    val monitoredEndpointId: Long?,
    val name: String,
    val url: String,
    val dateOfCreation: LocalDateTime?,
    val dateOfLastCheck: LocalDateTime?,
    val monitoredInterval: Int,
) {
    fun setDateOfCreation(date: LocalDateTime) =
        MonitoredEndpointDTO(
            monitoredEndpointId,
            name,
            url,
            date,
            dateOfLastCheck,
            monitoredInterval
        )
}
