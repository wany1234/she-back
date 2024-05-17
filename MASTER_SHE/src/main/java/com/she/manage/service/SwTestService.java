package com.she.manage.service;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.manage.mapper.SwTestMapper;

@Service
public class SwTestService {

    @Autowired
    private SwTestMapper swTestMapper;

    public int updateVendorIdPw() throws Exception {
        int result = 0;

        // 협력업체 id/pw 업데이트
        List<String> vendorCdList = swTestMapper.getVendorCdList();
        if (CollectionUtils.isNotEmpty(vendorCdList)) {
            for (String vendorCd : vendorCdList) {
                String shaPw = DigestUtils.sha256Hex(vendorCd);
                result += swTestMapper.updateVendorIdPw(vendorCd, shaPw);
            }
        }
        return result;
    }
}
