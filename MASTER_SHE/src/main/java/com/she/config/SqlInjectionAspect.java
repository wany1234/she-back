package com.she.config;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.she.security.util.StringUtil;

@Aspect
@Component
public class SqlInjectionAspect {

    public static final String[] SQL_KEY_WORDS = { "SELECT", "INSERT", "UPDATE", "DELETE", "CREATE", "DROP", "ALTER", "OR", "AND", "UNION", "INTERSECT", "MINUS", "EXCEPT", "JOIN" };

    @Around(value = "execution(* com..controller.*Controller.*(..))")
    public Object logging(ProceedingJoinPoint pjp) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        String method = StringUtil.isNotEmpty(request.getMethod()) ? request.getMethod().toUpperCase() : "";

        if ("GET".equals(method) && StringUtil.isNotEmpty(request.getParameter("orderByExpression"))) {
            // 서버 페이지을 하는 경우 받는 정렬 정보에서 sql 삽입이 이루어질 수 있음
            // 해당 부분 AOP로 체크 후 반환 처리
            // sql 삽입이 감지되면 null로 반환
            String orderByExpression = request.getParameter("orderByExpression").toUpperCase();
            for (String keyWord : SQL_KEY_WORDS) {
                if (orderByExpression.contains(keyWord)) {
                    ModifiableHttpServletRequest m = new ModifiableHttpServletRequest(request);
                    m.setParameter("orderByExpression", "");
                    request = (HttpServletRequest) m;

                    break;
                }
            }
        }

        return pjp.proceed();

    }

}
