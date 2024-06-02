package com.itbank.smartFarm.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemVO {
    private int id;
    private String name;
    private String description;
    private int price;
    private int stock;
    private String category;
    private String createdAt;
    private String updatedAt;
}
