package cn.tianqb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 *
 * Spring配置
 *
 * @author tianqingbo3
 * @version 1.0
 * @date 2021/1/11 16:24
 */
@Configuration
@ImportResource(value = {"classpath:dubbo-provider.xml"})
public class SpringConfig {
}
