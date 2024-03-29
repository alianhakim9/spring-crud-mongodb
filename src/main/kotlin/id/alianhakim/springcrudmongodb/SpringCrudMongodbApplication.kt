package id.alianhakim.springcrudmongodb

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class SpringCrudMongodbApplication

fun main(args: Array<String>) {
    runApplication<SpringCrudMongodbApplication>(*args)
}
