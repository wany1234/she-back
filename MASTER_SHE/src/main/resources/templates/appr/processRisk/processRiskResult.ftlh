<table>
    <tr>
        <th style="width:20%;">사업장</th>
        <td style="width:30%;">${model.plantNm!}</td>
        <th style="width:20%;">대상년도</th>
        <td style="width:30%;">${model.assessYear!}</td>
    </tr>
    <tr>
        <th>주관부서</th>
        <td>${model.mainDeptNm!}</td>
        <th>대상부서</th>
        <td>${model.deptNm!}</td>
    </tr>
    <tr>
        <th>평가기법</th>
        <td>${model.assessTypeNm!}</td>
        <th>구분</th>
        <td>${model.regRegdemNm!}</td>
    </tr>
    <tr>
        <th>기간</th>
        <td>${model.assessStartDt!} ~ ${model.assessEndDt!}</td>
        <th>주관부서</th>
        <td>${model.mainDeptNm!}</td>
    </tr>
    <tr>
        <th>평가명</th>
        <td colspan="3">${model.assessNm!}</td>
    </tr>
    <tr>
        <th>상세내용</th>
        <td colspan="3">${model.assessDesc!?replace("\n", "<br/>")}</td>
    </tr>
</table>