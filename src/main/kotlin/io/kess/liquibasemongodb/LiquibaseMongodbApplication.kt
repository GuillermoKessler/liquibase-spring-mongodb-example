package io.kess.liquibasemongodb

import liquibase.Contexts
import liquibase.Liquibase
import liquibase.database.Database
import liquibase.database.DatabaseFactory
import liquibase.resource.ClassLoaderResourceAccessor
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource

@SpringBootApplication
class LiquibaseMongodbApplication

fun main(args: Array<String>) {
    runApplication<LiquibaseMongodbApplication>(*args)
}

@Configuration
class LiquibaseConfiguration : InitializingBean {
    @Value("\${spring.liquibase.url}")
    private val uri: String? = null

    @Value("\${spring.liquibase.change-log}")
    private lateinit var resource: Resource

    @Throws(Exception::class)
    override fun afterPropertiesSet() {
        val openDatabase: Database = DatabaseFactory.getInstance().openDatabase(uri, null, null, null, null, null)
        val changelogMaster = (resource as ClassPathResource).path
        Liquibase(
            changelogMaster,
            ClassLoaderResourceAccessor(),
            openDatabase
        ).use { liquibase -> liquibase.update(Contexts()) }
    }
}
