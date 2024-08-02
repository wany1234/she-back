<table>
	<tr>
		<th style="width:20%;">사업장</th>
		<td style="width:30%;">${model.plantNm!}</td>
		<th style="width:20%;">점검대분류</th>
		<td style="width:30%;">${model.chngKindNm!}</td>
	</tr>
	<tr>
		<th>점검중분류</th>
		<td>${model.chngKindNm!}</td>
		<th>점검종류</th>
		<td>${model.safCheckKindNm!}</td>
	</tr>
	<tr>
		<th>점검일</th>
		<td>${model.checkYmd!}</td>
		<th>점검부서</th>
		<td>${model.deptNm!}</td>
	</tr>
	<tr>
		<th>점검명</th>
		<td colspan="3">${model.checkTitle!}</td>
	</tr>
	<tr>
		<th>점검결과요약</th>
		<td colspan="3">${model.checkResult!?replace("\n", "<br/>")}</td>
	</tr>
</table>