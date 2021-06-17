package com.wbj.service;

import com.wbj.common.Result;
import com.wbj.entity.Hr;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wbj
 * @since 2021-06-16
 */
public interface IHrService extends IService<Hr> {

    /**
     * @param name hr唯一用户姓名
     * @param password hr密码
     * @return
     * 登录接口
     *
     *
     */
    Result login(String name, String password);

    /**
     *
     * @param
     * @return
     * 添加hr接口
     */
    Result register(Hr hr);


}
