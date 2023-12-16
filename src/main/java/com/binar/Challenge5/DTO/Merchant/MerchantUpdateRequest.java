package com.binar.Challenge5.DTO.Merchant;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class MerchantUpdateRequest {

    @NotBlank(message = "Merchant name cannot be blank")
    private String merchantName;

    @NotBlank(message = "Merchant Location cannot be blank")
    private String merchantLocation;

    @NotNull
    private boolean open;
}
