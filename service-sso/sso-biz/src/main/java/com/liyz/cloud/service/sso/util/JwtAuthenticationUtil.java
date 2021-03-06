package com.liyz.cloud.service.sso.util;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.liyz.cloud.common.base.Result.Result;
import com.liyz.cloud.common.base.enums.CommonCodeEnum;
import com.liyz.cloud.common.base.remote.bo.JwtUserBO;
import com.liyz.cloud.service.sso.config.MyUser;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2019/9/7 17:37
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JwtAuthenticationUtil {

    public static MyUser create(JwtUserBO user) {
        if (Objects.isNull(user)) {
            return null;
        }
        return new MyUser(
                user.getLoginName(),
                user.getLoginPwd(),
                //由于web没有角色这种概念，所以不用设置
                Lists.newArrayList(),
                user.getUserId(),
                user.getEmail(),
                user.getWebTokenTime(),
                user.getAppTokenTime()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> authorities) {
        return authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    public static void authFail(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(Result.error(CommonCodeEnum.AuthorizationFail)));
        httpServletResponse.addHeader("Session-Invalid","true");
    }

    /*public static List<String> getAntPatterns() throws ClassNotFoundException {
        List<String> list = new ArrayList<>();
        Map<String, Object> map = SpringContextUtil.getBeansWithAnnotation(RestController.class);
        for (Object bean : map.values()) {
            Class beanClass;
            if (AopUtils.isAopProxy(bean)) {
                beanClass = AopUtils.getTargetClass(bean);
            } else {
                beanClass = bean.getClass();
            }
            if (beanClass.isAnnotationPresent(Anonymous.class)) {
                if (beanClass.isAnnotationPresent(RequestMapping.class)) {
                    RequestMapping requestMapping = (RequestMapping) beanClass.getAnnotation(RequestMapping.class);
                    String[] mappings = requestMapping.value();
                    if (mappings != null && mappings.length > 0) {
                        list.add(mappings[0] + "/**");
                    } else {
                        getAntPatternsByMethods(beanClass, list);
                    }
                }
            } else {
                getAntPatternsByMethods(beanClass, list);
            }
        }
        return list;
    }*/

    /*private static void getAntPatternsByMethods(Class beanClass, List<String> list) throws ClassNotFoundException {
        String url = "";
        if (beanClass.isAnnotationPresent(RequestMapping.class)) {
            RequestMapping requestMapping = (RequestMapping) beanClass.getAnnotation(RequestMapping.class);
            String[] mappings = requestMapping.value();
            if (mappings != null && mappings.length > 0) {
                url = mappings[0];
            }
        }
        Method[] methods = beanClass.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Anonymous.class)) {
                String[] mappings = null;
                if (method.isAnnotationPresent(RequestMapping.class)) {
                    mappings = method.getAnnotation(RequestMapping.class).value();
                } else if (method.isAnnotationPresent(GetMapping.class)) {
                    mappings = method.getAnnotation(GetMapping.class).value();
                } else if (method.isAnnotationPresent(PutMapping.class)) {
                    mappings = method.getAnnotation(PutMapping.class).value();
                } else if (method.isAnnotationPresent(PostMapping.class)) {
                    mappings = method.getAnnotation(PostMapping.class).value();
                } else if (method.isAnnotationPresent(DeleteMapping.class)) {
                    mappings = method.getAnnotation(DeleteMapping.class).value();
                }
                if (mappings != null && mappings.length > 0) {
                    String mapping =  mappings[0];
                    if (mapping.contains("{")) {
                        mapping = mapping.substring(0, mapping.indexOf("{")) + "**";
                    }
                    list.add(url + mapping);
                }
            }
        }
    }*/
}
