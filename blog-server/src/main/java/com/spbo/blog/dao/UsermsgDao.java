package com.spbo.blog.dao;

import com.spbo.blog.domain.Usermsg;
import org.apache.ibatis.annotations.Param;

public interface UsermsgDao {
    int deleteByPrimaryKey(String iduser);

    int insert(Usermsg record);

    int insertSelective(Usermsg record);

    Usermsg selectByPrimaryKey(String iduser);

    int updateByPrimaryKeySelective(Usermsg record);

    int updateByPrimaryKeyWithBLOBs(Usermsg record);

    int updateByPrimaryKey(Usermsg record);

    Usermsg userLogin(@Param("iduser") String iduser, @Param("userpsw") String userpsw);

    int updatePassword(@Param("String userpsw") String newPassword,
                       @Param("oldPassword") String oldPassword,@Param("id") String iduser);
}