package cn.stylefeng.guns;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.stereotype.Component;

/**
 * @Description: TODO
 * @Duthor: lichenfeng
 * @Date: 2019/9/27 8:57
 * @Version 1.0
 */
@Component
public class ServerDisposableListener implements DisposableBean, ExitCodeGenerator {
    @Override
    public void destroy() throws Exception {
        System.out.println("服务器销毁------------------------------");
    }

    @Override
    public int getExitCode() {
        return 0;
    }
}
