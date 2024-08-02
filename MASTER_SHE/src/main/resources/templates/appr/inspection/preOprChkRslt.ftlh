<table>
	<tr>
		<th style="width:20%;">사업장</th>
		<td style="width:30%;">${model.plantNm!}</td>
		<th style="width:20%;">라인구분</th>
		<td style="width:30%;">${model.processClsNm!}</td>
	</tr>
	<tr>
		<th>라인명</th>
		<td>${model.processNm!}</td>
		<th>점검유형</th>
		<td>${model.safCheckTypeNm!}</td>
	</tr>
	<tr>
		<th>점검부서</th>
		<td>${model.deptNm!}</td>
		<th>점검수행자</th>
		<td>${model.chkUserNm!}</td>
	</tr>
	<tr>
		<th>점검기간</th>
		<td colspan="3">${model.chkYmd!}</td>
	</tr>
	<tr>
		<th>점검명</th>
		<td colspan="3">${model.chkTitle!}</td>
	</tr>
	<tr>
		<th>점검결과</th>
		<td colspan="3">${model.chkRsltNm!}</td>
	</tr>
	<tr>
		<th>점검결과요약</th>
		<td colspan="3">${model.chkRsltDesc!?replace("\n", "<br/>")}</td>
	</tr>
	<tr>
		<th>관련변경관리</th>
		<td colspan="3">${model.chngNum!}</td>
	</tr>
</table>