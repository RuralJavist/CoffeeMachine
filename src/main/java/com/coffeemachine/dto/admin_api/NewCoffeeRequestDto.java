package com.coffeemachine.dto.admin_api;

import com.coffeemachine.dto.ReceiptDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.io.Serializable;
import java.util.List;


@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NewCoffeeRequestDto implements Serializable {

    @Pattern(regexp = "^[a-zA-Z\\s]{3,15}$", message = "Invalid coffee name")
    @NotNull(message = "Coffee name can`t be null")
    @Schema(example = "latte")
    private String name;

    @NotEmpty(message = "Receipt data can`t be empty")
    @NotNull(message = "Drinks data can`t be null")
    @Valid
    @JsonProperty("receipt_components")
    private List<ReceiptDto> receipts;
}