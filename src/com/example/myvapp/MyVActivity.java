package com.example.myvapp;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import com.vaadin.server.VaadinServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class MyVActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        new MyTask().execute();
    }

    class MyTask extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... params) {
            Server server = new Server(8080);

            ServletContextHandler appContext = new ServletContextHandler(ServletContextHandler.SESSIONS);
            appContext.setContextPath("/test");
            appContext.addServlet(TestUi.TestServlet.class, "/*");
            server.setHandler(appContext);

            try {
                server.start();
                server.join();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return null;
        }
    }
}
