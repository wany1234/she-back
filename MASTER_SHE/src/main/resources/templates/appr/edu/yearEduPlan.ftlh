<table>
    <tr>
        <th style="width:20%;">사업장</th>
        <td style="width:30%;">${model.plantNm}</td>
        <th style="width:20%;">작성부서/작성자</th>
        <td style="width:30%;">${model.createNm}</td>
    </tr>
    <tr>
        <th style="width:20%;">교육년도</th>
        <td style="width:30%;">${model.year}</td>
        <th style="width:20%;">작성일</th>
        <td style="width:30%;">${model.createDt}</td>
    </tr>
    <tr>
        <th style="width:20%;">비고</th>
        <td style="width:30%;">${model.remark!?replace("\n", "<br/>")}</td>
        <th style="width:20%;"></th>
        <td style="width:30%;"></td>
    </tr>
        
     
    
</table>

