<table>
    <tr>
        <th>진행단계</th>
        <td colspan="3">${model.imprStepNm!}</td>
    </tr>
	<tr>
		<th style="width:20%;">사업장</th>
		<td style="width:30%;">${model.plantNm!}</td>
		<th style="width:20%;">업무구분</th>
		<td style="width:30%;">${model.imprClassNm!}</td>
	</tr>
	<tr>
		<th>작성일/작성자</th>
		<td>${model.imprRqstYmd!} / ${model.imprRqstUserInfo!}</td>
		<th>개선투자비용</th>
		<td>${model.actCost!}</td>
	</tr>
	<tr>
        <th>제목</th>
        <td colspan="3">${model.imprTitle!}</td>
    </tr>
    <tr>
        <th>조치 요청내용</th>
        <td colspan="3">${model.imprRqstContents!?replace("\n", "<br/>")}</td>
    </tr>
	<tr>
		<th>조치부서/조치자</th>
		<td>${model.actDeptNm!} / ${model.actUserNm!}</td>
		<th>조치기한</th>
		<td>${model.actLimitYmd!}</td>
	</tr>m
	<tr>
        <th>조치일</th>
        <td colspan="3">${model.actConfirmYmd!}</td>
    </tr>
    <tr>
        <th>조치 결과내용</th>
        <td colspan="3">${model.actResultContents!?replace("\n", "<br/>")}</td>
    </tr>
</table>