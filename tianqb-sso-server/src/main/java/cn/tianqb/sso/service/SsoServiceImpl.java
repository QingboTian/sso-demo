package cn.tianqb.sso.service;

import cn.tianqb.enums.StatusEnum;
import cn.tianqb.sdk.common.LoginContext;
import cn.tianqb.sdk.common.SsoConstant;
import cn.tianqb.sdk.exception.SsoException;
import cn.tianqb.sdk.service.SsoService;
import cn.tianqb.sso.domain.AccessToken;
import cn.tianqb.sso.mapper.UserInfoMapper;
import cn.tianqb.sso.domain.UserInfo;
import cn.tianqb.sso.domain.UserInfoExample;
import cn.tianqb.sso.utils.TokenUtils;
import cn.tianqb.utils.Assert;
import cn.tianqb.utils.DateUtils;
import cn.tianqb.utils.UUIDUtils;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author tianqingbo3
 * @version 1.0
 * @date 2021/1/6 14:34
 */
@Service
@Slf4j
public class SsoServiceImpl implements SsoService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public boolean isLogin(String token) {
        return false;
    }

    @Override
    public LoginContext getInfo(String token) {
        if (ObjectUtils.isEmpty(token)) {
            throw new SsoException("User not login", HttpStatus.UNAUTHORIZED.value());
        }
        String value = redisTemplate.boundValueOps(String.join(SsoConstant.SEPARATOR, SsoConstant.TOKEN_PREFIX,
                        token)).get();

        if (ObjectUtils.isEmpty(value)) {
            throw new SsoException("User not login", HttpStatus.UNAUTHORIZED.value());
        }
        return JSONObject.parseObject(value, LoginContext.class);
    }

    public AccessToken login(String username, String password) {
        String token = null;
        AccessToken accessToken = null;
        String pwd = MD5Encoder.encode(password.getBytes());
        UserInfoExample example = new UserInfoExample();
        example.createCriteria()
                .andUsernameEqualTo(username);
        List<UserInfo> userInfos = userInfoMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(userInfos)) {
            UserInfo userInfo = userInfos.get(0);
            if (StatusEnum.DELETED.getCode().equals(userInfo.getStatus())) {
                throw new SsoException("The current user has been blocked", HttpStatus.UNAUTHORIZED.value());
            }
            if (!pwd.equals(userInfo.getPassword())) {
                throw new SsoException("Password mistake", HttpStatus.UNAUTHORIZED.value());
            }
            // 颁发token
            token = TokenUtils.accessToken();
            userInfo.setPassword(null);
            String key = String.join(SsoConstant.SEPARATOR, SsoConstant.TOKEN_PREFIX, token);
            redisTemplate.opsForValue().set(key, JSONObject.toJSONString(userInfo));
            redisTemplate.expire(key, 2, TimeUnit.HOURS);
            accessToken = new AccessToken();
            accessToken.setToken(token);
            LocalDateTime localDateTime = LocalDateTime.now();
            accessToken.setExpire(DateUtils.getTime(localDateTime.plusDays(2)));
        } else {
            throw new SsoException("The current user does not exist", HttpStatus.UNAUTHORIZED.value());
        }
        return accessToken;
    }

    public Boolean regist(UserInfo userInfo) {
        check(userInfo);
        init(userInfo);
        if (exist(userInfo)) {
            throw new SsoException("Do not repeat registration", HttpStatus.UNAUTHORIZED.value());
        }
        return userInfoMapper.insertSelective(userInfo) == 1;
    }

    private boolean exist(UserInfo userInfo) {
        PageHelper.startPage(1, 1);
        UserInfoExample example = new UserInfoExample();
        UserInfoExample.Criteria username = example.createCriteria().andUsernameEqualTo(userInfo.getUsername());
        UserInfoExample.Criteria mail = example.createCriteria().andMailEqualTo(userInfo.getMail());
        example.or(username);
        example.or(mail);
        List<UserInfo> userInfos = userInfoMapper.selectByExample(example);
        return CollectionUtils.isEmpty(userInfos);
    }



    private void init(UserInfo userInfo) {
        PageHelper.startPage(1, 1);
        String openId = null;
        boolean flag = true;
        while (flag) {
            openId = UUIDUtils.uuid();
            UserInfoExample example = new UserInfoExample();
            example.createCriteria()
                    .andOpenIdEqualTo(openId)
                    .andStatusEqualTo(StatusEnum.NORMAL.getCode());
            List<UserInfo> userInfos = userInfoMapper.selectByExample(example);
            if (CollectionUtils.isEmpty(userInfos)) {
                flag = false;
            }
        }
        userInfo.setOpenId(openId);
        userInfo.setPassword(MD5Encoder.encode(userInfo.getPassword().getBytes()));
        userInfo.setStatus(StatusEnum.NORMAL.getCode());

    }

    private void check(UserInfo userInfo) {
        Assert.notNull(userInfo, "userInfo为空");
        Assert.isEmpty(userInfo.getUsername(), "用户名为空");
        Assert.isEmpty(userInfo.getPassword(), "密码为空");
    }
}
