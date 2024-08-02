<table>
    <tr>
        <th style="width:20%;">사업장</th>
        <td style="width:30%;">${model.plantNm!}</td>
        <th style="width:20%;">년도</th>
        <td style="width:30%;">${model.auditYear!}</td>
    </tr>
    <tr>
        <th>감사종류</th>
        <td>${model.auditTypeNm!}</td>
        <th>감사기간</th>
        <td>${model.auditStartDt!} ~ ${model.auditEndDt!}</td>
    </tr>
    <tr>
        <th>감사명</th>
        <td colspan="3">${model.auditNm!}</td>
    </tr>
    <tr>
        <th colspan="4">감사원의 역활과 책임</th>
    </tr>
    <tr>
        <th>선임감사원</th>
        <td>${model.seniorAudidorRole!?replace("\n", "<br/>")}</td>
        <th>감사원</th>
        <td>${model.audidorRole!?replace("\n", "<br/>")}</td>
    </tr>
    <tr>
        <th colspan="4">감사원의 소견</th>
    </tr>
    <tr>
        <th>선임감사원</th>
        <td>${model.seniorAuditorRemark!?replace("\n", "<br/>")}</td>
        <th>감사원</th>
        <td>${model.auditorRemark!?replace("\n", "<br/>")}</td>
    </tr>
    <tr>
        <th>차기감사시 반영사항</th>
        <td colspan="3">${model.nextRemark!?replace("\n", "<br/>")}</td>
    </tr>
    <tr>
        <th>비고</th>
        <td colspan="3">${model.remark!?replace("\n", "<br/>")}</td>
    </tr>
</table>