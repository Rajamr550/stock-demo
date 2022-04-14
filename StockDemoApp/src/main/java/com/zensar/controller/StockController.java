package com.zensar.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.dto.Stock;
import com.zensar.entity.StockDocument;
import com.zensar.service.StockService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/stocks")
@CrossOrigin(origins = "*")

public class StockController {
    @Autowired
    StockService stockService;

    @GetMapping(value = "/stock", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ApiOperation(value = "Reads all stocks", notes = "This REST API returns list of all stocks")

    public List<Stock> getAllStocks() {
	return stockService.getAllStocks();
    }

    @GetMapping(value = "/stock/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ApiOperation(value = "Reads specific stock", notes = "This REST API returns list the stock of given id")

    public Optional<StockDocument> getStockById(@PathVariable("id") int stockId) {
	return stockService.getStockById2(stockId);
    }

    @DeleteMapping(value = "/stock/{id}")
    @ApiOperation(value = "Deletes the stock by id", notes = "Deletes the stock by id")

    public boolean deleteStockById(@PathVariable("id") int stockId) {
	return stockService.deleteStocksById(stockId);
    }

    @DeleteMapping(value = "/stock")
    @ApiOperation(value = "Deletes the stock list", notes = "Deletes all the stock")

    public boolean deleteAllStocks() {
	return stockService.deleteAllStocks();

    }

    @PostMapping(value = "/stock", consumes = { MediaType.APPLICATION_JSON_VALUE,
	    MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
		    MediaType.APPLICATION_XML_VALUE })
    @ApiOperation(value = "create a new stock", notes = "create a stock")

    public Stock createNewStock(@RequestBody Stock stock) {
	return stockService.createNewStock(stock);
    }

    @PutMapping(value = "/stock/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE,
	    MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
		    MediaType.APPLICATION_XML_VALUE })
    @ApiOperation(value = "ediitng a stockc", notes = "edit a stock")

    public Stock updateStock(@PathVariable("id") int stockId, @RequestBody Stock updatedStock) {
	return stockService.updateStock(stockId, updatedStock);
    }

    @GetMapping(value = "/employee")
    public boolean testRequestParam(@RequestParam(value = "eid", required = false) int empId) {
	System.out.println("emp id " + empId);
	return true;
    }

    @GetMapping(value = "/employee2")
    public boolean testHeaderParam(@RequestHeader(value = "auth-token") String authToken) {
	System.out.println("Auth Token " + authToken);
	return true;
    }

    // customized 8/march - sorting n paging

    @GetMapping(value = "/stock/name/{name}", produces = { MediaType.APPLICATION_JSON_VALUE,
	    MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<List<Stock>> getStocksByName(@PathVariable("name") String stockName) {
	return new ResponseEntity<List<Stock>>(stockService.getStocksByName(stockName), HttpStatus.OK);
    }

    @GetMapping(value = "/stock/market/{market}", produces = { MediaType.APPLICATION_JSON_VALUE,
	    MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<List<Stock>> getStocksByMarket(@PathVariable("market") String stockMarket) {
	return new ResponseEntity<List<Stock>>(stockService.getStocksByMarket(stockMarket), HttpStatus.OK);
    }

    // sort
    @GetMapping(value = "/stock/sort/{sortType}", produces = { MediaType.APPLICATION_JSON_VALUE,
	    MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<List<Stock>> getStocksSortedByName(@PathVariable("sortType") String sortType) {
	return new ResponseEntity<List<Stock>>(stockService.getStocksSortedByName(sortType), HttpStatus.OK);
    }

    @GetMapping(value = "/stock/{startIndex}/{records}", produces = { MediaType.APPLICATION_JSON_VALUE,
	    MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<List<Stock>> getStocksByPage(@PathVariable("startIndex") int startIndex,
	    @PathVariable("records") int records) {
	return new ResponseEntity<List<Stock>>(stockService.getStocksByPage(startIndex, records), HttpStatus.OK);
    }

    @GetMapping(value = "/stock/like/{namelike}", produces = { MediaType.APPLICATION_JSON_VALUE,
	    MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<List<Stock>> getStocksByNameLike(@PathVariable("namelike") String namelike) {
	return new ResponseEntity<List<Stock>>(stockService.getStocksByNameLike(namelike), HttpStatus.OK);
    }

}
