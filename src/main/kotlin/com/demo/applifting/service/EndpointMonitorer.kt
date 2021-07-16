package com.demo.applifting.service

import com.demo.applifting.model.entity.MonitoringResult
import com.demo.applifting.repository.MonitoredEndpointRepository
import com.demo.applifting.repository.MonitoringResultRepository
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient
import java.time.LocalDateTime

class EndpointMonitorer(
    private val monitoredEndpointRepository: MonitoredEndpointRepository,
    private val monitoringResultRepository: MonitoringResultRepository,
    private val monitoredEndpointId: Long
) : Runnable {

    override fun run() {
        val monitoredEndpoint = monitoredEndpointRepository.findById(monitoredEndpointId)
            .orElseThrow { NoSuchElementException() }
        if (monitoredEndpoint.dateOfCreation.plusSeconds(
                monitoredEndpoint.monitoredInterval.toLong()
            ).isBefore(LocalDateTime.now())
        ) {
            throw Exception()
        }
        val webClient = WebClient.create(monitoredEndpoint.url)
        val getResponse = webClient
            .get()
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .toEntity(String::class.java)
            .block()!!
        val checkTime = LocalDateTime.now()
        monitoringResultRepository.save(
            MonitoringResult(
                null,
                checkTime,
                getResponse.statusCode.value(),
                // should by used BLOB to fit all data
                getResponse.body?.substring(0, 100) ?: "",
                monitoredEndpoint
            )
        )
        monitoredEndpointRepository.save(
            monitoredEndpoint.setDateOfLastCheck(checkTime)
        )
    }
}