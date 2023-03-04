package com.edu.bookstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();

        // 允许cookies跨域
        config.setAllowCredentials(true);
        // 需要跨域的地址 * 表示对所有的地址都可以访问
        config.setAllowedOrigins(Collections.singletonList("*"));
        // 跨域的请求头, *表示全部
        config.setAllowedHeaders(Collections.singletonList("*"));
        // 跨域的请求方法, *表示全部允许,也可以单独设置GET、PUT等
        config.setAllowedMethods(Collections.singletonList("*"));
        List<String> list = new ArrayList<>();
        list.add("Content-Disposition");
        config.setExposedHeaders(list);
        // 预检请求的缓存时间(秒), 即在这个时间段里,对于相同的跨域请求不会再预检了
        config.setMaxAge(300L);
        // 配置可以访问的地址
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}