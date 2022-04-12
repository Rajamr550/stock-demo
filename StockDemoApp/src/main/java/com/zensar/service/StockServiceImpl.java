package com.zensar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.zensar.dto.Stock;
import com.zensar.entity.StockEntity;
import com.zensar.repository.StockRepo;

//@Service
public class StockServiceImpl implements StockService {
    @Autowired
    StockRepo stockRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public Boolean deleteAllStocks() {
	stockRepo.deleteAll();
	return true;

    }

    @Override
    public Boolean deleteStocksById(int stockId) {
	// TODO Auto-generated method stub
	if (stockRepo.existsById(stockId)) {
	    stockRepo.deleteById(stockId);
	    return true;

	}

	return false;
    }

    @Override
    public Stock createNewStock(Stock stock) {
	StockEntity stockEntity = convertDTOIntoEntity(stock);
	stockEntity = stockRepo.save(stockEntity);
	return convertEntityIntoDTO(stockEntity);

    }

    @Override
    public List<Stock> getAllStocks() {
	List<StockEntity> stockEntityList = stockRepo.findAll();
	List<Stock> stockDtoList = new ArrayList<Stock>();
	for (StockEntity stockEntity : stockEntityList) {
	    Stock stock = convertEntityIntoDTO(stockEntity);
	    stockDtoList.add(stock);
	}
	return stockDtoList;

    }

    @Override
    public Stock updateStock(int stockId, Stock updateStock) {
	Optional<StockEntity> opStockEntity = stockRepo.findById(stockId);
	if (opStockEntity.isPresent()) {
	    StockEntity stockEntity = opStockEntity.get();
	    stockEntity.setMarket(updateStock.getMarket());
	    stockEntity.setName(updateStock.getName());
	    stockEntity.setPrice(updateStock.getPrice());
	    stockRepo.save(stockEntity);
	}
	return null;

    }

//    @Override
//    public Optional<StockEntity> getStockById(int stockId) {
//	Optional<StockEntity> sEntity = stockRepo.findById(stockId);
//	return sEntity;
//
//    }

    @Override
    public List<Stock> getStocksByName(String stockName) {
	List<StockEntity> stockEntityList = stockRepo.getStocksByNameSQL(stockName);
	List<Stock> stockDtoList = new ArrayList<Stock>();
	for (StockEntity stockEntity : stockEntityList) {
	    Stock stock = convertEntityIntoDTO(stockEntity);
	    stockDtoList.add(stock);
	}
	return stockDtoList;
    }

    // get name like

    @Override
    public List<Stock> getStocksByNameLike(String nameLike) {
	List<StockEntity> stockEntityList = stockRepo.getByNameLikeSQL(nameLike);
	List<Stock> stockDtoList = new ArrayList<Stock>();
	for (StockEntity stockEntity : stockEntityList) {
	    Stock stock = convertEntityIntoDTO(stockEntity);
	    stockDtoList.add(stock);
	}
	return stockDtoList;
    }

    @Override
    public List<Stock> getStocksByMarket(String stockMarket) {
	List<StockEntity> stockEntityList = stockRepo.findByMarket(stockMarket);
	List<Stock> stockDtoList = new ArrayList<Stock>();
	for (StockEntity stockEntity : stockEntityList) {
	    Stock stock = convertEntityIntoDTO(stockEntity);
	    stockDtoList.add(stock);
	}
	return stockDtoList;
    }

    @Override
    public List<Stock> getStocksSortedByName(String sortType) {
	List<StockEntity> stockEntityList = null;
	if ("ASC".equalsIgnoreCase(sortType))
	    stockEntityList = stockRepo.findByOrderByNameAsc();

	else
	    stockEntityList = stockRepo.findByOrderByNameDesc();

	List<Stock> stockDtoList = new ArrayList<Stock>();
	for (StockEntity stockEntity : stockEntityList) {
	    Stock stock = convertEntityIntoDTO(stockEntity);
	    stockDtoList.add(stock);
	}

	return stockDtoList;
    }

    @Override
    public List<Stock> getStocksByPage(int startIndex, int records) {
	Pageable myPageable = PageRequest.of(startIndex, records);
	Page<StockEntity> stockEntityPage = stockRepo.findAll(myPageable);
	List<StockEntity> stockEntityList = stockEntityPage.getContent();

	List<Stock> stockDtoList = new ArrayList<Stock>();
	for (StockEntity stockEntity : stockEntityList) {
	    Stock stock = convertEntityIntoDTO(stockEntity);
	    stockDtoList.add(stock);
	}
	return stockDtoList;
    }

    private StockEntity convertDTOIntoEntity(Stock stock) {
	TypeMap<Stock, StockEntity> tMap = modelMapper.typeMap(Stock.class, StockEntity.class);
	tMap.addMappings(mapper -> {
	    mapper.map(Stock::getMarket, StockEntity::setMarket);
	});
	StockEntity stockEntity = modelMapper.map(stock, StockEntity.class);
	return stockEntity;
    }

    private Stock convertEntityIntoDTO(StockEntity stockEntity) {
	TypeMap<StockEntity, Stock> tMap = modelMapper.typeMap(StockEntity.class, Stock.class);
	tMap.addMappings(mapper -> {
	    mapper.map(StockEntity::getMarket, Stock::setMarket);
	});
	Stock stock = modelMapper.map(stockEntity, Stock.class);
	return stock;
    }
}
