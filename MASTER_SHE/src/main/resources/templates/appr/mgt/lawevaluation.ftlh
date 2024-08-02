<table>
    <tr>
        <th>사업장</th>
        <td>${model.plantCd!}</td>
        <th>평가년도</th>
        <td>${model.evalYear!}</td>
    </tr>
    <tr>
        <th>평가구분</th>
        <td>${model.evalTypeNm!}/${model.halfTypeNm!}</td>
        <th>평가명</th>
        <td>${model.evalNm!}</td>
    </tr>
    <tr>
        <th>평가기간</th>
        <td>${model.planSymd!}~${model.planEymd!}</td>
        <th>주관부서/담당자</th>
        <td>${model.deptNm!}/${model.mgrNm!}</td>
    </tr>
    <tr>
        <th width="150px">비고</th>
        <td colspan="3">${model.remark!?replace("\n", "<br/>")}</td>
    </tr>
</table>