package com.she.mgt.training.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.common.model.DefaultParam;
import com.she.mgt.training.mapper.TrainingScenarioMapper;
import com.she.mgt.training.model.TrainingScenario;

@Service
public class TrainingScenarioService {
    @Autowired
    private TrainingScenarioMapper trainingScenarioMapper;

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
    public List<TrainingScenario> getTrainingScenarios(String plantCd, String deptCd, String deptSubYn, String trainSceTypeCd, String title, DefaultParam defaultParam) throws Exception {
        return trainingScenarioMapper.getTrainingScenarios(plantCd, deptCd, deptSubYn, trainSceTypeCd, title, defaultParam);
    }

    /**
     * 안전운전계획>비상사태 대비대응>훈련시나리오 상세 조회
     * 
     * @param trainSceNo
     *            훈련시나리오 번호
     * @return 훈련시나리오
     * @throws Exception
     */
    public TrainingScenario getTrainingScenario(int trainSceNo) throws Exception {
        return trainingScenarioMapper.getTrainingScenario(trainSceNo);
    }

    /**
     * 안전운전계획>비상사태 대비대응>훈련시나리오 상세 조회
     * 
     * @param trainSceGrpNo
     *            훈련시나리오 그룹 번호
     * @return 훈련시나리오 개정이력 목록
     * @throws Exception
     */
    public List<TrainingScenario> getTrainingScenarioRevList(int trainSceGrpNo, DefaultParam defaultParam) throws Exception {
        return trainingScenarioMapper.getTrainingScenarioRevList(trainSceGrpNo, defaultParam);
    }

    /**
     * 안전운전계획>비상사태 대비대응>훈련시나리오 등록/개정
     * 
     * @param trainingSinario
     *            훈련시나리오
     * @return 훈련시나리오
     * @throws Exception
     */
    public int createTrainingScenario(TrainingScenario trainingScenario) throws Exception {
        if (trainingScenario.getTrainSceGrpNo() > 0) {
            // 개정하는 경우이며 해당 경우에 그룹번호로 엮여 있는 데이터들을 전부 use_yn N처리 한다.
            trainingScenarioMapper.updateTrainingScenarioUseyn(trainingScenario.getTrainSceGrpNo());
        }
        trainingScenarioMapper.createTrainingScenario(trainingScenario);
        return trainingScenario.getTrainSceNo();
    }

    /**
     * 안전운전계획>비상사태 대비대응>훈련시나리오 수정
     * 
     * @param trainingSinario
     *            훈련시나리오
     * @return 훈련시나리오
     * @throws Exception
     */
    public int editTrainingScenario(TrainingScenario trainingScenario) throws Exception {
        trainingScenarioMapper.editTrainingScenario(trainingScenario);
        return trainingScenario.getTrainSceNo();
    }

    /**
     * 안전운전계획>비상사태 대비대응 > 훈련시나리오 개정이력 삭제 추가
     * 
     * @param trainingSinario
     * @return 생성 행 수
     * @throws Exception
     */

    public int revDeleteTrainingScenario(List<TrainingScenario> trainingScenario) throws Exception {
        int count = 0;
        for (TrainingScenario trainingScenari : trainingScenario) {
            count += this.trainingScenarioMapper.revDeleteTrainingScenario(trainingScenari);
        }
        return count;
    }

}
