/**
 * Copyright (C) 2019, 2019 All Right Reserved, http://www.yullin.com/
 *
 * SHE Business can not be copied and/or distributed without the express
 * permission of Yullin Technologies
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 */

package com.she.manage.controller;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.net.URLCodec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.common.model.AttachFile;
import com.she.common.model.DefaultParam;
import com.she.common.model.Menu;
import com.she.manage.model.EduHistory;
import com.she.manage.model.EhrHistory;
import com.she.manage.model.HeaHistory;
import com.she.manage.model.LoginUserInfo;
import com.she.manage.model.SafHistory;
import com.she.manage.model.User;
import com.she.manage.service.UserService;
import com.she.security.auth.IAuthenticationFacade;
import com.she.utils.AES256Util;
import com.she.utils.RequestMapper;

import io.swagger.annotations.ApiOperation;

/**
 * 사용자 컨트롤러
 *
 */
@RestController
@RequestMapping("api/manage")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    private RequestMapper requestMapper = new RequestMapper();

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    /**
     * 인증된 토큰으로 사용자 정보 조회
     *
     * @param token
     *            인증토큰
     * @return 사용자
     * @throws Exception
     */
    @ApiOperation(value = "사용자상세조회[AUT00001]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/loginuserinfo")
    public ResponseEntity<LoginUserInfo> getUserByToken(HttpServletRequest request) throws Exception {
        String userId = authenticationFacade.getUserName("");
        LoginUserInfo loginUserInfo = this.userService.getUserByToken(userId);
        return ResponseEntity.ok().body(loginUserInfo);
    }

    /**
     * 인증된 토큰으로 협력업체 사용자 정보 조회
     *
     * @param token
     *            인증토큰
     * @return 사용자
     * @throws Exception
     */
    @ApiOperation(value = "사용자상세조회[AUT00001]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/loginvendorinfo")
    public ResponseEntity<LoginUserInfo> getVendorByToken(HttpServletRequest request) throws Exception {
        String userId = authenticationFacade.getUserName("");
        LoginUserInfo loginUserInfo = this.userService.getVendorByToken(userId);
        return ResponseEntity.ok().body(loginUserInfo);
    }

    /**
     * 사용자 조회
     *
     * @param parameter
     *            검색조건
     * @return 사용자 목록
     * @throws Exception
     */
    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String processCd = map.containsKey("processCd") ? map.get("processCd").toString() : "";
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        String userId = map.containsKey("userId") ? map.get("userId").toString() : "";
        String userNm = map.containsKey("userNm") ? map.get("userNm").toString() : "";
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";
        String deptSubYn = map.containsKey("deptSubYn") ? map.get("deptSubYn").toString() : "Y";
        String dutyCd = map.containsKey("dutyCd") ? map.get("dutyCd").toString() : "";

        return ResponseEntity.ok().body(this.userService.getUsers(plantCd, processCd, deptCd, userId, userNm, useYn, deptSubYn, dutyCd, defaultParam));
    }

    /**
     * 사용자 상세 조회
     *
     * @param userId
     *            사용자아이디
     * @return 사용자
     * @throws Exception
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<User> getUser(@PathVariable String userId) throws Exception {
        User user = this.userService.getUser(userId);
        user.setUserPwdSHA("");

        return ResponseEntity.ok().body(user);
    }

    /**
     * user 중복검사
     *
     * @param userId
     * @return 중복 행 수
     * @throws Exception
     */
    @GetMapping("/countuser")
    public ResponseEntity<Integer> countUser(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String userId = map.containsKey("userId") ? map.get("userId").toString() : "";

        return ResponseEntity.ok().body(this.userService.countUser(userId));
    }

    @PostMapping("/user")
    public ResponseEntity<String> createUser(@RequestBody User user) throws Exception {
        return ResponseEntity.ok().body(this.userService.createUser(user));
    }

    @PutMapping("/user")
    public ResponseEntity<Integer> updateUser(@RequestBody User user) throws Exception {
        return ResponseEntity.ok().body(this.userService.updateUser(user));
    }

    @PutMapping("/password")
    public ResponseEntity<Integer> passwordChange(@RequestBody User user) throws Exception {
        if (user.getUserId() == null || user.getUserId().equals("")) {
            throw new SQLException("ID는 반드시 입력해야 합니다.", new SQLException("No required value"));
        }
        user = this.encryptUserInfo(user);

        Integer count = this.userService.passwordChange(user);
        return ResponseEntity.ok().body(count);
    }

    /**
     * user 즐겨찾기 검색
     *
     * @param userId
     * @return
     * @throws Exception
     */
    @GetMapping("/user/favorites")
    public ResponseEntity<List<Menu>> getUserFavorites(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String taskGrpCd = map.containsKey("taskGrpCd") ? map.get("taskGrpCd").toString() : "";
        String menuNm = map.containsKey("menuNm") ? map.get("menuNm").toString() : "";
        String upMenuId = map.containsKey("upMenuId") ? map.get("upMenuId").toString() : "";
        String userId = map.containsKey("userId") ? map.get("userId").toString() : "";
        String menuId = map.containsKey("menuId") ? map.get("menuId").toString() : "";

        return ResponseEntity.ok().body(this.userService.getUserFavorites(userId, taskGrpCd, menuNm, upMenuId, menuId, defaultParam));
    }

    /**
     * user 즐겨찾기 등록
     *
     * @param userId
     * @param menuId
     * @return
     * @throws Exception
     */
    @PostMapping("/user/favorites")
    public ResponseEntity<Integer> createUserFavorites(@RequestBody HashMap<String, Object> parameter) throws Exception {
        String userId = parameter.containsKey("userId") ? parameter.get("userId").toString() : "";
        String menuId = parameter.containsKey("menuId") ? parameter.get("menuId").toString() : "";

        return ResponseEntity.ok().body(this.userService.createUserFavorites(userId, menuId));
    }

    /**
     * user 즐겨찾기 삭제
     *
     * @param userId
     * @param menuId
     * @return
     * @throws Exception
     */
    @PutMapping("/user/favorites")
    public ResponseEntity<Integer> deleteUserFavorites(@RequestBody List<HashMap<String, Object>> parameter) throws Exception {
        return ResponseEntity.ok().body(this.userService.deleteUserFavorites(parameter));
    }

    /**
     * 사용자 정보 암호화
     *
     * @param user
     * @return
     * @throws EncoderException
     * @throws UnsupportedEncodingException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     *             AlgorithmException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws InvalidAlgorithmParameterException
     */
    public User encryptUserInfo(User user) throws EncoderException, UnsupportedEncodingException, NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        final String userId = user.getUserId();
        URLCodec urlCodec = new URLCodec();
        String key = urlCodec.encode(userId);
        AES256Util aes256Util = new AES256Util(key);

        String sha256hex = DigestUtils.sha256Hex(user.getUserPwd());

        user.setUserPwdSHA("");

        user.setUserPwd(sha256hex);
        user.setUserPwdSHA(sha256hex);

        user.setPhoneNo(aes256Util.fullEncode(user.getPhoneNo()));

        user.setEmail(aes256Util.fullEncode(user.getEmail()));

        return user;
    }

    /**
     * 사용자 리스트 정보 복호화
     *
     * @param users
     * @return List<User> 사용자 리스트(복호화도니 데이터)
     * @throws EncoderException
     * @throws UnsupportedEncodingException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws InvalidAlgorithmParameterException
     */
    public List<User> decryptUsersInfo(List<User> users) throws EncoderException, UnsupportedEncodingException, NoSuchPaddingException, InvalidKeyException, DecoderException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        if (users != null && users.size() > 0) {
            for (User user : users) {
                final String userId = user.getUserId();
                URLCodec urlCodec = new URLCodec();
                String key = urlCodec.encode(userId);
                AES256Util aes256Util = new AES256Util(key);

                // 핸드폰 번호 AES256 복호화
                user.setPhoneNo(aes256Util.fullDecode(user.getPhoneNo()));

                // 이메일 AES256 복호화
                user.setEmail(aes256Util.fullDecode(user.getEmail()));

                // 생년월일 AES256 복호화

            }
        }
        return users;
    }

    /**
     * 사용자 정보 복호화
     *
     * @param user
     * @return
     * @throws EncoderException
     * @throws UnsupportedEncodingException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws InvalidAlgorithmParameterException
     */
    public User decryptUserInfo(User user) throws EncoderException, UnsupportedEncodingException, NoSuchPaddingException, InvalidKeyException, DecoderException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        final String userId = user.getUserId();
        URLCodec urlCodec = new URLCodec();
        String key = urlCodec.encode(userId);
        AES256Util aes256Util = new AES256Util(key);

        // 핸드폰 번호 AES256 복호화
        user.setPhoneNo(aes256Util.fullDecode(user.getPhoneNo()));

        // 이메일 AES256 복호화
        user.setEmail(aes256Util.fullDecode(user.getEmail()));

        // 생년월일 AES256 복호화

        return user;
    }

    // AES256 암복호화 테스트
    @GetMapping("/test/{str}")
    public ResponseEntity<String> getEncryptTest(@PathVariable String str) throws UnsupportedEncodingException, NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, EncoderException, BadPaddingException, InvalidAlgorithmParameterException, DecoderException {
        String keyData = "aes256-test-key!!";
        AES256Util aes256Util = new AES256Util(keyData);
        String encrypt = aes256Util.fullEncode(str);
        String decrypt = aes256Util.fullDecode(encrypt);
        return ResponseEntity.ok().body(encrypt);
    }

    @GetMapping("/user/favorite/menu/{userId}")
    public ResponseEntity<List<Menu>> getUserFavoriteMenus(@PathVariable String userId, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(this.userService.getUserFavoriteMenus(userId, defaultParam));
    }

    /**
     * 사용자 서명 등록
     *
     * @param parameter
     *            사용자 아이디, 사용자 서명
     * @return
     * @throws Exception
     */
    @PutMapping("/user/signature")
    public ResponseEntity<Integer> updateUserSignature(@RequestBody HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String userId = map.containsKey("userId") ? map.get("userId").toString() : "";
        String signature = map.containsKey("signature") ? map.get("signature").toString() : "";

        return ResponseEntity.ok().body(this.userService.updateUserSignature(userId, signature));
    }

    /**
     * 메뉴얼 다운로드 를위한 파일검색
     *
     * @param parameter
     * @return
     * @throws Exception
     */
    @GetMapping("/user/fileSearch")
    public ResponseEntity<AttachFile> fileSearch(@RequestParam HashMap<String, Object> param) throws Exception {
        return ResponseEntity.ok().body(userService.fileSearch(param));
    }
    
    /**
     * EHR 근무이력 조회
     * @param userId
     * @return
     * @throws Exception
     */
    @GetMapping("/user/ehrHistory/{userId}")
    public ResponseEntity<List<EhrHistory>> ehrHistory(@PathVariable String userId, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(userService.getEhrHistory(userId, defaultParam));
    }
    
    /**
     * 교육이력 조회
     * @param userId
     * @return
     * @throws Exception
     */
    @GetMapping("/user/eduHistory/{userId}")
    public ResponseEntity<List<EduHistory>> eduHistory(@PathVariable String userId, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(userService.getEduHistory(userId, defaultParam));
    }
    
    /**
     * 검진이력 조회
     * @param userId
     * @return
     * @throws Exception
     */
    @GetMapping("/user/heaHistory/{userId}")
    public ResponseEntity<List<HeaHistory>> heaHistory(@PathVariable String userId, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(userService.getHeaHistory(userId, defaultParam));
    }
    
    /**
     * 사고이력 조회
     * @param userId
     * @return
     * @throws Exception
     */
    @GetMapping("/user/safHistory/{userId}")
    public ResponseEntity<List<SafHistory>> safHistory(@PathVariable String userId, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(userService.getSafHistory(userId, defaultParam));
    }
}
