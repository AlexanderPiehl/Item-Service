package eu.nuoli.itemservice.item.repository;

import eu.nuoli.itemservice.item.entity.ItemEntity;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<ItemEntity, String> {
}
