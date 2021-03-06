package com.wbj.service.impl;

import com.wbj.entity.Employee;
import com.wbj.mapper.EmployeeMapper;
import com.wbj.service.IEmployeeService;
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
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

}
