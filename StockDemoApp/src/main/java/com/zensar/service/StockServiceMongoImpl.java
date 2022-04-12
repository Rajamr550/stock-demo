package com.zensar.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.zensar.dto.Stock;
import com.zensar.entity.StockDocument;
import com.zensar.repository.StockMongoRepo;

@Service(value = "MONGO_SERVICE")
@Primary
public class StockServiceMongoImpl implements StockService {
    @Autowired
    StockMongoRepo stockMongoRepo;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public Stock updateStock(int stockId, Stock updatedStock) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Boolean deleteAllStocks() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Boolean deleteStocksById(int stockId) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Stock createNewStock(Stock stock) {
	StockDocument stockDocument = convertDTOIntoEntity(stock);
	stockDocument = stockMongoRepo.save(stockDocument);
	return convertEntityIntoDTO(stockDocument);
    }

    @Override
    public List<Stock> getAllStocks() {
	// TODO Auto-generated method stub
	return null;
    }

//    @Override
//    public Optional<StockDocument> getStockById(int StockId) {
//	// TODO Auto-generated method stub
//	return null;
//    }

    @Override
    public List<Stock> getStocksByName(String stockName) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<Stock> getStocksSortedByName(String sortType) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<Stock> getStocksByPage(int startIndex, int records) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<Stock> getStocksByNameLike(String namelike) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<Stock> getStocksByMarket(String stockMarket) {
	// TODO Auto-generated method stub
	return null;
    }

    private StockDocument convertDTOIntoEntity(Stock stock) {
	TypeMap<Stock, StockDocument> tMap = modelMapper.typeMap(Stock.class, StockDocument.class);
	tMap.addMappings(mapper -> {
	    mapper.map(Stock::getMarket, StockDocument::setMarket);
	});
	StockDocument stockDocument = modelMapper.map(stock, StockDocument.class);
	return stockDocument;
    }

    private Stock convertEntityIntoDTO(StockDocument stockDocument) {
	TypeMap<StockDocument, Stock> tMap = modelMapper.typeMap(StockDocument.class, Stock.class);
	tMap.addMappings(mapper -> {
	    mapper.map(StockDocument::getMarket, Stock::setMarket);
	});
	Stock stock = modelMapper.map(stockDocument, Stock.class);
	return stock;
    }

}
