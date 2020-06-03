package eu.nuoli.itemservice.item.entity;

import eu.nuoli.itemservice.item.domain.Item;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "item")
public class ItemEntity {
    @Id
    private String id;
    private String name;

    @CreatedDate
    private Instant created;
    @LastModifiedDate
    private Instant lastModified;

    @SuppressWarnings("unused")
    private ItemEntity() {
        // JPA needs a default constructor
    }

    public ItemEntity(Item item) {
        setDomainData(item);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Instant getCreated() {
        return created;
    }

    public Instant getLastModified() {
        return lastModified;
    }

    public Item toDomain() {
        return Item.builder()
                .id(this.getId())
                .name(this.getName())
                .build();
    }

    public final void setDomainData(Item item) {
        this.name = item.getName();
    }
}
