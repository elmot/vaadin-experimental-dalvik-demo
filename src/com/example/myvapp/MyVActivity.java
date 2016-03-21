package com.example.myvapp;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://127.0.0.1:8080/test/?debug"));
                    startActivity(myIntent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(MyVActivity.this, "No application can handle this request."
                            + " Please install a webbrowser", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });
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
