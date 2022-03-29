package com.ks.member.service;

import com.ks.member.entity.UserLabel;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dCoder
 * @since 2022-03-27
 */
public interface UserLabelService extends IService<UserLabel> {

    boolean saveList(String userId, List<String> labelIds);
}
