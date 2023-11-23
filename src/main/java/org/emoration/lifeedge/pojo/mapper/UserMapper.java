package org.emoration.lifeedge.pojo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.emoration.lifeedge.pojo.User;

/**
 * @Author czh
 * @Description 用户Mapper
 * @Date 2023/11/16
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
