package com.vn.controller;


import com.vn.config.TokenProvider;
import com.vn.entity.*;
import com.vn.service.UserService;
import com.vn.vo.LoginVo;
import com.vn.vo.UsersVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;




@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AuthenticationController {


    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity login(@RequestBody LoginVo vo) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        vo.getUsername(),
                        vo.getPassword()
                )
        );
        // Lấy token của người dùng
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);

        // Gán lại giá trị cho UserVo để hiển thị lên thông tin
        UsersVO usersVO = new UsersVO();
        usersVO.setToken(token);
        usersVO.setUsername(vo.getUsername());
        // Từ Username lấy thông tin của member
        Users users = userService.findUser(vo.getUsername());
        usersVO.setId(users.getId());
        usersVO.setName(users.getName());
        usersVO.setEmail(users.getEmail());

        String role = users.getUserRoles().get(0).getRole().getName();
        return ResponseEntity.ok(usersVO);
    }

}
