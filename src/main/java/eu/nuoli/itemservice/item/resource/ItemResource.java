package eu.nuoli.itemservice.item.resource;

import eu.nuoli.itemservice.item.service.ItemService;
import eu.nuoli.itemservice.item.domain.Item;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/item", produces = MediaType.APPLICATION_JSON_VALUE)
public class ItemResource {
    private final ItemService itemService;

    public ItemResource(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Item> createItem(@RequestBody @Valid Item item) {
        Item createdItem = itemService.createItem(item);

        String itemUriString = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/item/{id}").build()
                .expand(createdItem.getId()).toUriString();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add(HttpHeaders.LOCATION, itemUriString);
        return new ResponseEntity<>(createdItem, responseHeaders, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Item> getItem(@PathVariable("id") String id) {
        return ResponseEntity.ok(itemService.getItem(id));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable("id") String id, @RequestBody @Valid Item item) {
        return ResponseEntity.ok(itemService.updateItem(id, item));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable("id") String id) {
        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
