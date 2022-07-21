package io.kess.liquibasemongodb

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LiquibaseMongodbApplication

fun main(args: Array<String>) {
	runApplication<LiquibaseMongodbApplication>(*args)
}
