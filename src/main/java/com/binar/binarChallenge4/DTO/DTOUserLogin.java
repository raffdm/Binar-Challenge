package com.binar.binarChallenge4.DTO;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class DTOUserLogin {

    @NotNull
    private String username;

    @NotNull
    private String password;

}
