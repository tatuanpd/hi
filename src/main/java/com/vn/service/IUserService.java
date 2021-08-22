package com.vn.service;



import java.util.List;

import com.vn.entity.Users;
import com.vn.vo.UsersVO;

public interface IUserService {
    public UsersVO create(UsersVO userVO);

    public List<UsersVO> readAll(String search);

    public UsersVO update(UsersVO userVO);

    public UsersVO delete(Integer id);

//    List<Product> readPage(int pageSize, int pageNumber);
    public Users findUser(String user);
    public Users findByUsernameAndPassword(String username, String password);
    public UsersVO getById(Integer id);
    public boolean existById(Integer id);
}
