package com.demo.applifting

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles(profiles = ["test"])
@SpringBootTest
class DemoRestApplicationTests {

    @Test
    fun contextLoads() {
    }

}
