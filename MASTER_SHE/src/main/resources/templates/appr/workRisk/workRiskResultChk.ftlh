<table>
    <tr>
        <th style="width:20%;">사업장</th>
        <td style="width:30%;">${model.plantNm!}</td>
        <th style="width:20%;">평가년도/구분</th>
        <td style="width:30%;">${model.evalYear!}/${model.evalTypeNm!}</td>
    </tr>
    <tr>
        <th>평가명</th>
        <td>${model.evalNm!}</td>
        <th>평가계획기간</th>
        <td>${model.planPeriod!}</td>
    </tr>
    <tr>
        <th>평가계획부서</th>
        <td>${model.mainDeptNm!}</td>
        <th>평가계획담당자</th>
        <td>${model.mgrNm!}</td>
    </tr>
    <tr>
        <th>평가대상부서</th>
        <td>${model.deptNm!}</td>
        <th>대표평가자</th>
        <td>${model.deptEvalUserNm!}</td>
    </tr>
    <tr>
        <th>검토담당자</th>
        <td>${model.chkUserNm!}</td>
        <th></th>
        <td></td>
    </tr>
    <tr>
        <th>비고</th>
        <td colspan="3">${model.remark!?replace("\n", "<br/>")}</td>
    </tr>
</table>