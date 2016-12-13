package org.eddy.service.impl;

import org.eddy.dao.mapper.test.UserMapper;
import org.eddy.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by eddy on 16/12/13.
 */
@Service
@Profile("dev")
public class TestServiceImpl implements TestService{

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public void test() {
        userMapper.insert("test");
    }
}
