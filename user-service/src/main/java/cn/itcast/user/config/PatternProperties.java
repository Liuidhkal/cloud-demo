package cn.itcast.user.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component //注入到bean
@ConfigurationProperties(prefix = "pattern") //根据前缀 获取配置文件的数据
public class PatternProperties {
    private String dateformat;
    private String envSharedValue;
    private String name;
}
