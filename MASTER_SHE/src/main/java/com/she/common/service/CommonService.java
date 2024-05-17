package com.she.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.common.mapper.CommonMapper;
import com.she.common.model.CodeDomain;
import com.she.common.model.DefaultParam;
import com.she.common.model.Dept;
import com.she.common.model.Menu;
import com.she.common.model.MenuTreeBase;
import com.she.manage.mapper.AuthGrpMenuMapper;

@Service
public class CommonService {

    @Autowired
    private CommonMapper commonMapper;

    @Autowired
    private AuthGrpMenuMapper authGrpMenuMapper;

    public List<Dept> getDepts() throws Exception {
        return commonMapper.getDepts();
    }

    /**
     * ///// 메뉴 영역 시작 /////
     */
    /**
     * 메뉴 등록 서비스
     * 
     * @param menu
     *            : 메뉴 모델
     * @return : 등록된 메뉴 ID
     */
    public String createMenu(Menu menu) throws Exception {
        // TODO : transaction 처리 할 것
        String menuId = this.generateMenuId(menu.getTaskGrpCd(), menu.getMenuLvl());
        menu.setMenuId(menuId);

        // 메뉴 레벨이 1이 아니라면
        if (menu.getMenuLvl() != 1) {
            // 아이콘과 아이콘 색상의 값을 null 처리한다.
            // 메뉴 레벨이 1레벨인 경우에만 아이콘과 색상을 가짐
            menu.setIcon(null);
            menu.setColor(null);
        }

        commonMapper.createMenu(menu);
        return menuId;
    }

    /**
     * ///// 메뉴 영역 시작 /////
     */
    /**
     * 메뉴 ID 생성 함수
     * 
     * @param taskGrpCd
     *            : 업무 그룹 코드(com_code_domain에 정의)
     * @param menuLvl
     *            : 메뉴 레벨 1~9까지
     * @return 메뉴 ID
     * @throws Exception
     */
    public String generateMenuId(String taskGrpCd, int menuLvl) throws Exception {
        // String menuId = "";
        // int menuCount = commonMapper.getMenuCount(taskGrpCd, menuLvl);
        // menuCount++;
        // // 업무코드 + 레벨 1자리 + 0001
        // menuId = String.format("%s%d%s", taskGrpCd, menuLvl,
        // Methods.leftPad(String.valueOf(menuCount), 4, '0'));
        return taskGrpCd + (commonMapper.getMenuCount(taskGrpCd, menuLvl));
    }

    /**
     * 메뉴 목록 조회 서비스
     * 
     * @param menu
     *            : 메뉴 모델 형태 검색 조건
     * @return 검색 조건에 맞는 메뉴 목록
     * @throws Exception
     */

    /**
     * 메뉴 목록 조회 서비스
     * 
     * @param taskGrpCd
     *            : 업무 그룹 코드
     * @param menuNm
     *            : 메뉴명
     * @param upMenuId
     *            : 상위메뉴ID
     * @param menuLvl
     * @param useYn
     *            : 사용여부
     * @return 메뉴 리스트
     * @throws Exception
     */
    public List<Menu> getMenus(String taskGrpCd, String menuNm, String upMenuId, Integer menuLvl, String useYn, DefaultParam defaultParam) throws Exception {
        return commonMapper.getMenus(taskGrpCd, menuNm, upMenuId, menuLvl, useYn, defaultParam);
    }

    /**
     * 메뉴 수정 서비스
     * 
     * @param menu
     * @return
     * @throws Exception
     */
    @Transactional
    public int updateMenu(Menu menu) throws Exception {
        int orginMenuLevel = 0;

        // 메뉴id가 없다면 0 리턴
        if (menu.getMenuId() == null || "".equals(menu.getMenuId())) {
            return 0;
        }

        // 메뉴 레벨이 1이 아니라면
        if (menu.getMenuLvl() != 1) {
            // 아이콘과 아이콘 색상의 값을 null 처리한다.
            // 메뉴 레벨이 1레벨인 경우에만 아이콘과 색상을 가짐
            menu.setIcon(null);
            menu.setColor(null);
        }

        // 수정되기 전의 메뉴정보를 검색해 메뉴 레벨을 가지고 있는다
        Menu originMenu = commonMapper.getMenu(menu.getMenuId());
        if (originMenu != null) {
            orginMenuLevel = originMenu.getMenuLvl();
        }

        // 메뉴 수정
        int resultNo = commonMapper.updateMenu(menu);

        // 상위메뉴의 정보가 바뀌었으며 하위메뉴가 존재하는지 확인
        // 바뀌었으며 하위메뉴가 있다면
        if (menu.isChangeUpMenu()) {
            // 하위 메뉴들의 정보를 가지고 온다.
            List<MenuTreeBase> subMenus = commonMapper.getMenuTreeBases(null, "", null, null, null, menu.getMenuId());

            if (subMenus != null && subMenus.size() > 0) {
                for (MenuTreeBase subMenu : subMenus) {
                    // 업무그룹을 그대로 가져간다.
                    subMenu.setTaskGrpCd(menu.getTaskGrpCd());
                    // 하위 메뉴의 레벨과 기존 상위메뉴의 레벨 차이만큼 수정된 메뉴레벨에 더한다.
                    subMenu.setMenuLvl(String.valueOf(menu.getMenuLvl() + (Integer.parseInt(subMenu.getMenuLvl()) - orginMenuLevel)));
                    subMenu.setUpdateUserId(menu.getUpdateUserId());
                    // 하위메뉴 수정
                    resultNo += commonMapper.updateSubMenu(subMenu);
                }
            }
        }

        /**
         * [사용여부가 'N'으로 바뀐 경우]
         * 
         * 하위 메뉴들의 사용여부도 전부 'N'처리
         * 
         * [사용여부가 'Y'으로 바뀐 경우]
         * 
         * 하위 메뉴들의 사용여부도 전부 'Y'처리
         * 
         * 권한별 메뉴정보가 엮여 있다면 해당 데이터를 삭제처리하여 권한별 메뉴 화면에서 제대로 된 정보가 보이도록 처리
         */
        // 현재 메뉴의 사용여부가 N으로 바뀌었다면 그 아래에 있는 메뉴들도 사용여부 N으로 변경, 현재 메뉴의 사용여부가 Y으로
        // 바뀌었다면 그 아래에 있는 메뉴들도 사용여부 Y으로 변경
        if (menu.getUseYn().equals("N")) {
            // 권한별 메뉴 테이블에 저장되어져 있는 데이터 정보를 지운다.
            authGrpMenuMapper.deleteAuthMenuAccordingMenuId(menu.getMenuId());
        }
        // 하위 메뉴들의 정보를 가지고 온다.
        List<MenuTreeBase> subMenus = commonMapper.getMenuTreeBases(null, "", null, null, null, menu.getMenuId());

        if (subMenus != null && subMenus.size() > 0) {
            for (MenuTreeBase subMenu : subMenus) {
                if (menu.getUseYn().equals("N")) {
                    // 사용여부를 N으로 바꾼다.
                    subMenu.setUseYn("N");
                } else {
                    // 사용여부를 Y으로 바꾼다.
                    subMenu.setUseYn("Y");
                }
                subMenu.setUpdateUserId(menu.getUpdateUserId());
                // 하위메뉴 수정
                resultNo += commonMapper.updateSubMenuUseYn(subMenu);
                if (menu.getUseYn().equals("N")) {
                    // 권한별 메뉴 테이블에 저장되어져 있는 데이터 정보를 지운다.
                    authGrpMenuMapper.deleteAuthMenuAccordingMenuId(subMenu.getMenuId());
                }
            }
        }

        return resultNo;
    }

    /**
     * 트리 메뉴를 구성할 기초 메뉴 정보 조회 서비스
     * 
     * @return
     * @throws Exception
     * @param taskGrpCd
     * @param menuNm
     * @param upMenuId
     * @param menuLvl
     * @param useYn
     */
    public List<MenuTreeBase> getMenuTreeBases(String taskGrpCd, String menuNm, String upMenuId, Integer menuLvl, String useYn) throws Exception {
        return commonMapper.getMenuTreeBases(taskGrpCd, menuNm, upMenuId, menuLvl, useYn, "");
    }

    /**
     * ///// 메뉴 영역 종료 /////
     */

    /**
     * ///// 공통 코드 영역 /////
     */

    public List<CodeDomain> getCodeDomains() throws Exception {
        return commonMapper.getCodeDomains();
    }

    public CodeDomain getCodeDomain() throws Exception {
        return commonMapper.getCodeDomain();
    }

    /**
     * ///// 공통 코드 영역 종료 /////
     */
}
