package com.lwx.spring.cloud.gateway.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * 全局过滤器
 * @author Liuwuxiang
 * @date 2022年03月11日 12:50
 */
@Component
public class AuthFilter implements GlobalFilter, Ordered {

    /**
     * 功能描述: 拦截过滤
     *
     * @param exchange      {@link ServerWebExchange} 服务器Web交换
     * @param chain         {@link GatewayFilterChain} 网关过滤器链
     * @return Mono<Void>   {@link Mono}
     * @auther 刘武祥
     * @date 2022/3/13 16:26
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1.获取到请求 -> 获取到查询参数 -> 得到请求传递的第一个参数令牌
        String token = exchange.getRequest().getQueryParams().getFirst("token");

        // 2.如果没有token令牌
        if (token == null || token.isEmpty()) {

            /*//设置响应状态码为 未授权
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            //设置响应结束
            return exchange.getResponse().setComplete();*/

            // 2.1 获取响应
            ServerHttpResponse response = exchange.getResponse();

            // 2.2 封装错误信息
            Map<String,Object> responseData = Maps.newHashMap();
            responseData.put("code",401);
            responseData.put("message","非法请求");
            responseData.put("cause","Token is empty");

            try {
                // 2.3 将信息转换为 JSON
                ObjectMapper objectMapper = new ObjectMapper();
                // 2.4 将 封装信息responseData的值写为字节
                byte[] data = objectMapper.writeValueAsBytes(responseData);

                // 2.5 将字节错误信息变为buffer
                DataBuffer buffer = response.bufferFactory().wrap(data);
                // 2.6 设置响应状态码 为 未经授权
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                // 2.7 添加响应头
                response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
                // 将 buffer 写到响应结果集中
                return response.writeWith(Mono.just(buffer));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        //3. 传给下一个过滤器
        return chain.filter(exchange);
    }

    /**
     * 功能描述: 设置过滤顺序
     *
     * @return int 值越小越靠前
     * @auther 刘武祥
     * @date 2022/3/13 16:26
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
