package Listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class contextListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent arg0)  {
        System.out.println("web 已关闭!");
    }

    public void contextInitialized(ServletContextEvent arg0)  {
        String name = arg0.getServletContext().getInitParameter("name");
        System.out.println("web 已启动!");
        System.out.println("contextInitialized: name = " + name);
    }

}
