package eu.nuoli.itemservice.item.controller

import eu.nuoli.itemservice.item.ResourceSpecificationBase
import eu.nuoli.itemservice.item.domain.Item
import org.hamcrest.Matchers

import static io.restassured.RestAssured.given
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE

class ItemResourceSpec extends ResourceSpecificationBase {

    def 'createItem should create a Item and return status code 201'() {
        given:
        def item = Item.builder().name(name).build()
        def request = given()
                .accept(APPLICATION_JSON_VALUE)
                .contentType(APPLICATION_JSON_VALUE)
                .body(item)
                .log().all()

        when:
        def response = request.with().post('/item')

        then:
        response.then().log().all()
                .statusCode(201)
                .body('id', Matchers.notNullValue())
                .body('name', Matchers.equalTo(name))

        where:
        name = 'name'
    }
}
