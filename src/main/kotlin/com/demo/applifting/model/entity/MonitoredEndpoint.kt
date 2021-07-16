package com.demo.applifting.model.entity

import java.time.LocalDateTime
import javax.persistence.*

/**
 * @property monitoredInterval in seconds
 */
@Entity
data class MonitoredEndpoint(
    @Id
    @GeneratedValue
    val monitoredEndpointId: Long?,
    val name: String,
    val url: String,
    val dateOfCreation: LocalDateTime,
    val dateOfLastCheck: LocalDateTime?,
    val monitoredInterval: Int,
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = User::class)
    @JoinColumn(name = "user_id", nullable = false)
    val owner: User,
) {
    fun setDateOfLastCheck(newDate: LocalDateTime) = MonitoredEndpoint(
        this.monitoredEndpointId,
        this.name,
        this.url,
        this.dateOfCreation,
        newDate,
        this.monitoredInterval,
        this.owner
    )

    fun setNewName(newName: String) = MonitoredEndpoint(
        this.monitoredEndpointId,
        newName,
        this.url,
        this.dateOfCreation,
        this.dateOfLastCheck,
        this.monitoredInterval,
        this.owner
    )
}
