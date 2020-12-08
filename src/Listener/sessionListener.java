package Listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class sessionListener implements HttpSessionListener {
    public static int userNumber = 0;

    @Override
    public void sessionCreated(HttpSessionEvent arg0) {
        userNumber++;
        arg0.getSession().setAttribute("userNumber", userNumber);
        System.out.println("用户" + arg0.getSession().getAttributeNames() + "会话已创建");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent arg0) {
        userNumber--;
        arg0.getSession().setAttribute("userNumber", userNumber);
        System.out.println("用户" + arg0.getSession().getAttributeNames() + "会话已关闭");
    }
}
