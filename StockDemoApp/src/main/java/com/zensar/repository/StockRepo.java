package com.zensar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.zensar.entity.StockEntity;

public interface StockRepo extends JpaRepository<StockEntity, Integer> {
    public List<StockEntity> findByName(String stockName);

    public List<StockEntity> findByMarket(String marketName);

    @Query(value = "select se from StockEntity as se where se.name = :stockName") // JPQL
    List<StockEntity> getStocksByName(String stockName);

    @Query(value = "select * from stocks where name = :stockName", nativeQuery = true)
    List<StockEntity> getStocksByNameSQL(String stockName);

    public List<StockEntity> findByNameContains(String nameLike);

//
    public List<StockEntity> findByNameContaining(String nameLike);

//
    public List<StockEntity> findByNameIsContaining(String nameLike);

    // jpql

    @Query(value = "SELECT se FROM StockEntity AS se WHERE se.name LIKE %:nameLike%")
    public List<StockEntity> getNameLike(String nameLike);

    @Query(value = "SELECT * FROM Stocks Where name LIKE %:nameLike%", nativeQuery = true)
    List<StockEntity> getByNameLikeSQL(String nameLike);

    List<StockEntity> findByOrderByNameAsc();

    List<StockEntity> findByOrderByNameDesc();

//    @Query(value = "SELECT se FROM StockEntity AS se order by se.name")
//    List<StockEntity> sortStocksByNameAsc();
//
//    @Query(value = "SELECT se FROM StockEntity AS se order by se.name DESC")
//
//    List<StockEntity> sortStocksByNameDsc();
}