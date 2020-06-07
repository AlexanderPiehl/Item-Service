package eu.nuoli.itemservice.item.resource

import eu.nuoli.itemservice.item.ResourceSpecificationBase
import eu.nuoli.itemservice.item.domain.Item
import eu.nuoli.itemservice.item.entity.ItemEntity
import eu.nuoli.itemservice.item.repository.ItemRepository
import org.hamcrest.Matchers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus

import static io.restassured.RestAssured.given
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE

class ItemResourceSpec extends ResourceSpecificationBase {

    @Autowired
    private ItemRepository itemRepository

    def setup() {
        itemRepository.deleteAll()
    }

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
                .statusCode(HttpStatus.CREATED.value())
                .body('id', Matchers.notNullValue())
                .body('name', Matchers.equalTo(name))

        where:
        name = 'name'
    }

    def 'getItem should return a Item for the given id'() {
        given:
        def savedEntity = itemRepository.save(new ItemEntity(Item.builder().name(name).build()))
        def request = given()
                .accept(APPLICATION_JSON_VALUE)
                .contentType(APPLICATION_JSON_VALUE)
                .log().all()

        when:
        def response = request.with().get('/item/{id}', savedEntity.id)

        then:
        response.then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body('id', Matchers.equalTo(savedEntity.id))
                .body('name', Matchers.equalTo(savedEntity.name))

        where:
        name = 'name'
    }

    def 'getItem should return 404 if no Item was found'() {
        given:
        def request = given()
                .accept(APPLICATION_JSON_VALUE)
                .contentType(APPLICATION_JSON_VALUE)
                .log().all()

        when:
        def response = request.with().get('/item/{id}', '1234')

        then:
        response.then().log().all()
                .statusCode(HttpStatus.NOT_FOUND.value())
    }

    def 'updateItem should update a Item for the given id'() {
        given:
        def savedEntity = itemRepository.save(new ItemEntity(Item.builder().name(name).build()))

        def updateItem = Item.builder().name(newName).build()

        def request = given()
                .accept(APPLICATION_JSON_VALUE)
                .contentType(APPLICATION_JSON_VALUE)
                .body(updateItem)
                .log().all()

        when:
        def response = request.with().put('/item/{id}', savedEntity.id)

        then:
        response.then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body('id', Matchers.equalTo(savedEntity.id))
                .body('name', Matchers.equalTo(updateItem.name))

        where:
        name = 'name'
        newName = 'name2'
    }

    def 'updateItem should return 404 if no Item was found'() {
        given:
        def updateItem = Item.builder().name('name').build()

        def request = given()
                .accept(APPLICATION_JSON_VALUE)
                .contentType(APPLICATION_JSON_VALUE)
                .body(updateItem)
                .log().all()

        when:
        def response = request.with().put('/item/{id}', '1234')

        then:
        response.then().log().all()
                .statusCode(HttpStatus.NOT_FOUND.value())
    }

    def 'deleteItem should delete a Item and return no content'() {
        given:
        def savedEntity = itemRepository.save(new ItemEntity(Item.builder().name('name').build()))

        def request = given()
                .accept(APPLICATION_JSON_VALUE)
                .contentType(APPLICATION_JSON_VALUE)
                .log().all()

        when:
        def response = request.with().delete('/item/{id}', savedEntity.id)

        then:
        response.then().log().all()
                .statusCode(HttpStatus.NO_CONTENT.value())
    }
}
