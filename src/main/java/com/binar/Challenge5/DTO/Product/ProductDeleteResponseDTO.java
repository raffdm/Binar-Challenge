package com.binar.Challenge5.DTO.Product;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductDeleteResponseDTO {

    private String message;

    private String errors;
}
