<table>
    <tr>
        <th style="width:20%;">사업장</th>
        <td style="width:30%;">${model.plantNm}</td>
        <th style="width:20%;">목표년도</th>
        <td style="width:30%;">${model.year}</td>
    </tr>
    <tr>
        <th style="width:20%;">부서</th>
        <td style="width:30%;">${model.deptNm}</td>
        <th style="width:20%;">작성일자</th>
        <td style="width:30%;">${model.wrtDt}</td>
    </tr>
    <tr>
        <th style="width:20%;">작성자</th>
        <td style="width:30%;">${model.wrtUserNm}</td>
        <th style="width:20%;">검토담당자</th>
        <td style="width:30%;">${model.chkDeptNm}/${model.chkMgrNm}</td>
    </tr>
        <tr>
        <th style="width:20%;">부서방침</th>
        <td style="width:30%;">${model.policy!?replace("\n", "<br/>")}</td>
        <th style="width:20%;">부서목표</th>
        <td style="width:30%;">${model.target!?replace("\n", "<br/>")}</td>
    </tr>
        <tr>
        <th style="width:20%;">부서개요</th>
        <td style="width:30%;">${model.summary!?replace("\n", "<br/>")}</td>
        <th style="width:20%;">비고</th>
        <td style="width:30%;">${model.remark!?replace("\n", "<br/>")}</td>
    </tr>
       <tr>
        <th style="width:20%;">검토결과</th>
        <td style="width:30%;">${model.chkRsltNm}</td>
        <th style="width:20%;">검토완료일자</th>
        <td style="width:30%;">${model.chkEndDt}</td>
    </tr>
    <tr>
        <th style="width:20%;">검토의견</th>
        <td style="width:30%;">${model.chkContent!?replace("\n", "<br/>")}</td>
            <th style="width:20%;"></th>
        <td style="width:30%;"></td>
    </tr>
</table>

