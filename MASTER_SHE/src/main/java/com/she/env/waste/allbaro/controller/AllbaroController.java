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
package com.she.env.waste.allbaro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.she.env.waste.allbaro.service.AllbaroService;
import com.she.env.waste.model.DisposalResult;
import com.she.manage.mapper.CodeMasterMapper;
import com.she.utils.RequestMapper;

/**
 * 폐기물 관리대장
 *
 */
@RestController("allbaroController")
@RequestMapping("api/env/waste/operationLog")
public class AllbaroController {
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private AllbaroService allbaroService;

    @Autowired
    private CodeMasterMapper codeMasterMapper;

    @PostMapping("/excel/allbaro")
    public ResponseEntity<List<DisposalResult>> getUploadExcelAllbaro(@RequestParam("files") MultipartFile[] files) throws Exception {

        List<DisposalResult> allbaros = allbaroService.getUploadExcelAllbaro(files);

        return ResponseEntity.ok().body(allbaros);
    }

    @PostMapping("/allbaro")
    public ResponseEntity<Integer> createAllbaroData(@RequestBody List<DisposalResult> allbaros) throws Exception {
        return ResponseEntity.ok().body(allbaroService.createAllbaroData(allbaros));
    }

}
