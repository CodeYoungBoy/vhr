package com.wbj.service.impl;

import com.wbj.entity.Department;
import com.wbj.mapper.DepartmentMapper;
import com.wbj.service.IDepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wbj
 * @since 2021-06-16
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

}
