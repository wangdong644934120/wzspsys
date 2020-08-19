package cn.stylefeng.guns.core.aspect;

import cn.stylefeng.guns.core.context.ProjectContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 项目切换切面
 */
@Aspect
@Component
public class ProjectSwitchAspect {

    @Pointcut("@within(org.springframework.stereotype.Controller)")
    //@Pointcut("@annotation(cn.plou.web.common.annotation.EnableDbDirect)||@within(cn.plou.web.common.annotation.EnableDbDirect)")
    //@Pointcut(("execution(* cn.stylefeng.guns..*.*controller.*(..))"))
    private void pointCutMethod() {
    }

    //声明前置通知
    @Before("pointCutMethod()")
    public void doBefore(JoinPoint point) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            String project = "";
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("project")){
                    project=cookie.getValue();
                    break;
                }
            }
//            if(StringUtils.isEmpty(companyId)){
//                companyId=request.getHeader("ActionID");
//            }
            if(StringUtils.isNotEmpty(project)){
                ProjectContextHolder.set(project);
            }else{
                ProjectContextHolder.set(null);
            }
        }
    }
}
