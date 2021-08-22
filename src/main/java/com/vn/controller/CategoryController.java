package com.vn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vn.service.ICategoryService;
import com.vn.vo.CategoryVO;

@CrossOrigin("*")
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @RequestMapping(value = "/getall", method = RequestMethod.GET)
    public ResponseEntity<List<CategoryVO>> readAll(@RequestParam(value = "search", required = false) String search) {
        return ResponseEntity.ok(categoryService.readAll(search));
    }

    //    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<CategoryVO> create(@RequestBody CategoryVO vo) {
        return ResponseEntity.ok(categoryService.create(vo));
    }

//    	@PreAuthorize("hasAnyRole('ADMIN','USER')")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<CategoryVO> update(@RequestBody CategoryVO vo) {
        if (!categoryService.existById(vo.getId())) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categoryService.update(vo));
    }

    //    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ResponseEntity<CategoryVO> delete(@PathVariable Integer id) {
        if (!categoryService.existById(id)) {
            return ResponseEntity.notFound().build();
        }
        categoryService.delete(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<CategoryVO> detail(@PathVariable Integer id) {
        if (!categoryService.existById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categoryService.getById(id));
    }
}
