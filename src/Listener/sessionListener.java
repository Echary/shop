package Listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class sessionListener implements HttpSessionListener {
    public static int userNumber = 0;

    @Override
    public void sessionCreated(HttpSessionEvent arg0) {
        userNumber++;
        arg0.getSession().setAttribute("userNumber", userNumber);
        System.out.println("�û�" + arg0.getSession().getAttributeNames() + "�Ự�Ѵ���");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent arg0) {
        userNumber--;
        arg0.getSession().setAttribute("userNumber", userNumber);
        System.out.println("�û�" + arg0.getSession().getAttributeNames() + "�Ự�ѹر�");
    }
}
