<table>
    <tr>
        <th colspan="2">대상년도</th>
        <td colspan="2">${model.year}</td>
        <th colspan="2">사엄장</th>
        <td colspan="2">${model.plantNm}</td>
    </tr>
    <tr>
        <th colspan="2">교육대분류</th>
        <td colspan="2">${model.eduAttNm}</td>
        <th colspan="2">교육분류</th>
        <td colspan="2">${model.eduClassNm}</td>
    </tr>
        <tr>
        <th colspan="2">교육과정</th>
        <td colspan="2">${model.eduCourseNm}</td>
        <th colspan="2">교육주관구분</th>
        <td colspan="2">${model.eduTypeNm}</td>
    </tr>
    <tr>
        <th colspan="2">교육담당자</th>
        <td colspan="2">${model.deptNm}/${model.userNm}</td>
                <th colspan="2">관련법규</th>
        <td colspan="2">${model.isTypeNm}</td>
    </tr>
        <tr>
        <th colspan="2">담당부서</th>
        <td colspan="2">${model.mainDeptNm}</td>
            <th colspan="2">교육명</th>
        <td colspan="2">${model.eduNm}</td>
    </tr>
        <tr>
        <th colspan="2">관련 연간교육계획 과정</th>
        <td colspan="2">${model.eduCseSeqNm!?replace("\n", "<br/>")}</td>
                <th colspan="2">교육기간</th>
        <td colspan="2">${model.eduSYmd} ~ ${model.eduEYmd}</td>
    </tr>
       <tr>
        <th colspan="2">교육시작/종료시</th>
        <td colspan="2">${model.eduSHour} ~ ${model.eduEHour}</td>
                <th colspan="2">교육시간</th>
        <td colspan="2">${model.eduTime}</td>
    </tr>
    <tr>

            <th colspan="2">교육장소</th>
        <td colspan="2">${model.eduPlace}</td>
                <th colspan="2">교육강사</th>
        <td colspan="2">${model.eduTeacher}</td>
    </tr>
     <tr>

            <th colspan="2">교육총비용(천원)</th>
        <td colspan="2">${model.eduCost}</td>
                <th colspan="2">변경발의번호</th>
        <td colspan="2">${model.chngNum!?replace("\n", "<br/>")}</td>
    </tr>
      <tr>

            <th colspan="2">교육내용</th>
        <td colspan="5">${model.eduContent!?replace("\n", "<br/>")}</td>
    </tr>
</table>

<style>
    table {
        border-collapse: collapse;
    }

    table,  td {
        border: 1px solid black;
        width: 30%;
    }

    th {
        
	width: 20%;
    }
</style>