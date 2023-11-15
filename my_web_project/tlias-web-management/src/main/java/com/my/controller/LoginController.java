package com.my.controller;

import com.my.pojo.Emp;
import com.my.pojo.Result;
import com.my.service.EmpService;
import com.my.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private EmpService empService;

    /**
     * employee login
     * @param emp
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
        log.info("employee login: {}", emp);
        Emp e = empService.login(emp);

        //if login successful, generate token and return
        if(e != null){
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getId());
            claims.put("name", e.getName());
            claims.put("username", e.getUsername());

            String jwt = JwtUtils.GenerateJwt(claims); //jwt include the information of login employee
            return Result.success(jwt);
        }

        return Result.error("username or password error");
    }

}
