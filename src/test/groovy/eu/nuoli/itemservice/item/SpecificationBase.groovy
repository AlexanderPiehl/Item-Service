package eu.nuoli.itemservice.item

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import spock.lang.Specification

@ContextConfiguration(classes = TestConfiguration)
@ActiveProfiles(profiles = 'test')
@WebAppConfiguration
abstract class SpecificationBase extends Specification {

    @ComponentScan(basePackages = ['eu.nuoli.itemservice'])
    @EnableMongoRepositories(basePackages = ['eu.nuoli.itemservice'])
    @EnableAutoConfiguration
    static class TestConfiguration {}

}
