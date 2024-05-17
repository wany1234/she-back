package com.she.env.water.baseInfo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.common.model.DefaultParam;
import com.she.env.water.baseInfo.mapper.MonPosMapper;
import com.she.env.water.model.MonPos;
import com.she.env.water.model.MonPosTestItem;

@Service
public class MonPosService {
    @Autowired
    private MonPosMapper monPosMapper;

    /**
     * 측정위치 조회
     *
     * @param useYn
     *            사용여부
     * @return 측정위치 목록
     * @throws Exception
     *             예외
     */
    public List<MonPos> getMonPoss(String useYn, String plantCd, String deptCd, String ewtrMonPosNm, DefaultParam defaultParam) throws Exception {
        return monPosMapper.getMonPoss(useYn, plantCd, deptCd, ewtrMonPosNm, defaultParam);
    }

    /**
     * 측정위치 상세조회
     *
     * @param ewtrMonPosNo
     *            측정위치번호
     * @return MonPos 측정위치
     * @throws Exception
     *             예외
     */
    public MonPos getMonPos(int ewtrMonPosNo, DefaultParam defaultParam) throws Exception {
        List<MonPosTestItem> testItems = this.monPosMapper.getMonPosTestItems(ewtrMonPosNo, "", defaultParam);
        String[] tmp = new String[testItems.size()];
        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = testItems.get(i).getEwtrTestItemCd();
        }

        MonPos monPos = this.monPosMapper.getMonPos(ewtrMonPosNo);
        monPos.setSelectedTestItemCds(tmp);
        monPos.setMonPosTestItem(testItems);

        return monPos;
    }

    /**
     * 측정위치 신규등록
     *
     * @param MonPos
     *            측정위치
     * @return ewtrMonPosNo 측정위치번호
     * @throws Exception
     *             예외
     */
    @Transactional
    public int createMonPos(MonPos monPos) throws Exception {
        int count = 0;
        count = this.monPosMapper.createMonPos(monPos);

        if (count > 0) {
            this.monPosMapper.deleteMonPosTestItem(monPos.getEwtrMonPosNo());

            for (MonPosTestItem monPosTestItem : monPos.getMonPosTestItem()) {
                monPosTestItem.setCreateUserId(monPos.getCreateUserId());
                monPosTestItem.setEwtrMonPosNo(monPos.getEwtrMonPosNo());
                count += this.monPosMapper.createMonPosTestItem(monPosTestItem);
            }
        }

        return monPos.getEwtrMonPosNo();
    }

    /**
     * 측정위치 수정
     *
     * @param MonPos
     *            측정위치
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    @Transactional
    public int updateMonPos(MonPos monPos) throws Exception {
        int count = 0;
        count = monPosMapper.updateMonPos(monPos);

        if (count > 0) {
            this.monPosMapper.deleteMonPosTestItem(monPos.getEwtrMonPosNo());

            for (MonPosTestItem monPosTestItem : monPos.getMonPosTestItem()) {
                monPosTestItem.setCreateUserId(monPos.getCreateUserId());
                monPosTestItem.setEwtrMonPosNo(monPos.getEwtrMonPosNo());
                count += this.monPosMapper.createMonPosTestItem(monPosTestItem);
            }
        }

        return count;
    }

    /**
     * 측정위치별 체크
     * 
     * @return 배출수 체크 값
     * @throws Exception
     *             예외
     */
    public int getMonPosCheck(String plantCd, String ewtrMonPosNm, String deptCd, int ewtrMonPosNo) throws Exception {
        return monPosMapper.getMonPosCheck(plantCd, ewtrMonPosNm, deptCd, ewtrMonPosNo);
    }

    /**
     * 측정위치별 실험항목 조회
     *
     * @param ewtrMonPosNo
     * @return 측정위치별 실험항목 목록
     * @throws Exception
     *             예외
     */
    public List<MonPosTestItem> getMonPosTestItems(int ewtrMonPosNo, String ewtrTestItemCd, DefaultParam defaultParam) throws Exception {
        return monPosMapper.getMonPosTestItems(ewtrMonPosNo, ewtrTestItemCd, defaultParam);
    }

}
