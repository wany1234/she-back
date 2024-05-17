package com.she.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("com.she.manage.mapper.SwTestMapper")
public interface SwTestMapper {

    /**
     * 협력업체 코드 목록 조회
     * 
     * @return
     * @throws Exception
     */
    public List<String> getVendorCdList() throws Exception;

    /**
     * 협력업체 id/pw 업데이트
     * 
     * @param portalId
     * @param portalPwd
     * @return
     * @throws Exception
     */
    public int updateVendorIdPw(@Param("portalId") String portalId, @Param("portalPwd") String portalPwd)
            throws Exception;
}
