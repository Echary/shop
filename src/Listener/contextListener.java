package Listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class contextListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent arg0)  {
        System.out.println("web �ѹر�!");
    }

    public void contextInitialized(ServletContextEvent arg0)  {
        String name = arg0.getServletContext().getInitParameter("name");
        System.out.println("web ������!");
        System.out.println("contextInitialized: name = " + name);
    }

}
