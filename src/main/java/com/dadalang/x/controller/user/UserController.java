package com.dadalang.x.controller.user;

import com.dadalang.x.dto.user.AccountUserDto;
import com.dadalang.x.service.user.UserService;
import com.dadalang.x.util.jwt.PassToken;
import com.dadalang.x.vo.res.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;

/**
 * @version 1.0
 * @Author lj
 * @date 2021/5/24 9:55 上午
 * @desc
 */
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired UserService userService;

    @PostMapping("/info")
    @PassToken
    public Response userInfo() {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        String accountId = (String) request.getAttribute("accountId");
        AccountUserDto account = userService.findByAccoundId(accountId);
        if (account != null) {
            return Response.success(account);
        }else{
            return Response.failed("fail", "账户不存在");
        }
    }

//    @GetMapping("/{id}")
//    public Mono<AccountUserDto> queryUser(@PathVariable String id) {
//
//    }
}
