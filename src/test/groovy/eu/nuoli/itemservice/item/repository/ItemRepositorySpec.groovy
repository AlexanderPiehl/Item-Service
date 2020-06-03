package eu.nuoli.itemservice.item.repository

import eu.nuoli.itemservice.item.SpecificationBase
import eu.nuoli.itemservice.item.domain.Item
import eu.nuoli.itemservice.item.entity.ItemEntity
import org.springframework.beans.factory.annotation.Autowired

import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class ItemRepositorySpec extends SpecificationBase {
    def formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss[.SSS]").withZone(ZoneOffset.UTC)

    @Autowired
    ItemRepository itemRepository

    def setup() {
        itemRepository.deleteAll()
    }

    def 'insert new item'() {
        given:
        def item = Item.builder().name(name).build()

        when:
        def result = itemRepository.save(new ItemEntity(item))

        then:
        result != null
        result.id != null
        result.name == name

        result.created != null
        result.lastModified == result.created

        where:
        name = 'name'
    }

    def 'update item'() {
        given:
        def savedEntity = itemRepository.save(new ItemEntity(
                Item.builder().name(name).build()
        ))

        def updateItem = Item.builder().name(newName).build()
        def entity = itemRepository.findById(savedEntity.id).get()
        entity.domainData = updateItem

        when:
        def result = itemRepository.save(entity)

        then:
        result != null
        result.id == savedEntity.id
        result.name == newName

        formatter.format(result.lastModified) != formatter.format(savedEntity.lastModified)
        result.lastModified.isAfter(savedEntity.lastModified)
        formatter.format(result.created) == formatter.format(savedEntity.created)

        where:
        name = 'name'
        newName = 'name2'
    }
}
