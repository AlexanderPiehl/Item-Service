package eu.nuoli.itemservice.item

import eu.nuoli.itemservice.ItemServiceApplication
import io.restassured.RestAssured
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@SpringBootTest(classes = ItemServiceApplication, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = 'test')
@EnableMongoRepositories(basePackages = ['eu.nuoli.itemservice'])
abstract class ResourceSpecificationBase extends Specification {

    @LocalServerPort
    private int port

    def setup() {
        RestAssured.port = port
    }
}
