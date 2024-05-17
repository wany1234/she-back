package com.she.security.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.security.model.token.UserTokenStore;

@Mapper
@Repository("com.she.security.mapper.UserTokenStoreMapper")
public interface UserTokenStoreMapper {


    /**
     * 토큰저장소 상세조회
     * @param userId 사용자아이디
     * @return 조회한 토큰 저장소
     * @throws Exception
     */
    public UserTokenStore getUserTokenStore(@Param("userId") String userId) throws Exception;


    /**
     * 토큰저장소 신규 등록
     * @param userTokenStore
     * @return 등록된 토큰 저장소
     * @throws Exception
     */
    public int createUserTokenStore(UserTokenStore userTokenStore) throws Exception;


    /**
     * 토큰저장소 수정
     * @param userTokenStore
     * @return 등록된 토큰 저장소
     * @throws Exception
     */
    public int updateUserTokenStore(UserTokenStore userTokenStore) throws Exception;


    /**
     * 토큰저장소 삭제
     * @param userId 사용자아이디
     * @return 삭제갯수
     * @throws Exception
     */
    public Integer removeUserTokenStore(@Param("userId") String userId) throws Exception;


    /**
     * 사용자ID의 토큰 정보 초기화
     * @param userId 사용자아이디
     * @return 삭제갯수
     * @throws Exception
     */
    public Integer clearUserTokenStore(@Param("userId") String userId) throws Exception;

}
