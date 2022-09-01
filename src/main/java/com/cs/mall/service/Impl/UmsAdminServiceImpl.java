package com.cs.mall.service.Impl;

import com.cs.mall.common.utils.JwtTokenUtil;
import com.cs.mall.dao.UmsAdminRoleRelationDao;
import com.cs.mall.dto.AdminUserDetails;
import com.cs.mall.dto.UmsAdminParam;
import com.cs.mall.mbg.mapper.UmsAdminMapper;
import com.cs.mall.mbg.model.*;
import com.cs.mall.service.UmsAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author Caosen
 * @Date 2022/8/8 14:56
 * @Version 1.0
 */

@Service
@Slf4j
public class UmsAdminServiceImpl implements UmsAdminService {


    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UmsAdminMapper adminMapper;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UmsAdminRoleRelationDao adminRoleRelationDao;

    @Override
    public UmsAdmin register(UmsAdminParam umsAdminParam) {
        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtils.copyProperties(umsAdminParam, umsAdmin);
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setStatus(1);
        //查询是否有相同用户名的用户
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(umsAdmin.getUsername());
        List<UmsAdmin> umsAdminList = adminMapper.selectByExample(example);
        if (umsAdminList.size() > 0) {
            return null;
        }
        //将密码进行加密操作
        String encodePassword = passwordEncoder.encode(umsAdmin.getPassword());
        umsAdmin.setPassword(encodePassword);
        adminMapper.insert(umsAdmin);
        return umsAdmin;
    }



    @Override
    public String login(String username, String password) {
        //login方法是不去过滤的，直接来这里，我们和数据库验证，创建token，存到redis，返回token。
        // 其他的请求就要走过滤链，那就和我们jwtXXXtoenfilter有关了
        String token = null;
        try {
            //用userdetailservice的实现类的重写方法去获取userdetails 数据库
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            //判断是否正确
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            //todo 如果正确 保存还需要把数据存入redis当中，不过还没写redistemplate
            //待完善

            //这一行一般来说已经再jwtautheniccation中实现了，可以不写
            //放入securitycontextholder里面
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            //利用自己重写的方法，generatetoken生成了token,把他用工具类变成那种String传到客户端
            token = jwtTokenUtil.generateToken(userDetails);


        } catch (AuthenticationException e) {
            log.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    @Override
    public UmsAdmin getItem(Long id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<UmsRole> getRoleList(Long adminId) {
        return adminRoleRelationDao.getRoleList(adminId);
    }

    @Override
    public List<UmsPermission> getPermissionList(Long adminId) {
        return adminRoleRelationDao.getPermissionList(adminId);
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) {
//        return null;
//    }

//    @Override
//    public UserDetails loadUserByUsername(String username) {
//        //获取用户信息
//        UmsAdmin admin = getAdminByUsername(username);
//        if (admin != null) {
//            List<UmsResource> resourceList = getResourceList(admin.getId());
//            return new AdminUserDetails(admin,resourceList);
//        }
//        throw new UsernameNotFoundException("用户名或密码错误");
//    }


    @Override
    public UmsAdmin getAdminByUsername(String username) {
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UmsAdmin> adminList = adminMapper.selectByExample(example);
        if (adminList != null && adminList.size() > 0) {
            return adminList.get(0);
        }
        return null;
    }
}
