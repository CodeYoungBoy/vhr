package com.wbj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wbj.common.MD5Utils;
import com.wbj.common.Result;
import com.wbj.entity.Hr;
import com.wbj.mapper.HrMapper;
import com.wbj.service.IHrService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wbj
 * @since 2021-06-16
 */
@Service
public class HrServiceImpl extends ServiceImpl<HrMapper, Hr> implements IHrService {

    @Autowired
    private HrMapper hrMapper;

    /**
     *
     * @param name hr唯一用户姓名
     * @param password hr密码
     * @return
     * 登录接口
     */
    @Override
    public Result login(String name, String password) {
        QueryWrapper<Hr> queryWrapper=new QueryWrapper<Hr>();
        queryWrapper.eq("name", name);
        Hr hr = hrMapper.selectOne(queryWrapper);
        if (hr == null) {
            return Result.error("用户不存在");

        }else{
            if (StringUtils.pathEquals(hr.getPassword(),MD5Utils.md5(password+hr.getSalt()))){
                Integer expiration=1000*60*60*24;
                Map<String, Object> map = new HashMap<String, Object>(10);
                //可以设置权限或者角色
                map.put("username", hr.getUsername());
                String token = createToken(hr, expiration, map);
                return Result.ok(token, hr,expiration);
            }
            return Result.error("密码错误");
        }

    }

    /**
     *
     * @param hr
     * @return
     */
    @Override
    public Result register(Hr hr) {
        QueryWrapper<Hr> queryWrapper=new QueryWrapper<Hr>();
        queryWrapper.eq("name", hr.getName());
        if (hrMapper.selectOne(queryWrapper) == null) {
            hr.setPassword(MD5Utils.md5(hr.getPassword()+MD5Utils.getSalt()));
            hr.setSalt(MD5Utils.getSalt());
            int insert = hrMapper.insert(hr);
            if (insert>0){
                return Result.ok("创建成功",hr);
            }
            return Result.error("插入失败");
        }

        return Result.error("用户名存在，请重试");
    }

    /**
     *
     * @param hr hr对象
     * @param expiration 过期时间
     * @param map 设置其他值
     * @return
     * 生成token
     */
    private String createToken(Hr hr, Integer expiration, Map<String, Object> map) {
        JwtBuilder builder = Jwts.builder();
        String token =
                //设置jwt的主题，token中携带的数据
                builder.setSubject(hr.getUsername())
                        //设置token的生成时间
                        .setIssuedAt(new Date())
                        //设置token的id
                        .setId(hr.getId().toString())
                        //map可以携带用户的角色信息
                        .setClaims(map)
                        //设置token的过期时间
                        .setExpiration(new Date(System.currentTimeMillis() + expiration))
                        //设置加方式和加密密码
                        .signWith(SignatureAlgorithm.HS256, hr.getPassword())
                        .compact();
        return token;
    }


}
