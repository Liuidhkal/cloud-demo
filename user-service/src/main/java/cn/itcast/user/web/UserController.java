package cn.itcast.user.web;

import cn.itcast.user.config.PatternProperties;
import cn.itcast.user.pojo.User;
import cn.itcast.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestController
@RequestMapping("/user")
//@RefreshScope  //配置自动刷新
public class UserController {

    @Autowired
    private UserService userService;
    //@Value("${pattern.dateformat}")
    //private String dateformate;
    @Resource
    private PatternProperties patternProperties;

    /*
    * 这个方法用来返回PatternProperties（pojo）的json值
    * */
    @GetMapping("/prop")
    public PatternProperties patternProperties(){
        return patternProperties;
    }

    /*
    * 根据nacos中配置的格式 获取当前时间
    * 前端访问：http://localhost:8081/user/now
    * */
    @GetMapping("/now")
    public String now(){
        //return LocalDateTime.now().format(DateTimeFormatter.ofPattern(dateformate));
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(patternProperties.getDateformat()));
    }

    /**
     * 路径： /user/110
     *
     * @param id 用户id
     * @return 用户
     */
    @GetMapping("/{id}")
    public User queryById(@PathVariable("id") Long id) {
        return userService.queryById(id);
    }
}
