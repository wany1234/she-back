package com.she.manage.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.manage.model.AttachSetting;
import com.she.manage.service.AttachSettingService;
import com.she.utils.RequestMapper;

@RestController
@RequestMapping("/api/manage")
public class AttachSettingController {

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private AttachSettingService attachSettingService;

    /**
     * 첨부파일 설정 목록조회
     * 
     * @param parameter
     * @return
     * @throws Exception
     */
    @GetMapping("/attachsettings")
    public ResponseEntity<List<AttachSetting>> getAttachSettings(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String taskClassNm = map.containsKey("taskClassNm") ? map.get("taskClassNm").toString() : "";
        String taskClassDesc = map.containsKey("taskClassDesc") ? map.get("taskClassDesc").toString() : "";

        List<AttachSetting> list = this.attachSettingService.getAttachSettings(taskClassNm, taskClassDesc);

        return ResponseEntity.ok().body(list);
    }

    /**
     * 첨부파일 설정 상세조회
     * 
     * @param taskClassNm
     * @return
     * @throws Exception
     */
    @GetMapping("/attachsetting/{taskClassNm}")
    public ResponseEntity<AttachSetting> getAttachSetting(@PathVariable String taskClassNm) throws Exception {

        AttachSetting attachSetting = this.attachSettingService.getAttachSetting(taskClassNm);

        return ResponseEntity.ok().body(attachSetting);
    }

    /**
     * 첨부파일 설정 등록
     * 
     * @param attachSetting
     * @return
     * @throws Exception
     */
    @PostMapping("/attachsetting")
    public ResponseEntity<Integer> createAttachSetting(@RequestBody AttachSetting attachSetting) throws Exception {
        return ResponseEntity.ok().body(this.attachSettingService.createAttachSetting(attachSetting));
    }

    /**
     * 첨부파일 설정 수정
     * 
     * @param attachSetting
     * @return
     * @throws Exception
     */
    @PutMapping("/attachsetting")
    public ResponseEntity<Integer> updateAttachSetting(@RequestBody AttachSetting attachSetting) throws Exception {
        return ResponseEntity.ok().body(this.attachSettingService.updateAttachSetting(attachSetting));
    }

    /**
     * 첨부파일 구분명 중복검사
     * 
     * @param taskClassNm
     * @return
     * @throws Exception
     */
    @GetMapping("/attachsettingcheckduplicate/{taskClassNm}")
    public ResponseEntity<Boolean> checnDuplicateTaskClassNm(@PathVariable String taskClassNm) throws Exception {
        return ResponseEntity.ok().body(this.attachSettingService.checkDuplicateTaskClassNm(taskClassNm));
    }

}
