package com.wz.TakeOut.service.Imple;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wz.TakeOut.entity.Employee;
import com.wz.TakeOut.mapper.EmployeeMapper;
import com.wz.TakeOut.service.EmployeeService;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}

