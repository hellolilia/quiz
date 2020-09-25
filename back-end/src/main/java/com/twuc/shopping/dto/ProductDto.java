package com.twuc.shopping.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String price;
    private String unit;
    private String image;
}
