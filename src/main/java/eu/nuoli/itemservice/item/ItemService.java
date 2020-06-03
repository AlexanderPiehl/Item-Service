package eu.nuoli.itemservice.item;

import eu.nuoli.itemservice.item.domain.Item;
import eu.nuoli.itemservice.item.entity.ItemEntity;
import eu.nuoli.itemservice.item.repository.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item createItem(Item item) {
        item.setId(null);
        return itemRepository.save(new ItemEntity(item)).toDomain();
    }

    public Item getItem(String id) {
        return itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Item was not found")).toDomain();
    }

    public Item updateItem(String id, Item item) {
        ItemEntity itemEntity = itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Item was not found"));
        itemEntity.setDomainData(item);

        return itemRepository.save(itemEntity).toDomain();
    }

    public void deleteItem(String id) {
        itemRepository.deleteById(id);
    }
}
