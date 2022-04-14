package com.zensar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.zensar.dto.Stock;
import com.zensar.entity.StockDocument;
import com.zensar.entity.StockEntity;
import com.zensar.exception.InvalidStockIdException;
import com.zensar.entity.StockDocument;
import com.zensar.entity.StockDocument;
import com.zensar.entity.StockDocument;
import com.zensar.repository.StockMongoRepo;

import com.zensar.exception.*;

@Service
@Primary
public class StockServiceMongoImpl implements StockService {
    @Autowired
    StockMongoRepo stockRepo;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public Stock updateStock(int stockId, Stock updateStock) {
	Optional<StockDocument> opStockDocument = stockRepo.findById(stockId);
	if (opStockDocument.isPresent()) {
	    StockDocument stockDocument = opStockDocument.get();
	    stockDocument.setMarket(updateStock.getMarket());
	    stockDocument.setName(updateStock.getName());
	    stockDocument.setPrice(updateStock.getPrice());
	    stockRepo.save(stockDocument);
	}
	return null;
    }

    @Override
    public Boolean deleteAllStocks() {
	stockRepo.deleteAll();
	return true;
    }

    @Override
    public Boolean deleteStocksById(int stockId) {
	if (stockRepo.existsById(stockId)) {
	    stockRepo.deleteById(stockId);
	    return true;

	}

	return false;
    }

    @Override
    public Stock createNewStock(Stock stock) {
	StockDocument stockDocument = convertDTOIntoDocument(stock);
	stockDocument = stockRepo.save(stockDocument);
	return convertDocumentIntoDTO(stockDocument);
    }

    @Override
    public List<Stock> getAllStocks() {
	List<StockDocument> stockDocumentList = stockRepo.findAll();
	List<Stock> stockDtoList = new ArrayList<Stock>();
	for (StockDocument stockDocument : stockDocumentList) {
	    Stock stock = convertDocumentIntoDTO(stockDocument);
	    stockDtoList.add(stock);
	}
	return stockDtoList;
    }

//    @Override
//    public Optional<StockDocument> getStockById(int StockId) {
//	Optional<StockDocument> sDocument = stockRepo.findById(stockId);
//	return sDocument;
//    }

    @Override
    public List<Stock> getStocksByName(String stockName) {
	List<StockDocument> stockDocumentList = stockRepo.findByName(stockName);
	List<Stock> stockDtoList = new ArrayList<Stock>();
	for (StockDocument stockDocument : stockDocumentList) {
	    Stock stock = convertDocumentIntoDTO(stockDocument);
	    stockDtoList.add(stock);
	}
	return stockDtoList;
    }

    @Override
    public List<Stock> getStocksSortedByName(String sortType) {
	List<StockDocument> stockDocumentList = null;
	if ("ASC".equalsIgnoreCase(sortType))
	    stockDocumentList = stockRepo.findByOrderByNameAsc();

	else
	    stockDocumentList = stockRepo.findByOrderByNameDesc();

	List<Stock> stockDtoList = new ArrayList<Stock>();
	for (StockDocument stockDocument : stockDocumentList) {
	    Stock stock = convertDocumentIntoDTO(stockDocument);
	    stockDtoList.add(stock);
	}

	return stockDtoList;
    }

    @Override
    public List<Stock> getStocksByPage(int startIndex, int records) {
	Pageable myPageable = PageRequest.of(startIndex, records);
	Page<StockDocument> stockDocumentPage = stockRepo.findAll(myPageable);
	List<StockDocument> stockDocumentList = stockDocumentPage.getContent();

	List<Stock> stockDtoList = new ArrayList<Stock>();
	for (StockDocument stockDocument : stockDocumentList) {
	    Stock stock = convertDocumentIntoDTO(stockDocument);
	    stockDtoList.add(stock);
	}
	return stockDtoList;
    }

    @Override
    public List<Stock> getStocksByNameLike(String nameLike) {
	List<StockDocument> stockDocumentList = stockRepo.findByNameContains(nameLike);
	List<Stock> stockDtoList = new ArrayList<Stock>();
	for (StockDocument stockDocument : stockDocumentList) {
	    Stock stock = convertDocumentIntoDTO(stockDocument);
	    stockDtoList.add(stock);
	}
	return stockDtoList;
    }

    @Override
    public List<Stock> getStocksByMarket(String stockMarket) {
	List<StockDocument> stockDocumentList = stockRepo.findByMarket(stockMarket);
	List<Stock> stockDtoList = new ArrayList<Stock>();
	for (StockDocument stockDocument : stockDocumentList) {
	    Stock stock = convertDocumentIntoDTO(stockDocument);
	    stockDtoList.add(stock);
	}
	return stockDtoList;
    }

    private StockDocument convertDTOIntoDocument(Stock stock) {
	TypeMap<Stock, StockDocument> tMap = modelMapper.typeMap(Stock.class, StockDocument.class);
	StockDocument stockDocument = modelMapper.map(stock, StockDocument.class);
	return stockDocument;
    }

    private Stock convertDocumentIntoDTO(StockDocument stockDocument) {
	TypeMap<StockDocument, Stock> tMap = modelMapper.typeMap(StockDocument.class, Stock.class);
	Stock stock = modelMapper.map(stockDocument, Stock.class);
	return stock;
    }

    @Override
    public Optional<StockDocument> getStockById2(int StockId) {
	Optional<StockDocument> sDocument = stockRepo.findById(StockId);
	throw new InvalidStockIdException("" + StockId);
    }

    @Override
    public Optional<StockEntity> getStockById(int StockId) {
	// TODO Auto-generated method stub
	return null;
    }

}
