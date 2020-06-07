package eu.nuoli.itemservice.item.resource;

import eu.nuoli.itemservice.item.service.ItemService;
import eu.nuoli.itemservice.item.domain.Item;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Create a Item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item was successfully created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Item.class))})
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Item> createItem(@RequestBody @Valid Item item) {
        Item createdItem = itemService.createItem(item);

        String itemUriString = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/item/{id}").build()
                .expand(createdItem.getId()).toUriString();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add(HttpHeaders.LOCATION, itemUriString);
        return new ResponseEntity<>(createdItem, responseHeaders, HttpStatus.CREATED);
    }

    @Operation(summary = "Find a Item via id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item was found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Item.class))}),
            @ApiResponse(responseCode = "404", description = "Item was not found")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<Item> getItem(@Parameter(description = "id of item to be searched") @PathVariable("id") String id) {
        return ResponseEntity.ok(itemService.getItem(id));
    }

    @Operation(summary = "Update a Item via id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item was updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Item.class))}),
            @ApiResponse(responseCode = "404", description = "Item was not found")
    })
    @PutMapping(value = "/{id}")
    public ResponseEntity<Item> updateItem(@Parameter(description = "id of item to be updated") @PathVariable("id") String id,
                                           @RequestBody @Valid Item item) {
        return ResponseEntity.ok(itemService.updateItem(id, item));
    }

    @Operation(summary = "Delete a Item via id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Item was deleted")
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteItem(@Parameter(description = "id of item to be deleted") @PathVariable("id") String id) {
        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
