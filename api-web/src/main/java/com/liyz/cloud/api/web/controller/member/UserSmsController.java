package com.liyz.cloud.api.web.controller.member;

import com.liyz.cloud.api.web.dto.member.SmsInfoDTO;
import com.liyz.cloud.api.web.feign.member.FeignUserSmsService;
import com.liyz.cloud.api.web.vo.member.ImageVO;
import com.liyz.cloud.common.base.Result.Result;
import com.liyz.cloud.common.base.enums.CommonCodeEnum;
import com.liyz.cloud.common.base.util.CommonConverterUtil;
import com.liyz.cloud.common.controller.util.HttpRequestUtil;
import com.liyz.cloud.common.model.bo.member.ImageBO;
import com.liyz.cloud.common.model.bo.member.SmsInfoBO;
import com.liyz.cloud.common.security.annotation.Anonymous;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/3/12 13:11
 */
@Api(value = "验证码", tags = "验证码")
@ApiResponses(value = {
        @ApiResponse(code = 0, message = "成功"),
        @ApiResponse(code = 1, message = "失败"),
        @ApiResponse(code = 401, message = "暂无授权"),
        @ApiResponse(code = 404, message = "找不到资源"),
        @ApiResponse(code = 500, message = "服务器内部错误")
})
@Slf4j
@RestController
@RequestMapping("/user/sms")
public class UserSmsController {

    @Autowired
    FeignUserSmsService feignUserSmsService;

    @ApiOperation(value = "发送信息", notes = "发送信息")
    @Anonymous
    @PostMapping(value = "/message")
    public Result<Boolean> message(@Validated(SmsInfoDTO.Sms.class) @RequestBody SmsInfoDTO smsInfoDTO) {
        SmsInfoBO smsInfoBO = CommonConverterUtil.beanCopy(smsInfoDTO, SmsInfoBO.class);
        HttpServletRequest httpServletRequest = HttpRequestUtil.getRequest();
        String ip = HttpRequestUtil.getIpAddress(httpServletRequest);
        log.info("user login，ip:{}", ip);
        smsInfoBO.setIp(ip);
        return feignUserSmsService.message(smsInfoBO);
    }

    @ApiOperation(value = "刷新图片验证码", notes = "刷新图片验证码")
    @Anonymous
    @GetMapping(value = "/imageCode")
    public Result<ImageVO> imageCode() {
        HttpServletRequest httpServletRequest = HttpRequestUtil.getRequest();
        String ip = HttpRequestUtil.getIpAddress(httpServletRequest);
        log.info("create imageCode，ip:{}", ip);
        Result<ImageBO> boResult = feignUserSmsService.imageCode();
        if (CommonCodeEnum.success.getCode().equals(boResult.getCode())) {
            return Result.success(CommonConverterUtil.beanCopy(boResult.getData(), ImageVO.class));
        }
        return Result.error(boResult.getCode(), boResult.getMessage());
    }
}
