<table>
    <tr>
        <td colspan="4">
 <span style="font-weight:bold"> ※ 휴업일수 1일 미만</span><br>
 <span>&nbsp;&nbsp;- 결재선 : 사고부서장-안전환경팀장(협조)-지원팀장(협조)-설비팀장(협조)-안전환경실장(협조)-공장장</span><br>
 <span>&nbsp;&nbsp;- 사전참조 : CEO, 경영지원본부장, 생산본부장, 전 팀장 및 계장, 안전환경실</span><br>
 <span style="font-weight:bold"> ※ 휴업일수 1일 이상</span><br>
 <span>&nbsp;&nbsp;- 결재선 : 사고부서장-안전환경팀장(협조)-지원팀장(협조)-설비팀장(협조)-안전환경실장(협조)-공장장-생산본부장-CEO</span><br>
 <span>&nbsp;&nbsp;- 사전참조 : 경영지원본부장, CFO, 전 팀장 및 계장, 안전환경실</span><br>
        </td>
    </tr>
    <tr>
        <th style="width:20%;">사업장</th>
        <td style="width:30%;">${model.plantNm!}</td>
        <th style="width:20%;">발행번호</th>
        <td style="width:30%;">${model.accidentNum!}</td>
    </tr>
    <tr >
        <th>사고명</th>
        <td colspan="3">${model.accidentTitle!}</td>
        
    </tr>
    <tr>
        <th>보고자/보고일시</th>
        <td>${model.rptUserId!}/${model.rptUserNm!}</td>
        <td>${model.rptDeptNm!}</td>
        <td>${model.rptDt!}</td>
    </tr>
    <tr>
        <th>사고종류</th>
        <td colspan="3">${model.accidentTypeNms!}</td>
    </tr>
    <tr>
        <th>사고자구분</th>
        <td colspan="3">${model.accKindNms!}</td>
    </tr>
    <tr>
        <th>발생일시</th>
        <td>${model.accidentDateTime!}</td>
        <th>발행부서</th>
        <td>${model.deptNm!}</td>
    </tr>
     <tr>
        <th>발생장소</th>
        <td>${model.area!}</td>
        <th>날씨</th>
        <td>${model.weatherNm!}</td>
    </tr>
     <tr>
        <th>사고경위</th>
        <td colspan="3">${model.contents!?replace("\n", "<br/>")}</td>
    </tr>
     <tr>
        <th>사고발생 개요</th>
        <td colspan="3">${model.firstAct!?replace("\n", "<br/>")}</td>
    </tr>
     <tr>
        <th>안전수칙 위반사항</th>
        <td colspan="3">${model.etc!?replace("\n", "<br/>")}</td>
    </tr>
</table>