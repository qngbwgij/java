package com.tianxiadiyi.shixianshouye.web.fore;


import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ErrorPageConfig  implements ErrorPageRegistrar {
    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        //这里直接映射到一个已经写好的URL映射路径controller，路径/已经好了，直接映射
        //如果是重新建一个URL映射路径controller，可能出现：
        // 1、映射结果对应的文件与已有的发生重复，可能不会正常跳到该文件，如果是responsebody是没问题的
        // 2、没有发生重复的情况，可能需要设置回应头，可能类似：<%response.setStatus(400);%>
        //以上均属推测，没有验证过
        ErrorPage e404 = new ErrorPage(HttpStatus.NOT_FOUND, "/");
        // TODO Auto-generated method stub
        //ErrorPage e500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/b");
        //registry.addErrorPages(e404, e500);
        registry.addErrorPages(e404);
    }

}
