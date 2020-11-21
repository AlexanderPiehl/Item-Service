package eu.nuoli.itemservice.item.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class Item {
    @Schema(description = "Id of the Item")
    private String id;
    @Schema(description = "Name of the Item")
    @NotBlank
    private final String name;
}
