import com.alibaba.fastjson.JSON;
import com.payease.wallet.app.api.service.ILoginService;
import com.payease.wallet.app.api.service.IRegistryService;
import com.payease.wallet.app.impl.AppServiceProviderApplication;
import com.payease.wallet.dto.bean.LoginUserBo;
import com.payease.wallet.dto.bean.PacketHead;
import com.payease.wallet.dto.bean.RequestBo;
import com.payease.wallet.dto.bean.ResultBo;
import com.payease.wallet.entity.pojo.TLoginInfo;
import com.payease.wallet.entity.pojo.TUserInfo;
import com.payease.wallet.orm.inter.TLoginInfoMapper;
import com.payease.wallet.orm.inter.TUserInfoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author : zhangwen
 * @Data : 2017/11/14
 * @Description :
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppServiceProviderApplication.class)
public class test {


    @Autowired
    private IRegistryService registryService;

    @Autowired
    private ILoginService loginService;

    @Autowired
    StringRedisTemplate stringRedis;

    @Autowired
    TLoginInfoMapper tLoginInfoMapper;

//    @Test
//    public void testRegistry(){
//        try {
//            ResultBo b = this.registryService.registory(null);
//            System.out.println(JSON.toJSON(b));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
    /**
     * @Author zhangwen
     * @MethodName 测试手动登录
     * @Params
     * @Return
     * @Date 2017/11/14 上午10:20
     * @Desp
     */
    @Test
    public void testInitiativeLogin(){
        try {

            String reqBody = "{\n" +
                    "        \"acctNo\": \"15222859003\",\n" +
                    "        \"loginType\": \"password\",\n" +
                    "        \"password\":\"zhangwen\",\n" +
                    "        \"deviceFactory\": \"诺基亚\",\n" +
                    "        \"systemVersion\": \"4.0.1\",\n" +
                    "        \"deviceModel\":\"5310\",\n" +
                    "        \"deviceId\":\"123\"\n" +
                    "    }";
            RequestBo bo = new RequestBo();
            bo.setPacketBody(reqBody);

            ResultBo b = this.loginService.initiativeLogin(bo);
            System.out.println(JSON.toJSON(b));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    @Test
//    public void testAutoLogin(){
//        try {
//            String token = "C0BE6AD177CC9555901A57E567FBC597";
//            PacketHead ph = new PacketHead();
//            ph.setServiceCode("2002");
//            ph.setToken(token);
//            String reqBody = "{\n" +
//                    "        \"deviceFactory\": \"诺基亚\",\n" +
//                    "        \"systemVersion\": \"4.0.1\",\n" +
//                    "        \"deviceModel\":\"5310\",\n" +
//                    "        \"deviceId\":\"123\"\n" +
//                    "    }";
//            RequestBo bo = new RequestBo();
//            bo.setPacketBody(reqBody);
//            bo.setPacketHead(ph);
//            ResultBo b = this.loginService.autoLogin(bo);
//            System.out.println(JSON.toJSON(b));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }


//    @Test
//    public void getTokenUser(){
//        try {
//            String token = "7587B3EC1F7674C8E2B0CE56E873F7C1";
////            stringRedis.delete(token);
//
//
//            LoginUserBo bo = this.loginService.getUserInRedis(token);
//            System.out.println(bo.getMobilePhone());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }




    @Autowired
    TUserInfoMapper tUserInfoMapper;

}
