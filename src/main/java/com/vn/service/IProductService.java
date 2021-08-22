package com.vn.service;



import java.util.List;

import com.vn.vo.ProductVO;

public interface IProductService {
    public ProductVO create(ProductVO productVO);

    public List<ProductVO> readAll(String search);

    public ProductVO update(ProductVO productVO);

    public ProductVO delete(Integer id);

//    List<Product> readPage(int pageSize, int pageNumber);

    public ProductVO getById(Integer id);

    public boolean existById(Integer id);

//    Product convertToEntity(ProductVO productVO, Product product);
//
//    ProductVO convertToModel(Product product, ProductVO productVO);
}
