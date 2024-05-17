package com.she.mgt.training.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.mgt.training.model.TrainingScenario;

@Mapper
@Repository("com.she.mgt.training.mapper.TrainingScenarioMapper")
public interface TrainingScenarioMapper {

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
    public List<TrainingScenario> getTrainingScenarios(@Param("plantCd") String plantCd, @Param("deptCd") String deptCd, @Param("deptSubYn") String deptSubYn, @Param("trainSceTypeCd") String trainSceTypeCd, @Param("title") String title, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 안전운전계획>비상사태 대비대응>훈련시나리오 상세 조회
     * 
     * @param trainSceNo
     *            훈련시나리오 번호
     * @return 훈련시나리오
     * @throws Exception
     */
    public TrainingScenario getTrainingScenario(@Param("trainSceNo") int trainSceNo) throws Exception;

    /**
     * 안전운전계획>비상사태 대비대응>훈련시나리오 상세 조회
     * 
     * @param trainSceGrpNo
     *            훈련시나리오 그룹 번호
     * @return 훈련시나리오 개정이력 목록
     * @throws Exception
     */
    public List<TrainingScenario> getTrainingScenarioRevList(@Param("trainSceGrpNo") int trainSceGrpNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 안전운전계획>비상사태 대비대응>훈련시나리오 등록/개정
     * 
     * @param trainingSinario
     *            훈련시나리오
     * @return 훈련시나리오
     * @throws Exception
     */
    public int createTrainingScenario(TrainingScenario trainingScenario) throws Exception;

    /**
     * 안전운전계획>비상사태 대비대응>훈련시나리오 수정
     * 
     * @param trainingSinario
     *            훈련시나리오
     * @return 훈련시나리오
     * @throws Exception
     */
    public int editTrainingScenario(TrainingScenario trainingScenario) throws Exception;

    /**
     * 안전운전계획>비상사태 대비대응 > 훈련시나리오 개정이력 삭제 추가
     * 
     * @param trainingSinario
     * @return 생성 행 수
     * @throws Exception
     */
    public int revDeleteTrainingScenario(TrainingScenario trainingScenario) throws Exception;

    public int updateTrainingScenarioUseyn(@Param("trainSceGrpNo") int trainSceGrpNo) throws Exception;

}
