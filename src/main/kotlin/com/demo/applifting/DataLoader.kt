package com.demo.applifting

import com.demo.applifting.model.entity.User
import com.demo.applifting.repository.UserRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class DataLoader(
    private val userRepository: UserRepository,
) : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {
        userRepository.saveAll(
            listOf(
                User(
                    null,
                    "Applifting",
                    "info@applifting.cz",
                    "93f39e2f-80de-4033-99ee-249d92736a25"
                ),
                User(
                    null,
                    "Batman",
                    "batman@example.com",
                    "dcb20f8a-5657-4f1b-9f7f-ce65739b359e"
                )
            )
        )
    }
}
