package com.she.mgt.training.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.common.model.DefaultParam;
import com.she.mgt.training.model.TrainingScenario;
import com.she.mgt.training.service.TrainingScenarioService;
import com.she.utils.RequestMapper;

@RestController
@RequestMapping("/api/sop/emergencyState")
public class TrainingScenarioController {
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private TrainingScenarioService trainingScenarioService;

    /**
     * 안전운전계획>비상사태 대비대응>훈련시나리오 목록 조회
     * 
     * @param plantCd
     *            사업장코드
     * @param deptCd
     *            부서코드
     * @param trainSceTypeCd
     *            분류
     * @param title
     *            제목
     * @return 훈련시나리오 목록
     * @throws Exception
     */
    @GetMapping("/trainingScenarios")
    public ResponseEntity<List<TrainingScenario>> getTrainingScenarios(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        // 하위부서 포함여부
        String deptSubYn = map.containsKey("deptSubYn") ? map.get("deptSubYn").toString() : "Y";

        String trainSceTypeCd = map.containsKey("trainSceTypeCd") ? map.get("trainSceTypeCd").toString() : "";

        String title = map.containsKey("title") ? map.get("title").toString() : "";

        return ResponseEntity.ok().body(trainingScenarioService.getTrainingScenarios(plantCd, deptCd, deptSubYn, trainSceTypeCd, title, defaultParam));
    }

    /**
     * 안전운전계획>비상사태 대비대응>훈련시나리오 상세 조회
     * 
     * @param trainSceNo
     *            훈련시나리오 번호
     * @return 훈련시나리오
     * @throws Exception
     */
    @GetMapping("trainingScenario/{trainSceNo}")
    public ResponseEntity<TrainingScenario> getTrainingScenario(@PathVariable("trainSceNo") int trainSceNo) throws Exception {
        return ResponseEntity.ok().body(trainingScenarioService.getTrainingScenario(trainSceNo));
    }

    /**
     * 안전운전계획>비상사태 대비대응>훈련시나리오 상세 조회
     * 
     * @param trainSceGrpNo
     *            훈련시나리오 그룹 번호
     * @return 훈련시나리오 개정이력 목록
     * @throws Exception
     */
    @GetMapping("trainingScenarioRev/{trainSceGrpNo}")
    public ResponseEntity<List<TrainingScenario>> getTrainingScenarioRevList(@PathVariable("trainSceGrpNo") int trainSceGrpNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(trainingScenarioService.getTrainingScenarioRevList(trainSceGrpNo, defaultParam));
    }

    /**
     * 안전운전계획>비상사태 대비대응>훈련시나리오 등록/개정
     * 
     * @param trainingSinario
     *            훈련시나리오
     * @return 훈련시나리오
     * @throws Exception
     */
    @PostMapping("trainingScenario")
    public ResponseEntity<Integer> createTrainingScenario(@RequestBody TrainingScenario trainingScenario) throws Exception {
        return ResponseEntity.ok().body(trainingScenarioService.createTrainingScenario(trainingScenario));
    }

    /**
     * 안전운전계획>비상사태 대비대응>훈련시나리오 수정
     * 
     * @param trainingSinario
     *            훈련시나리오
     * @return 훈련시나리오
     * @throws Exception
     */
    @PutMapping("trainingScenario")
    public ResponseEntity<Integer> editTrainingScenario(@RequestBody TrainingScenario trainingScenario) throws Exception {
        return ResponseEntity.ok().body(trainingScenarioService.editTrainingScenario(trainingScenario));
    }

    /**
     * 안전운전계획>비상사태 대비대응 > 훈련시나리오 개정이력 삭제 추가
     * 
     * @param trainingSinario
     * @return 생성 행 수
     * @throws Exception
     */
    @DeleteMapping("trainingScenario")
    public ResponseEntity<Integer> revDeleteTrainingScenario(@RequestBody List<TrainingScenario> trainingScenario) throws Exception {
        System.out.println("rrrr: ");
        return ResponseEntity.ok().body(this.trainingScenarioService.revDeleteTrainingScenario(trainingScenario));

    }

}
