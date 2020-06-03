package eu.nuoli.itemservice.item

import eu.nuoli.itemservice.item.domain.Item
import eu.nuoli.itemservice.item.entity.ItemEntity
import eu.nuoli.itemservice.item.repository.ItemRepository
import org.springframework.beans.factory.annotation.Autowired

class ItemServiceSpec extends SpecificationBase {

    @Autowired
    ItemRepository itemRepository
    @Autowired
    ItemService itemService

    def setup() {
        itemRepository.deleteAll()
    }

    def 'createItem should create an Item'() {
        given:
        def item = Item.builder().name(name).build()

        when:
        def result = itemService.createItem(item)

        then:
        result != null
        result.id != null
        result.name == name

        where:
        name = 'name'
    }

    def 'getItem should load a Item via id'() {
        given:
        def savedEntity = itemRepository.save(new ItemEntity(Item.builder().name(name).build()))

        when:
        def result = itemService.getItem(savedEntity.id)

        then:
        result != null
        result.id == savedEntity.id
        result.name == savedEntity.name

        where:
        name = 'name'
    }

    def 'getItem should throw a IllegalArgumentException if no Item was found'() {
        when:
        itemService.getItem('id')

        then:
        def ex = thrown(IllegalArgumentException)
        ex.message == 'Item was not found'
    }

    def 'updateItem should update a Item'() {
        given:
        def savedEntity = itemRepository.save(new ItemEntity(Item.builder().name(name).build()))
        def updateItem = Item.builder().name(newName).build()

        when:
        def result = itemService.updateItem(savedEntity.id, updateItem)

        then:
        result != null
        result.id == savedEntity.id
        result.name == updateItem.name

        where:
        name = 'name'
        newName = 'name2'
    }

    def 'updateItem should throw a IllegalArgumentException if no Item was found'() {
        when:
        itemService.updateItem('id', Item.builder().build())

        then:
        def ex = thrown(IllegalArgumentException)
        ex.message == 'Item was not found'
    }

    def 'deleteItem should delete a Item'() {
        given:
        def savedEntity = itemRepository.save(new ItemEntity(Item.builder().build()))

        when:
        itemService.deleteItem(savedEntity.id)

        then:
        itemRepository.findById(savedEntity.id).isEmpty()
    }
}
