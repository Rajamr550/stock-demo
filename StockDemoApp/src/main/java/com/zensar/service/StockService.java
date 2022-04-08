package com.zensar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.zensar.dto.Stock;
import com.zensar.entity.StockEntity;

public interface StockService {
    public abstract Stock updateStock(int stockId, Stock updatedStock);

    public abstract Boolean deleteAllStocks();

    public abstract Boolean deleteStocksById(int stockId);

    public abstract Stock createNewStock(Stock stock);

    public abstract List<Stock> getAllStocks();

    public abstract Optional<StockEntity> getStockById(int StockId);

    public List<Stock> getStocksByName(String stockName);

    public List<Stock> getStocksSortedByName(String sortType);

    public List<Stock> getStocksByPage(int startIndex, int records);

    public List<Stock> getStocksByNameLike(String namelike);

    public List<Stock> getStocksByMarket(String stockMarket);

}