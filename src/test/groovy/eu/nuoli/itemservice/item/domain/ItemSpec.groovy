package eu.nuoli.itemservice.item.domain

import spock.lang.Specification

class ItemSpec extends Specification {
    def 'builder with defaults'() {
        when:
        def item = Item.builder().build()

        then:
        item != null
        item.id == null
        item.name == null
    }

    def 'builder with all values set'() {
        when:
        def item = Item.builder().id(id).name(name).build()

        then:
        item != null
        item.id == id
        item.name == name

        where:
        id = 'id'
        name = 'name'
    }
}
