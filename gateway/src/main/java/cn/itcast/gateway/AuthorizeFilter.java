package cn.itcast.gateway;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Order(-1)//设置拦截顺序，数字越小 优先级越大
public class AuthorizeFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //1.获取请求参数
        ServerHttpRequest request = exchange.getRequest();
        MultiValueMap<String, String> params = request.getQueryParams();
        //2.获取参数中的authorization参数
        String auth = params.getFirst("authorization");
        //3.判断参数是否等于admin
        if ("admin".equals(auth)){
            //4.是，放行
            return chain.filter(exchange);
        }
        //5.否，拦截
        //5.1 设置状态码401
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);//未登录状态码401
        //5.2 拦截请求
        return exchange.getResponse().setComplete();//Complete结束
    }
}
