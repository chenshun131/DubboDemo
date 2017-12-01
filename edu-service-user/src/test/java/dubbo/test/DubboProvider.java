package dubbo.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * User: chenshun131 <p />
 * Time: 17/11/12 17:54  <p />
 * Version: V1.0  <p />
 * Description: 启动Dubbo服务用的MainClass <p />
 */
public class DubboProvider {

    private static final Log log = LogFactory.getLog(DubboProvider.class);

    public static void main(String[] args) {
        try {
            ClassPathXmlApplicationContext context =
                    new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
            context.start();
        } catch (Exception e) {
            log.error("== DubboProvider context start error:", e);
        }
        synchronized (DubboProvider.class) {
            while (true) {
                try {
                    DubboProvider.class.wait();
                } catch (InterruptedException e) {
                    log.error("== synchronized error:", e);
                }
            }
        }
    }

}
