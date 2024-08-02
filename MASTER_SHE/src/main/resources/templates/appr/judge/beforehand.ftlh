<table>
    <tr>
        <th>사업장</th>
        <td>${model.plantNm!}</td>
        <th>신청자</th>
        <td>${model.rqstDeptNm!}/${model.rqstUserNm!}</td>
    </tr>
    <tr>
        <th>신청일</th>
        <td colspan="3">${model.rqstYmd!}</td>
    </tr>
    <tr>
        <th>공사구분</th>
        <td colspan="3">
            <#list model.wkTypeList as item>
            ${item.wkTypeNm!}<BR>
            </#list>
        </td>
    </tr>
    <tr>
        <th width="150px">공사명(변경부자재)</th>
        <td colspan="3">${model.wkNm!}</td>
    </tr>
    <tr>
        <th>공사변경내용</th>
        <td colspan="3">${model.wkChngContent!?replace("\n", "<br/>")}</td>
    </tr>
    <tr>
        <th>공사개시일</th>
        <td>${model.wkSYmd!}</td>
        <th>완료예정일</th>
        <td>${model.wkCfmYmd!}</td>
    </tr>
    <tr>
        <th>접수담당자</th>
        <td colspan="3">${model.receDeptNm!}/${model.receUserNm!}</td>
    </tr>
</table>