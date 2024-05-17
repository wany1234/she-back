package com.she.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.CodeDomain;
import com.she.common.model.DefaultParam;
import com.she.common.model.Dept;
import com.she.common.model.Menu;
import com.she.common.model.MenuTreeBase;

@Mapper
@Repository("com.she.common.mapper.CommonMapper")
public interface CommonMapper {

    public List<Dept> getDepts() throws Exception;

    /**
     * ///// 메뉴 영역 시작 /////
     */
    /**
     * 메뉴 등록용 mapper
     * 
     * @param menu
     */
    public void createMenu(Menu menu) throws Exception;

    /**
     * 업무별 메뉴 개수 조회용 mapper
     * 
     * @param taskGrpCd
     *            : 업무 코드
     * @param menuLvl
     * @return 업무별 메뉴 개수
     * @throws Exception
     */
    public int getMenuCount(@Param("taskGrpCd") String taskGrpCd, @Param("menuLvl") int menuLvl) throws Exception;

    /**
     * 업무별 메뉴 목록 조회용 mapper
     * 
     * @param taskGrpCd
     *            : 업무 코드
     * @param menuNm
     *            : 메뉴명
     * @param upMenuId
     *            : 상위 메뉴 ID
     * @param menuLvl
     *            : 메뉴 level
     * @param useYn
     *            : 사용 여부
     * @return
     * @throws Exception
     */
    public List<Menu> getMenus(@Param("taskGrpCd") String taskGrpCd, @Param("menuNm") String menuNm, @Param("upMenuId") String upMenuId, @Param("menuLvl") Integer menuLvl, @Param("useYn") String useYn, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 업무별 메뉴 상세 조회용 mapper
     * 
     * @param menuId
     *            : 메뉴 ID
     * @return
     * @throws Exception
     */
    public Menu getMenu(@Param("menuId") String menuId) throws Exception;

    /**
     * 트리 메뉴를 구성할 기초 메뉴 정보 조회 mapper
     * 
     * @return
     * @throws Exception
     * @param taskGrpCd
     * @param menuNm
     * @param upMenuId
     * @param menuLvl
     * @param useYn
     */
    public List<MenuTreeBase> getMenuTreeBases(@Param("taskGrpCd") String taskGrpCd, @Param("menuNm") String menuNm, @Param("upMenuId") String upMenuId, @Param("menuLvl") Integer menuLvl, @Param("useYn") String useYn, @Param("changeForUpMenuId") String changeForUpMenuId) throws Exception;

    /**
     * ///// 메뉴 영역 종료 /////
     */

    /**
     * ///// 공통 코드 영역 /////
     */
    public List<CodeDomain> getCodeDomains() throws Exception;

    public CodeDomain getCodeDomain() throws Exception;

    /**
     * 메뉴 수정
     * 
     * @param menu
     * @return
     * @throws Exception
     */
    public Integer updateMenu(Menu menu) throws Exception;

    /**
     * 하위메뉴 수정
     * 
     * @param menu
     * @return
     * @throws Exception
     */
    public Integer updateSubMenu(MenuTreeBase menu) throws Exception;

    /**
     * 하위메뉴 수정2
     * 
     * @param menu
     * @return
     * @throws Exception
     */
    public Integer updateSubMenuUseYn(MenuTreeBase menu) throws Exception;

    /**
     * ///// 공통 코드 영역 종료 /////
     */

}
