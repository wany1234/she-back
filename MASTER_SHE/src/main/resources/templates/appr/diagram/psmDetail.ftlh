<table>
    <tr>
        <th>사업장</th>
        <td>
           <#if model.plantNm?has_content>
            ${model.plantNm}
        <#else>
            &nbsp; 
        </#if>
        </td>
        <th>문서구분</th>
                <td> 
                   <#if model.categoryNm?has_content>
            ${model.categoryNm}
        <#else>
            &nbsp; 
        </#if>
        </td>
         
    </tr>
    <tr>
        <th>Category</th>
          <td> 
          
             <#if model.subCategoryNm?has_content>
            ${model.subCategoryNm}
        <#else>
            &nbsp; 
        </#if>
        </td>
        <th>부서명</th>
        <td>
          <#if model.deptNm?has_content>
            ${model.deptNm}
        <#else>
            &nbsp; 
        </#if>
        </td>
    </tr>
    <tr>
        <th>문서번호</th>
        <td>
         <#if model.docuGrpNo?has_content>
            ${model.docuGrpNo}
        <#else>
            &nbsp; 
        </#if>
        </td>
        <th>최근작성일</th>
        <td>
        <#if model.docWriteDt?has_content>
            ${model.docWriteDt}
        <#else>
            &nbsp; 
        </#if>
        </td>
    </tr>
        <tr>
        <th>이력번호</th>
        <td>
        <#if model.revNum?has_content>
            ${model.revNum}
        <#else>
            &nbsp; 
        </#if>
        </td>
        <th>최근검토일</th>
        <td>
        <#if model.nextCheckDt?has_content>
            ${model.nextCheckDt}
        <#else>
            &nbsp; 
        </#if></td>
    </tr>
        <tr>
        <th>차기검토일</th>
        <td>
           <#if model.nextCheckDt?has_content>
            ${model.nextCheckDt}
        <#else>
            &nbsp; 
        </#if>
        </td>
        <th>문서명</th>
        <td>
        <#if model.docuTitle?has_content>
            ${model.docuTitle}
        <#else>
            &nbsp; 
        </#if>
        </td>
    </tr>
  
    <tr>
        <th>조치담당자</th>
        <td>
           <#if model.actMgrNm?has_content>
            ${model.actMgrNm}
        <#else>
            &nbsp; 
        </#if>
        
        </td>
             <th>담당자이메일</th>
        <td>
        <#if model.actEmail?has_content>
            ${model.actEmail}
        <#else>
            &nbsp; 
        </#if>
        </td>
    </tr>
      <tr>
        <th>내용</th>
        <td colspan="3">
           <#if model.docuContents?has_content>
            ${model.docuContents}
        <#else>
            &nbsp; 
        </#if>
        </td>
    </tr>
     <tr>
        <th>개정사유</th>
        <td colspan="3">
        <#if model.revContents?has_content>
            ${model.revContents}
        <#else>
            &nbsp; 
        </#if>
       </td>
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