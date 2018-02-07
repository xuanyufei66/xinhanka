import com.payease.wallet.app.MainApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhangzhili on 2017/11/16.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)

public class AppTest {

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Resource(name = "stringRedisTemplate")
    ValueOperations<String,String> valOpts;

    @Test
    public void setrediskey(){


        valOpts.set("zhangzhili","张三",1L, TimeUnit.MINUTES);
        String s = valOpts.get("zhangzhili");
        System.out.println(s);
    }
}
