package com.twuc.shopping.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @NotNull
    private String name;

    @NotNull
    @Pattern(regexp = "(?!^0*(\\.0{1,2})?$)^\\d{1,13}(\\.\\d{1,2})?$")
    private String price;

    @NotNull
    private String unit;

    @NotNull
    @Pattern(regexp = "https?://.+")
    private String image;
}
