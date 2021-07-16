package com.demo.applifting.model.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class MonitoringResult(
        @Id
        @GeneratedValue
        val monitoringResultId: Long?,
        val dateOfCheck: LocalDateTime,
        val returnedHttpStatusCode: Int,
        val returnedPayload: String,
        @ManyToOne(fetch = FetchType.EAGER, targetEntity = MonitoredEndpoint::class)
        @JoinColumn(name = "monitored_endpoint_id", nullable = false)
        val monitoredEndpoint: MonitoredEndpoint,
)
