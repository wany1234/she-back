package com.she.env.gas.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.env.gas.model.ActData;
import com.she.env.gas.service.ActDataService;
import com.she.manage.mapper.CodeMasterMapper;
import com.she.manage.model.CodeMaster;
import com.she.utils.ConstVal;
import com.she.utils.RequestMapper;

@RestController
public class ActDataController {

    // queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private ActDataService actDataService;

    @Autowired
    private CodeMasterMapper codeMasterMapper;

    /**
     * 활동데이터 조회
     * 
     * @param parameter
     *            검색조건
     * @return 활동데이터코드 목록
     * @throws Exception
     */
    @GetMapping("/api/env/gas/actdatas")
    public ResponseEntity<List<ActData>> getActDatas(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 활동데이터구분
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 활동데이터명
        String[] actMonth = this.requestMapper.convertObjectListAsStringArray(map.get("actMonth"));

        String startYear = "";
        String endYear = "";

        if (actMonth != null && actMonth.length == 2) {
            startYear = actMonth[0];
            endYear = actMonth[1];
        }
        // 작성상태
        String stepCd = map.containsKey("stepCd") ? map.get("stepCd").toString() : "";

        List<ActData> actDatas = actDataService.getActDatas(plantCd, startYear, endYear, stepCd);
        return ResponseEntity.ok().body(actDatas);
    }

    /**
     * 활동데이터 체크
     * 
     * @param actDataCd
     *            활동데이터코드 actMonth 배출연월
     * @return 활동데이터 중복코드 갯수
     * @throws Exception
     */
    @GetMapping("/api/env/gas/count/actdata/{plantCd}/{actMonth}")
    public ResponseEntity<Integer> countActData(@PathVariable("plantCd") String plantCd, @PathVariable("actMonth") String actMonth) throws Exception {
        return ResponseEntity.ok(actDataService.countActData(plantCd, actMonth));
    }

    /**
     * 활동데이터 신규등록
     * 
     * @param parameter
     *            검색조건
     * @return 활동데이터코드
     * @throws Exception
     */
    @PostMapping("api/env/gas/actdata")
    public ResponseEntity<Integer> createActData(@RequestBody ActData actData) throws Exception {
        return ResponseEntity.ok().body(this.actDataService.createActData(actData));
    }

    /**
     * 활동데이터 등록 시 목록조회
     * 
     * @param plantCd
     *            활동데이터코드 actMonth 년월
     * @return 활동데이터코드 상세내역
     * @throws Exception
     */
    @GetMapping("api/env/gas/items/actdata/{plantCd}/{actMonth}")
    public ResponseEntity<List<ActData>> getItemsActData(@PathVariable("plantCd") String plantCd, @PathVariable("actMonth") String actMonth) throws Exception {
        String year = actMonth.substring(0, 4);
        return ResponseEntity.ok().body(this.actDataService.getItemsActData(plantCd, year));
    }

    /**
     * 활동데이터 상세조회
     * 
     * @param actDataCd
     *            활동데이터코드
     * @return 활동데이터코드 상세내역
     * @throws Exception
     */
    @GetMapping("api/env/gas/actdata/{actDaRptNo}")
    public ResponseEntity<ActData> getActData(@PathVariable("actDaRptNo") String actDaRptNo) throws Exception {
        return ResponseEntity.ok().body(this.actDataService.getActData(actDaRptNo));
    }

    /**
     * 활동데이터 상세목록 조회
     * 
     * @param actDataCd
     *            활동데이터코드
     * @return 활동데이터코드 상세내역
     * @throws Exception
     */
    @GetMapping("api/env/gas/udateditems/actdata/{actDaRptNo}")
    public ResponseEntity<List<ActData>> getUpdatedItemsActData(@PathVariable("actDaRptNo") String actDaRptNo) throws Exception {
        return ResponseEntity.ok().body(this.actDataService.getUpdatedItemsActData(actDaRptNo));
    }

    /**
     * 활동데이터 수정
     * 
     * @param parameter
     *            검색조건
     * @return 활동데이터코드
     * @throws Exception
     */
    @PutMapping("api/env/gas/actdata")
    public ResponseEntity<Integer> updateActData(@RequestBody ActData actData) throws Exception {
        return ResponseEntity.ok().body(this.actDataService.updateActData(actData));
    }

    /**
     * 활동데이터 완료
     * 
     * @param parameter
     *            검색조건
     * @return 활동데이터코드
     * @throws Exception
     */
    @PutMapping("api/env/gas/complete/actda")
    public ResponseEntity<Integer> completeActData(@RequestBody ActData actData) throws Exception {
        return ResponseEntity.ok().body(this.actDataService.completeActData(actData));
    }

    /**
     * 활동데이터 확정취소
     *
     * @param actData
     *            검색조건
     * @return 활동데이터코드
     * @throws Exception
     */
    @PutMapping("api/env/gas/cancel-complete/actda")
    public ResponseEntity<Integer> cancelCompleteActData(@RequestBody ActData actData) throws Exception {
        return ResponseEntity.ok().body(this.actDataService.cancelCompleteActData(actData));
    }

    /**
     * 활동데이터 삭제
     * 
     * @param parameter
     *            검색조건
     * @return 활동데이터코드
     * @throws Exception
     */
    @DeleteMapping("api/env/gas/actdata/{actDaRptNo}")
    public ResponseEntity<Integer> deleteActData(@PathVariable("actDaRptNo") String actDaRptNo) throws Exception {
        return ResponseEntity.ok().body(this.actDataService.deleteActData(actDaRptNo));
    }

    @GetMapping("/api/env/gas/refbook/exceldown")
    public ResponseEntity<String> getActDataRefer(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        CodeMaster path = this.codeMasterMapper.getCodeMaster(ConstVal.CODE_GROUP_FILE_PATH, ConstVal.CODE_FILE_PATH_FORM, "Y");

        ClassPathResource classPathResource = new ClassPathResource("templates" + path.getCodeNm() + "SHE_Gas_Ref_Book.xlsx");

        File templete = classPathResource.getFile();

        int read = 0;
        byte[] buff = new byte[1024];

        ByteArrayInputStream in = null;
        FileInputStream fi = null;

        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            fi = new FileInputStream(templete);
            while ((read = fi.read(buff)) > 0) {
                out.write(buff, 0, read);
            }

            in = new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw e;
        } finally {
            try {
                if (in != null)
                    in.close();
                fi.close();
            } catch (IOException e) {
                throw e;
            }
        }

        byte[] encoded = Base64.encodeBase64(IOUtils.toByteArray(in));
        String encodedString = new String(encoded);
        return ResponseEntity.ok().body(encodedString);
    }

    /**
     * 배출량 산정 계산식
     * 
     * @param actDaRptNo
     * @return
     * @throws Exception
     */
    @PutMapping("/api/env/gas/calactdate")
    public ResponseEntity<List<ActData>> calActDate(@RequestBody ActData actData) throws Exception {
        return ResponseEntity.ok().body(this.actDataService.calActDate(actData));
    }
}
