package cn.tianqb.sso;

import cn.tianqb.SsoServerApplication;
import cn.tianqb.sdk.common.LoginContext;
import cn.tianqb.sdk.service.SsoService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author tianqingbo3
 * @version 1.0
 * @date 2021/1/11 16:35
 */
@SpringBootTest(classes = {SsoServerApplication.class})
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class SsoServiceTest {

    @Autowired
    private SsoService ssoService;

    @Test
    public void getInfoTest() {
        LoginContext info = ssoService.getInfo("d9546e61-d0b4-4c19-b298-58aad1d81827");
        log.info("LoginContext:{}", JSONObject.toJSONString(info));
    }

}
