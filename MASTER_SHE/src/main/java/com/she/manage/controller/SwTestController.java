package com.she.manage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.manage.service.LogListService;
import com.she.manage.service.SwTestService;
import com.she.utils.JcoUtil;
import com.she.utils.UmsUtil;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/manage/swtest")
public class SwTestController {

    @Autowired
    private SwTestService swTestService;

    @Autowired
    private LogListService logService;

    /**
     * 거래처 정보
     *
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "거래처 정보", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/sap-interface-test-01")
    public List<Map<String, Object>> sapInterfaceTest01() throws Exception {
        List<Map<String, Object>> datas = JcoUtil.getSapTableData("ZSHE_INTF_VENDOR", "ET_VEN");

        return datas;
    }

    /**
     * 자재마스터 정보
     *
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "자재마스터 정보", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/sap-interface-test-03")
    public List<Map<String, Object>> sapInterfaceTest03() throws Exception {
        List<Map<String, Object>> datas = JcoUtil.getSapTableData("ZSHE_INTF_MAT", "ET_MAT");

        return datas;
    }

    /**
     * 화학물질실적 정보
     *
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "화학물질실적 정보", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParam(name = "yyyymm", value = "년월", required = false, dataType = "string", paramType = "query")
    @GetMapping("/sap-interface-test-05")
    public List<Map<String, Object>> sapInterfaceTest05(@RequestParam String yyyymm) throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("I_SPMON", yyyymm);

        List<Map<String, Object>> datas = JcoUtil.getSapTableData("ZSHE_INTF_CHEMICAL_STOC", "ET_STOC", params);

        return datas;
    }

    /**
     * 자재검토요청 정보
     *
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "자재검토요청 정보", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "startDate", value = "자재등록요청일 FROM", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "endDate", value = "자재등록요청일 TO", required = false, dataType = "string", paramType = "query") })
    @GetMapping("/sap-interface-test-06")
    public List<Map<String, Object>> sapInterfaceTest06(@RequestParam String startDate, @RequestParam String endDate) throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("I_ERSDAF", startDate);
        params.put("I_ERSDAT", endDate);

        List<Map<String, Object>> datas = JcoUtil.getSapTableData("ZSHE_INTF_MSDS", "ET_MAT", params);

        return datas;
    }

    /**
     * 자재검토결과 전송
     *
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "자재검토결과 정보", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "bukrs", value = "회사코드", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "rqnum", value = "요청번호", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "rqitm", value = "요청품목번호", required = false, dataType = "string", paramType = "query") })
    @GetMapping("/sap-interface-test-07")
    public Map<String, Object> sapInterfaceTest07(@RequestParam String bukrs, @RequestParam String rqnum, @RequestParam String rqitm) throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("I_BUKRS", bukrs);
        params.put("I_RQNUM", rqnum);
        params.put("I_RQITM", rqitm);

        Map<String, Object> datas = JcoUtil.setSapTableData("ZSHE_INTF_MSDS2", params);

        return datas;
    }

    /**
     * 폐기물 정보
     *
     * @return
     * @throws Exception
     */
    // @ApiOperation(value = "폐기물 정보", produces =
    // MediaType.APPLICATION_JSON_UTF8_VALUE)
    // @GetMapping("/sap-interface-test-08")
    // public List<Map<String, Object>> sapInterfaceTest08() throws Exception {
    // Map<String, Object> params = new HashMap<>();
    // params.put("I_ZIODAT", "20150630");
    //
    // List<Map<String, Object>> datas =
    // JcoUtil.getSapTableData("ZSHE_INTF_WASTE", "ET_WEIGH", params);
    //
    // return datas;
    // }

    /**
     * 근태 정보
     *
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "근태 정보", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParam(name = "yyyymm", value = "년월", required = false, dataType = "string", paramType = "query")
    @GetMapping("/sap-interface-test-09")
    public List<Map<String, Object>> sapInterfaceTest09(@RequestParam String yyyymm) throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("I_ZYYMM", yyyymm);

        List<Map<String, Object>> datas = JcoUtil.getSapTableData("ZSHE_INTF_WORK", "ET_WORK", params);

        return datas;
    }

    /**
     * 부서월별무재해 정보
     *
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "부서월별무재해 정보", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "startDate", value = "근무일 From", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "endDate", value = "근무일 To", required = false, dataType = "string", paramType = "query") })
    @GetMapping("/sap-interface-test-10")
    public List<Map<String, Object>> sapInterfaceTest10(@RequestParam String startDate, @RequestParam String endDate) throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("I_ZWKDTF", startDate);
        params.put("I_ZWKDTT", endDate);

        List<Map<String, Object>> datas = JcoUtil.getSapTableData("ZSHE_INTF_NACD", "ET_NACD", params);

        return datas;
    }

    /**
     * 용수량 정보
     *
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "용수량 정보", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParam(name = "izmdate", value = "요청일", required = false, dataType = "string", paramType = "query")
    @GetMapping("/sap-interface-test-11")
    public List<Map<String, Object>> sapInterfaceTest11(@RequestParam String izmdate) throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("I_ZMDATE", izmdate);
        List<Map<String, Object>> datas = JcoUtil.getSapTableData("ZSHE_INTF_WASTEWATER", "ITAB0", params, true);
        return datas;
    }

    /**
     * 입고시설 정보
     *
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "입고시설 정보", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/sap-interface-test-13")
    public List<Map<String, Object>> sapInterfaceTest13() throws Exception {
        List<Map<String, Object>> datas = JcoUtil.getSapTableData("ZSHE_INTF_LGORT", "ET_LGORT");
        return datas;
    }

    /**
     * 수질분석결과 정보
     *
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "수질분석결과 정보", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParam(name = "izmdate", value = "요청일", required = false, dataType = "string", paramType = "query")
    @GetMapping("/sap-interface-test-12")
    public List<Map<String, Object>> sapInterfaceTest12(@RequestParam String izmdate) throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("I_ZMDATE", izmdate);

        List<Map<String, Object>> datas = JcoUtil.getSapTableData("ZSHE_INTF_WQMEASURE", "ET_MEASURE", params);

        return datas;
    }

    /**
     * 대기방지시설 정보
     *
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "대기방지시설 정보", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParam(name = "izmdate", value = "요청일", required = false, dataType = "string", paramType = "query")
    @GetMapping("/sap-interface-test-14")
    public List<Map<String, Object>> sapInterfaceTest14(@RequestParam String izmdate) throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("I_ZMDATE", izmdate);

        List<Map<String, Object>> datas = JcoUtil.getSapTableData("ZSHE_INTF_AIRPREV", "ITAB", params);

        return datas;
    }

    /**
     * MSDS 파일 다운로드
     *
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "입고시설 정보", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/sap-interface-file")
    public String sapInterfaceFile() throws Exception {
        String[] data = new String[] { "00000449470001", "00000449470002", "00000444630001", "00000444630002", "00000442690001", "00000439760001", "00000439780001", "00000448730001", "00000452540001", "00000435710001", "00000452540002", "00000452540003", "00000457710001", "00000449470003", "00000438860001", "00000462670001", "00000442180001",
                "00000437490001", "00000442310001", "00000444620001", "00000444620002", "00000430210001", "00000432620001", "00000432900001", "00000429740001", "00000433450001", "00000433490001", "00000407360001", "00000407370001", "00000408010001", "00000408020001", "00000476440001", "00000481150001", "00000485510001", "00000485540001",
                "00000487450001", "00000487520001", "00000478950001", "00000485720002", "00000485720003", "00000485720004", "00000485750001", "00000469530001", "00000470060001", "00000470060003", "00000470060004", "00000479620001", "00000491540001", "00000484880001", "00000468590001", "00000468600001", "00000470060002", "00000558590001",
                "00000546580001", "00000546590001", "00000536190001", "00000532390001", "00000532390002", "00000532390003", "00000532400001", "00000532400002", "00000532400003", "00000532400004", "00000532400005", "00000532610001", "00000532610002", "00000532610003", "00000532620001", "00000532620002", "00000532620003", "00000532620004",
                "00000532650001", "00000532680001", "00000532390004", "00000534070001", "00000534070002", "00000534070003", "00000534070004", "00000537110001", "00000537120001", "00000543540001", "00000523880001", "00000535490001", "00000535490002", "00000517300001", "00000517410001", "00000503200001", "00000512330001", "00000499680001",
                "00000521520001", "00000499680002", "00000499690001", "00000520010001", "00000518230001", "00000520210001", "00000518620001", "00000518700001", "00000509890001", "00000509890002", "00000509900001", "00000509900002", "00000509960001", "00000519210001", "00000519220001", "00000497430001", "00000562040001", "00000562040002",
                "00000579810002", "00000577520001", "00000581510001", "00000560620001", "00000577510001", "00000575320001", "00000575320002", "00000575330001", "00000570900001", "00000578130001", "00000560880001", "00000571280001", "00000571280002", "00000575330002", "00000575330003", "00000581220001", "00000566700001", "00000566700002",
                "00000578710001", "00000578710002", "00000578710003", "00000578710004", "00000578710005", "00000578710006", "00000578710007", "00000581270001", "00000579760001", "00000585930001", "00000612380001", "00000591850002", "00000624090001", "00000599490001", "00000604850001", "00000604850002", "00000604850003", "00000604850004",
                "00000604930001", "00000622320001", "00000679460001", "00000679460002", "00000607450001", "00000613730001", "00000604500001", "00000604500002", "00000611500002", "00000619180001", "00000679040001", "00000679040002", "00000589180001", "00000598630001", "00000605390001", "00000623870001", "00000652570001", "00000652570002",
                "00000621030001", "00000647300001", "00000648190001", "00000621030001", "00000612810001", "00000773470001", "00000727570001", "00000798660001", "00000747480001", "00000729660001", "00000789860001", "00000755910001", "00000776030001", "00000760630001", "00000712990001", "00000712990002", "00000712990003", "00000712990004",
                "00000702910001", "00000729010001", "00000729010001", "00000750360001", "00000793060001", "00000713780003", "00000713820010", "00000721650001", "00000720710001", "00000805330001", "00000781230001", "00000755140001", "00000755140002", "00000861450003", "00000863910001", "00000863920001" };

        for (String d : data) {
            Map<String, Object> params = new HashMap<>();
            params.put("CLASSNAME", "PICTURES");
            params.put("CLASSTYPE", "OT");
            params.put("OBJECT_KEY", "00000439780001"); // bdsnm
            params.put("BINARY_FLAG", "X");

            List<Map<String, Object>> signature = JcoUtil.getSapFileData("BDS_BUSINESSDOCUMENT_GET_TAB", "SIGNATURE", params);

            List<Map<String, Object>> content = JcoUtil.getSapFileData("BDS_BUSINESSDOCUMENT_CREA_TAB", "CONTENT", params);

            // 확장자 확인
            String ext = "";
            for (Map<String, Object> map : signature) {
                String propName = (String) map.get("PROP_NAME");
                if (StringUtils.equals(propName, "BDS_DOCUMENTCLASS")) {
                    ext = (String) map.get("PROP_VALUE");
                    ext = ext.toLowerCase();
                }
            }

            // 내용 확인
            String result = "";
            for (Map<String, Object> map : content) {
                byte[] obj = (byte[]) map.get("LINE");
                String str = new String(obj, "UTF-8");
                result += str;
            }
            if (!ext.equals("")) {
                // to do..
            }
            if (!result.equals("")) {
                // to do..
            }
        }

        // 파일 생성
        // File file = new File("D:/test_decode/test." + ext);
        //
        // try (FileOutputStream fos = new FileOutputStream(file);) {
        // for (Map<String, Object> map : content) {
        // fos.write((byte[]) map.get("LINE"));
        // }
        // } catch (Exception e) {
        // // TODO: handle exception
        // }
        //
        // return result;
        return "";
    }

    @GetMapping("/jcotest")
    public List<Map<String, Object>> jcoTest() throws Exception {

        Map<String, Object> params = new HashMap<>();
        params.put("I_ZMDATE", "20150601");

        List<Map<String, Object>> datas = JcoUtil.getSapTableData("ZSHE_INTF_WASTEWATER", "ITAB0", params, true);
        datas.addAll(JcoUtil.getSapTableData("ZSHE_INTF_WASTEWATER", "ITAB1", params, true));
        datas.addAll(JcoUtil.getSapTableData("ZSHE_INTF_WASTEWATER", "ITAB2", params, true));
        datas.addAll(JcoUtil.getSapTableData("ZSHE_INTF_WASTEWATER", "ITAB3", params, true));
        datas.addAll(JcoUtil.getSapTableData("ZSHE_INTF_WASTEWATER", "ITAB4", params, true));
        datas.addAll(JcoUtil.getSapTableData("ZSHE_INTF_WASTEWATER", "ITAB5", params, true));

        // JcoUtil.insertSapTableData();

        return datas;
    }

    @GetMapping("/kkttest")
    public void kktTest() throws Exception {
        UmsUtil.sendKakaoTalk();
    }

    @GetMapping("/change-vender-id-password")
    public int updateVendorIdPw() throws Exception {
        return swTestService.updateVendorIdPw();
    }
}
