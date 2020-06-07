package eu.nuoli.itemservice.item.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

@JsonDeserialize(builder = Item.Builder.class)
public class Item {
    @Schema(description = "Id of the Item")
    private String id;
    @Schema(description = "Name of the Item")
    @NotBlank
    private String name;

    public Item(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {
        private String id;
        private String name;

        private Builder() {
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Item build() {
            return new Item(this);
        }
    }
}
