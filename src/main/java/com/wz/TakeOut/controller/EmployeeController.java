package com.wz.TakeOut.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wz.TakeOut.entity.Employee;
import com.wz.TakeOut.entity.R;
import com.wz.TakeOut.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * (employee)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    /**
     * 服务对象
     */
    @Autowired
    private EmployeeService employeeServiceImpl;


    /**
     * 员工登录
     * @param data
     * @param request
     * @return
     */
    @PostMapping("login")
    public R<Employee> selectOne(@RequestBody Employee data, HttpServletRequest request) {
        HttpSession session = request.getSession();
        //1.将提交的密码进处理
        String username = data.getUsername();
        String password = data.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        //2.根据用户名查询数据库
        LambdaQueryWrapper<Employee> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Employee::getUsername,username);
        Employee emp = employeeServiceImpl.getOne(lambdaQueryWrapper);
        //3.判断用户是否存在
        if (emp==null) {
            return R.error("用户不存在");
        }
        //4.比对密码
        if (!password.equals(emp.getPassword())) {
            return R.error("密码错误");
        }
        //5.查看该员工状态
        if (emp.getStatus()==0) {
            return R.error("员工处于禁用状态");
        }
        //6.将员工id存入session
        session.setAttribute("employee",emp.getId());
        return R.success(emp);
    }
    @PostMapping("logout")
    public R<String> logout(HttpServletRequest httpServletRequest){
        //1.清楚session
        httpServletRequest.getSession().invalidate();
        return R.success("退出成功");
    }

}
