package com.codegym.dao;

import com.codegym.model.Product;

import java.util.List;

public interface IProductDAO extends IGeneralDAO<Product>{
    List<Product> findAllByName(String q);
}
