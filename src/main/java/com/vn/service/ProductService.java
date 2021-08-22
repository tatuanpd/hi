package com.vn.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.vn.entity.Category;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.vn.entity.Product;
import com.vn.repository.CategoryRepository;
import com.vn.repository.ProductRepository;
import com.vn.vo.CategoryVO;
import com.vn.vo.ProductVO;


@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public ProductVO create(ProductVO productVO) {
        Product product = new Product();
        Optional<Category> category = categoryRepository.findById(productVO.getCategory().getId());
        BeanUtils.copyProperties(productVO, product);
        product.setCategory(category.get());
        productRepository.save(product);
        return productVO;
    }

    @Override
    public List<ProductVO> readAll(String search) {
        List<ProductVO> voList = new ArrayList<>();
        List<Product> productList = new ArrayList<Product>();

        if (StringUtils.isEmpty(search)) {
            productList = productRepository.findAll();
        } else {
            productList = productRepository.findByNameContaining(search);
        }
        
        for (Product product : productList) {
            Optional<Category> category =categoryRepository.findById(product.getCategory().getId());
            ProductVO vo = new ProductVO();
            BeanUtils.copyProperties(product, vo);
            CategoryVO vo1 = new CategoryVO();
            vo1.setId(category.get().getId());
            vo1.setName(category.get().getName());
            vo.setCategory(vo1);
            voList.add(vo);
        }
        return voList;
    }


    @Override
    public ProductVO update(ProductVO productVO) {
        Optional<Product> optional = productRepository.findById(productVO.getId());
        if (optional.isPresent()) {
            Product product = optional.get();
            BeanUtils.copyProperties(productVO, product);
            productRepository.save(product);
        }
        return productVO;
    }

    @Override
    public ProductVO delete(Integer id) {
        ProductVO vo = new ProductVO();
        Optional<Product> optional = productRepository.findById(id);
        if (optional.isPresent()) {
            Product product = optional.get();
            BeanUtils.copyProperties(product, vo);
            productRepository.delete(product);
        }
        return vo;
    }

    @Override
    public ProductVO getById(Integer id) {
        ProductVO vo = new ProductVO();
        Optional<Product> optional = productRepository.findById(id);
        if (optional.isPresent()) {
            Product product = optional.get();
            BeanUtils.copyProperties(product, vo);
        }
        return vo;
    }

    @Override
    public boolean existById(Integer id) {
        return productRepository.existsById(id);
    }
    public Product convertToEntity(ProductVO model, Product entity) {
		if (entity == null)
			entity = new Product();
		BeanUtils.copyProperties(model, entity);
		return entity;
	}

}
