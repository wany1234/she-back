발의번호: ${chnage.safChngNo!}
<br><br>
발의부서/발의담당자: ${chnage.rqstDeptNm!} / ${chnage.rqstUserNm!}
<br><br>
발의일자: ${chnage.rqstDt!}
<br><br>
변경절차: ${chnage.chngAttNm!}
<br><br>
변경등급(발의): ${chnage.rqstMocLvlNm!}
<br><br>
변경요소: ${chnage.chngElem!}
<br><br>
접수부서/담당자: ${chnage.recevDeptNm!} / ${chnage.recevUserNm!}
<br><br>
변경검토승인여부: ${chnage.chkApprYnNm!}
<br><br>
변경검토완료일자: ${chnage.chkConfDt!}
<br><br>
<#if chnage.chkApprYn == "Y">
해당 변경 발의사항에 대해 변경검토승인여부가 “${chnage.chkApprYnNm!}”으로 검토완료되었습니다.
아래 바로 가기를 클릭하여 변경 검토결과를 확인하고 변경실시 후 실시결과를 확인하시기 바랍니다.
<#else>
해당 변경 발의사항에 대해 변경검토승인여부가 “${chnage.chkApprYnNm!}”로 검토완료되고, 변경종료처리되었습니다.
아래 바로 가기를 클릭하여 변경 검토결과를 확인하시기 바랍니다.
</#if>

