package com.zensar.dto;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Stock dto")
public class Stock {
    @ApiModelProperty(value = " Stock Id ")
    private int id;
    @ApiModelProperty(value = " Stock Name ")
    private String name;
    @ApiModelProperty(value = " Stock Market ")
    private String market;
    @ApiModelProperty(value = " Stock Price ")
    private int price;
}

//    public Stock() {
//	// TODO Auto-generated constructor stub
//    }

//    public Stock(int id, String name, String market, int price) {
//	super();
//	this.id = id;
//	this.name = name;
//	this.market = market;
//	this.price = price;
//    }
//
//    public int getId() {
//	return id;
//    }
//
//    public void setId(int id) {
//	this.id = id;
//    }
//
//    public String getName() {
//	return name;
//    }
//
//    public void setName(String name) {
//	this.name = name;
//    }
//
//    public String getMarket() {
//	return market;
//    }
//
//    public void setMarket(String market) {
//	this.market = market;
//    }
//
//    public int getPrice() {
//	return price;
//    }
//
//    public void setPrice(int price) {
//	this.price = price;
//    }
//
//    @Override
//    public String toString() {
//	return "Stock [id=" + id + ", name=" + name + ", market=" + market + ", price=" + price + "]";
//    }
