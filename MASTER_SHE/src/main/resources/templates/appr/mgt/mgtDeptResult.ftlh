<table>
    <tr>
        <th style="width:20%;">대표사업장</th>
        <td style="width:30%;">${model.plantNm}</td>
        <th style="width:20%;">목표년도/분기</th>
        <td style="width:30%;">${model.year}년 ${model.apprUnitNm}</td>
    </tr>
    <tr>
        <th style="width:20%;">작성일</th>
        <td style="width:30%;">${model.wrtDt}</td>
        <th style="width:20%;">작성부서/작성자</th>
        <td style="width:30%;">${model.deptNm}/${model.wrtUserNm}</td>
    </tr>
    <tr>
        <th style="width:20%;">실적요약</th>
        <td style="width:30%;">${model.rsltSumy!?replace("\n", "<br/>")}</td>
        <th style="width:20%;">비고</th>
        <td style="width:30%;">
        <#if model.remark?has_content>
            ${model.remark!?replace("\n", "<br/>")}
        <#else>
            &nbsp; 
        </#if>
        </td>
    </tr>
    
        
</table>
