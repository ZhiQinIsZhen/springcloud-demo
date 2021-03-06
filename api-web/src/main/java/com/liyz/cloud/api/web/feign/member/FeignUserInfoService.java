package com.liyz.cloud.api.web.feign.member;

import com.liyz.cloud.common.base.Result.PageResult;
import com.liyz.cloud.common.base.Result.Result;
import com.liyz.cloud.common.base.remote.bo.JwtUserBO;
import com.liyz.cloud.common.model.bo.member.LoginUserInfoBO;
import com.liyz.cloud.common.model.bo.member.UserInfoBO;
import com.liyz.cloud.common.model.bo.member.UserRegisterBO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/1/6 13:37
 */
@FeignClient(value = "service-member", contextId = "UserInfo")
public interface FeignUserInfoService {

    @PostMapping(value = "/member/register", consumes = "application/json")
    Result<UserInfoBO> register(@NotBlank UserRegisterBO userRegisterBO);

    @PostMapping(value = "/member/getByLoginName", consumes = "application/json")
    Result<JwtUserBO> getByLoginName(@RequestBody LoginUserInfoBO loginUserInfoBO);

    @PostMapping(value = "/member/kickDownLine", consumes = "application/json")
    Result<Date> kickDownLine(@RequestBody LoginUserInfoBO downLineBO);

    @PostMapping(value = "/member/loginTime", consumes = "application/json")
    Result<Date> loginTime(@RequestBody LoginUserInfoBO downLineBO);

    @GetMapping("/member/getByUserId")
    Result<UserInfoBO> getByUserId(@RequestParam(value = "userId", required = false) Long userId);

    @GetMapping("/member/page")
    PageResult<UserInfoBO> pageList(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                    @RequestParam(value = "size", required = false, defaultValue = "10") Integer size);
}
