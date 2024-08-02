<table>
    <tr>
        <th style="width:20%;">사업장</th>
        <td style="width:30%;">${model.plantNm!}</td>
        <th style="width:20%;">대상년도</th>
        <td style="width:30%;">${model.year!}</td>
    </tr>
    <tr>
        <th>구분</th>
        <td>${model.cmiClsNm!}</td>
        <th>주기</th>
        <td>${model.halfTypeNm!}</td>
    </tr>
    <tr>
        <th>주관부서</th>
        <td colspan="3">${model.mainDeptNm!}</td>
    </tr>
    <tr>
        <th>장소</th>
        <td>${model.confPlace!}</td>
        <th>일시</th>
        <td>${model.confDate!}</td>
    </tr>
    <tr>
        <th>제목</th>
        <td colspan="3">${model.confNm!}</td>
    </tr>
    <tr>
        <th>비고</th>
        <td colspan="3">${model.remark!?replace("\n", "<br/>")}</td>
    </tr>
</table>