package com.vn.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.vn.entity.Category;
import com.vn.repository.CategoryRepository;
import com.vn.vo.CategoryVO;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryVO create(CategoryVO categoryVO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryVO, category);
        categoryRepository.save(category);
        categoryVO.setId(category.getId());
        return categoryVO;
    }

    @Override
    public List<CategoryVO> readAll(String search) {
        List<CategoryVO> voList = new ArrayList<>();
        List<Category> categoryList = new ArrayList<Category>();

        if (StringUtils.isEmpty(search)) {
            categoryList = categoryRepository.findAll();
        } else {
            categoryList = categoryRepository.findByNameContaining(search);
        }
        for (Category category : categoryList) {
            CategoryVO vo = new CategoryVO();
            BeanUtils.copyProperties(category, vo);
            voList.add(vo);
        }
        return voList;
    }


    @Override
    public CategoryVO update(CategoryVO categoryVO) {
        Optional<Category> optional = categoryRepository.findById(categoryVO.getId());
        if (optional.isPresent()) {
            Category category = optional.get();
            BeanUtils.copyProperties(categoryVO, category);
            categoryRepository.save(category);
        }
        return categoryVO;
    }

    @Override
    public CategoryVO delete(Integer id) {
        CategoryVO vo = new CategoryVO();
        Optional<Category> optional = categoryRepository.findById(id);
        if (optional.isPresent()) {
            Category category = optional.get();
            BeanUtils.copyProperties(category, vo);
            categoryRepository.delete(category);
        }
        return vo;
    }

    @Override
    public CategoryVO getById(Integer id) {
        CategoryVO vo = new CategoryVO();
        Optional<Category> optional = categoryRepository.findById(id);
        if (optional.isPresent()) {
            Category category = optional.get();
            BeanUtils.copyProperties(category, vo);
        }
        return vo;
    }

    @Override
    public Boolean existById(Integer id) {
        return categoryRepository.existsById(id);
    }

    @Override
    public Optional<Category> findById(Integer id) {
        return categoryRepository.findById(id);
    }

}
