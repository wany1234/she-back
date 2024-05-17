package com.she.common.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.she.common.model.DefaultParam;
import com.she.common.model.LawElaw;
import com.she.common.service.SapDataService;
import com.she.manage.model.CodeMaster;
import com.she.manage.service.CodeMasterService;
import com.she.utils.ConstVal;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/common/sap")
public class SapDataController {

    @Autowired
    private SapDataService sapDataService;

    @Autowired
    private CodeMasterService codeMasterService;

    private static final Logger logger = LoggerFactory.getLogger(SapDataController.class);

    /**
     * SAP 거래처 정보 등록
     * 
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "SAP 거래처 정보 등록", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/insert-vendor-data")
    public ResponseEntity<Integer> insertSapVendorData() throws Exception {
        return ResponseEntity.ok().body(sapDataService.insertSapVendorData());
    }

    /**
     * SAP 자재마스터 등록
     * 
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "SAP 자재마스터 등록", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/insert-mat-data")
    public ResponseEntity<Integer> insertSapMatData() throws Exception {
        return ResponseEntity.ok().body(sapDataService.insertSapMatData());
    }

    /**
     * SAP 근태정보 등록
     * 
     * @param yyyymm
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "SAP 근태정보 등록", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParam(name = "yyyymm", value = "년월", required = false, dataType = "string", paramType = "query")
    @GetMapping("/insert-sap-work-info-data")
    public ResponseEntity<Integer> insertSapWorkInfoData(@RequestParam String yyyymm) throws Exception {
        return ResponseEntity.ok().body(sapDataService.insertSapWorkInfoData(yyyymm));
    }

    /**
     * SAP 부서월별 무재해정보 등록
     * 
     * @param startDate
     * @param endDate
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "SAP 부서월별 무재해정보 등록", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "startDate", value = "근무일 From", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "endDate", value = "근무일 To", required = false, dataType = "string", paramType = "query") })
    @GetMapping("/insert-nacd-info-data")
    public ResponseEntity<Integer> insertNacdInfoData(@RequestParam String startDate, @RequestParam String endDate) throws Exception {
        return ResponseEntity.ok().body(sapDataService.insertNacdInfoData(startDate, endDate));
    }

    /**
     * SAP 자재검토요청 등록
     * 
     * @param startDate
     * @param endDate
     * @param finish
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "SAP 자재검토요청 등록", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "startDate", value = "자재등록요청일 FROM", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "endDate", value = "자재등록요청일 TO", required = false, dataType = "string", paramType = "query") })
    @GetMapping("/insert-mat-chk-rqst-data")
    public ResponseEntity<Integer> insertMatChkRqstData(@RequestParam String startDate, @RequestParam String endDate) throws Exception {
        return ResponseEntity.ok().body(sapDataService.insertMatChkRqstData(startDate, endDate));
    }

    /**
     * SAP 입고시설(저장시설) 등록
     * 
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "SAP 입고시설(저장시설) 등록", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/insert-mat-strg-data")
    public ResponseEntity<Integer> insertMatStrgData() throws Exception {
        return ResponseEntity.ok().body(sapDataService.insertMatStrgData());
    }

    /**
     * SAP 화학물질실적 월 정보 등록
     * 
     * @param yyyymm
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "SAP 화학물질실적 월 정보 등록", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParam(name = "yyyymm", value = "년월", required = false, dataType = "string", paramType = "query")
    @GetMapping("/insert-sap-mat-inout-data")
    public ResponseEntity<Integer> insertSapMatInoutData(@RequestParam String yyyymm) throws Exception {
        return ResponseEntity.ok().body(sapDataService.insertSapMatInoutData(yyyymm));
    }

    /**
     * SAP 화학물질실적 일 정보 등록
     * 
     * @param yyyymm
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "SAP 화학물질실적 일 정보 등록", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParam(name = "yyyymm", value = "년월", required = false, dataType = "string", paramType = "query")
    @GetMapping("/insert-sap-mat-strg-stoc-data")
    public ResponseEntity<Integer> insertSapMatStrgStocData(@RequestParam String yyyymm) throws Exception {
        return ResponseEntity.ok().body(sapDataService.insertSapMatStrgStocData(yyyymm));
    }

    /**
     * SAP 수질분석결과 등록
     * 
     * @param reqDate
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "SAP 수질분석결과 등록", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParam(name = "reqDate", value = "요청일", required = false, dataType = "string", paramType = "query")
    @GetMapping("/insert-sap-wtr-meas-info-data")
    public ResponseEntity<Integer> insertSapWtrMeasInfoData(@RequestParam String reqDate) throws Exception {
        return ResponseEntity.ok().body(sapDataService.insertSapWtrMeasInfoData(reqDate));
    }

    /**
     * SAP 대기방지시설 등록
     * 
     * @param reqDate
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "SAP 대기방지시설 등록", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParam(name = "reqDate", value = "요청일", required = false, dataType = "string", paramType = "query")
    @GetMapping("/insert-sap-eair-info-data")
    public ResponseEntity<Integer> insertSapEairInfoData(@RequestParam String reqDate) throws Exception {
        return ResponseEntity.ok().body(sapDataService.insertSapEairInfoData(reqDate));
    }

    /**
     * SAP 수질용수량 등록
     * 
     * @param reqDate
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "SAP 수질용수량 등록", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParam(name = "reqDate", value = "요청일", required = false, dataType = "string", paramType = "query")
    @GetMapping("/insert-sap-waste-water-data")
    public ResponseEntity<Integer> insertSapWasteWaterData(@RequestParam String reqDate) throws Exception {
        return ResponseEntity.ok().body(sapDataService.insertSapWasteWaterData(reqDate));
    }

    /**
     * SAP 수질용수량 등록
     * 
     * @param reqDate
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "SAP 수질용수량 등록", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/law_sap_data_if")
    public ResponseEntity<Integer> lawElawIF() throws Exception {
        // 법제처코드(법)
        List<CodeMaster> law = this.codeMasterService.getSelect(ConstVal.LAW_ELAW_MST, "Y", new DefaultParam("kr"));
        // 법제처코드(시행령)
        law.addAll(this.codeMasterService.getSelect(ConstVal.LAW_ELAW_ENF, "Y", new DefaultParam("kr")));
        // 법제처코드(시행규칙)
        law.addAll(this.codeMasterService.getSelect(ConstVal.LAW_ELAW_RGL, "Y", new DefaultParam("kr")));

        if (law != null && law.size() > 0) {
            for (CodeMaster codeMaster : law) {
                // 법제처 open api를 통해 데이터 받아 저장
                this.lawIF(codeMaster.getCodeGroupCd(), codeMaster.getCode());
            }
        }
        return ResponseEntity.ok().body(0);
    }

    public int lawIF(String lawType, String lawId) throws Exception {
        BufferedReader br = null;
        // DocumentBuilderFactory 생성
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder;
        Document doc = null;
        try {
            String from = "";
            String to = "";
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            DateFormat df = new SimpleDateFormat("yyyyMMdd");
            cal.add(Calendar.YEAR, -1);
            from = df.format(cal.getTime());

            cal.add(Calendar.YEAR, 2);
            to = df.format(cal.getTime());

            // OpenApi호출
            String urlstr = "http://www.law.go.kr/DRF/lawSearch.do?target=eflaw" + "&OC=yhhwang&type=XML&display=100&LID=" + lawId + "&ancYd=" + from + "~" + to;
            URL url = new URL(urlstr);
            HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();

            // 응답 읽기
            br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
            String result = "";
            String line;
            while ((line = br.readLine()) != null) {
                result = result + line.trim();// result = URL로 XML을 읽은 값
            }

            // xml 파싱하기
            InputSource is = new InputSource(new StringReader(result));
            builder = factory.newDocumentBuilder();
            doc = builder.parse(is);
            XPathFactory xpathFactory = XPathFactory.newInstance();
            XPath xpath = xpathFactory.newXPath();
            XPathExpression expr = xpath.compile("/LawSearch/law");
            NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
            for (int i = 0; i < nodeList.getLength(); i++) {
                NodeList child = nodeList.item(i).getChildNodes();
                LawElaw lawElaw = new LawElaw();
                for (int j = 0; j < child.getLength(); j++) {
                    Node node = child.item(j);
                    switch (node.getNodeName()) {
                    case "법령일련번호":
                        lawElaw.setLmst(Integer.parseInt(node.getTextContent()));
                        break;
                    case "현행연혁코드":
                        lawElaw.setLstepNm(node.getTextContent());
                        break;
                    case "법령명한글":
                        lawElaw.setLnameKor(node.getTextContent());
                        break;
                    case "법령약칭명":
                        lawElaw.setLnameAbb(node.getTextContent());
                        break;
                    case "법령ID":
                        lawElaw.setLkey(Integer.parseInt(node.getTextContent()));
                        break;
                    case "공포일자":
                        lawElaw.setPromDate(Integer.parseInt(node.getTextContent()));
                        break;
                    case "공포번호":
                        lawElaw.setPromNum(Integer.parseInt(node.getTextContent()));
                        break;
                    case "제개정구분명":
                        lawElaw.setRevDiv(node.getTextContent());
                        break;
                    case "소관부처코드":
                        lawElaw.setMgrGovcd(node.getTextContent());
                        break;
                    case "소관부처명":
                        lawElaw.setMgrGov(node.getTextContent());
                        break;
                    case "법령구분명":
                        lawElaw.setLtypeNm(node.getTextContent());
                        break;
                    case "시행일자":
                        lawElaw.setEnfDate(Integer.parseInt(node.getTextContent()));
                        break;
                    case "자법타법여부":
                        lawElaw.setLflagNm(node.getTextContent());
                        break;
                    case "법령상세링크":
                        lawElaw.setLdtlLink(node.getTextContent());
                        break;
                    default:
                        // do something ...
                    }
                }
                lawElaw.setLawTypeCd(lawType);
                sapDataService.mergeLawIF(lawElaw);
            }
        } catch (IOException ie) {
            logger.error(ie.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            if (br != null)
                br.close();
        }

        return 0;
    }

}
