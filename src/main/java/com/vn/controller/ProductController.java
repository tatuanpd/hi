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

import com.vn.service.IProductService;
import com.vn.vo.ProductVO;

@CrossOrigin("*")
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @RequestMapping(value = "/getall", method = RequestMethod.GET)
    public ResponseEntity<List<ProductVO>> readAll(@RequestParam(value = "search", required = false) String search) {
        return ResponseEntity.ok(productService.readAll(search));
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<ProductVO> create(@RequestBody ProductVO vo) {
        return ResponseEntity.ok(productService.create(vo));
    }

//    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<ProductVO> update(@RequestBody ProductVO vo) {
        if (!productService.existById(vo.getId())) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productService.update(vo));
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ResponseEntity<ProductVO> delete(@PathVariable Integer id) {
        if (!productService.existById(id)) {
            return ResponseEntity.notFound().build();
        }
        productService.delete(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ProductVO> detail(@PathVariable Integer id) {
        if (!productService.existById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productService.getById(id));
    }
}
