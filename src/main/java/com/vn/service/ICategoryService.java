package com.vn.service;



import java.util.List;
import java.util.Optional;

import com.vn.entity.Category;
import com.vn.vo.CategoryVO;

public interface ICategoryService {
    public CategoryVO create(CategoryVO categoryVO);

    public List<CategoryVO> readAll(String search);

    public CategoryVO update(CategoryVO categoryVO);

    public CategoryVO delete(Integer id);

//    List<Category> readPage(int pageSize, int pageNumber);

    public CategoryVO getById(Integer id);

    public Boolean existById(Integer id);

    public Optional<Category> findById(Integer id);

}
