package com.liyz.cloud.common.controller.error;

import com.liyz.cloud.common.base.Result.Result;
import com.liyz.cloud.common.base.enums.CommonCodeEnum;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2019/9/4 9:36
 */
@Api(value = "错误重定向", tags = "错误重定向")
@ApiResponses(value = {
        @ApiResponse(code = 0, message = "成功"),
        @ApiResponse(code = 1, message = "失败"),
        @ApiResponse(code = 401, message = "暂无授权"),
        @ApiResponse(code = 404, message = "找不到资源"),
        @ApiResponse(code = 500, message = "服务器内部错误")
})
@Slf4j
@RestController
@RequestMapping("/error")
public class ErrorApiController implements ErrorController {

    @Override
    public String getErrorPath() {
        return "/error";
    }


    @ApiOperation(value = "错误重定向", notes = "错误重定向")
    @ApiImplicitParam(name = "Authorization", value = "认证Token", required = false, paramType = "header",
            dataType = "string")
    @RequestMapping
    public Result error(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        HttpStatus status = HttpStatus.valueOf(statusCode);
        String msg = Objects.isNull(status) ? CommonCodeEnum.UnckowException.getMessage() : status.getReasonPhrase();
        return Result.error(String.valueOf(statusCode), msg);
    }

}
