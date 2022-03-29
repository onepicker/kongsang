package com.ks.member.service.impl;

import com.ks.member.entity.UserLabel;
import com.ks.member.mapper.UserLabelMapper;
import com.ks.member.service.UserLabelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dCoder
 * @since 2022-03-27
 */
@Service
public class UserLabelServiceImpl extends ServiceImpl<UserLabelMapper, UserLabel> implements UserLabelService {

    @Override
    public boolean saveList(String userId, List<String> labelIds) {
        List<UserLabel> collect = labelIds.stream()
                .map(label -> {
                    UserLabel userLabel = new UserLabel();
                    userLabel.setUserId(userId);
                    userLabel.setLabelId(label);
                    return userLabel;
                })
                .collect(Collectors.toList());
        return this.saveBatch(collect);
    }
}
