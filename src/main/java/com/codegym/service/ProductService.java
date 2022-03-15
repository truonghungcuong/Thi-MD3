package com.codegym.service;

import com.codegym.dao.IProductDAO;
import com.codegym.model.Product;

import java.util.List;

public class ProductService implements IProductService{
    private IProductDAO productDAO;

    public ProductService(IProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public List<Product> findAll() {
        return productDAO.findAll();
    }

    @Override
    public Product findById(int id) {
        return productDAO.findById(id);
    }

    @Override
    public boolean create(Product product) {
        return productDAO.create(product);
    }

    @Override
    public boolean update(int id, Product product) {
        return productDAO.update(id,product);
    }

    @Override
    public boolean delete(int id) {
        return productDAO.delete(id);
    }

    @Override
    public List<Product> findAllByName(String q) {
        q = "%" + q + "%";
        return productDAO.findAllByName(q);    }
}
