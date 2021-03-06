package com.liyz.cloud.api.web.feign.member;

import com.liyz.cloud.common.base.Result.Result;
import com.liyz.cloud.common.model.bo.member.ImageBO;
import com.liyz.cloud.common.model.bo.member.SmsInfoBO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/3/12 13:10
 */
@FeignClient(value = "service-member", contextId = "UserSms")
public interface FeignUserSmsService {

    @PostMapping(value = "/member/sms/message", consumes = "application/json")
    Result<Boolean> message(@RequestBody SmsInfoBO smsInfoBO);

    @GetMapping(value = "/member/sms/imageCode")
    Result<ImageBO> imageCode();
}
