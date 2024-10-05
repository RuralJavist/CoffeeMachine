package com.coffeemachine.dto.client_api;

import com.coffeemachine.dto.ReceiptDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;
import java.util.List;


@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@NoArgsConstructor
@Getter
@Setter
public class DrinkResponseDto implements Serializable {
    @Schema(example = "1")
    @JsonProperty("id")
    private Long coffeeId;

    @Schema(example = "latte")
    @JsonProperty("name")
    private String coffeeName;

    @JsonProperty("ingredients")
    private List<ReceiptDto> receiptList;
}