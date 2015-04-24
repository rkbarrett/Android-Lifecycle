package com.msi.ekb015.androidlifecycle;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;


public class MainActivity extends ActionBarActivity {
    private ArrayList<LifecycleInfo> mLifecycleInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLifecycleInfo = new ArrayList<>();

        //record it
        recordLifecycleChange("onCreate");

        ListView listView = (ListView)findViewById(R.id.lifecycleList);
        ArrayAdapter<LifecycleInfo> =
    }

    @Override
    protected void onStart() {
        super.onStart();
        recordLifecycleChange("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        recordLifecycleChange("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        recordLifecycleChange("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        recordLifecycleChange("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        recordLifecycleChange("onDestroy");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void recordLifecycleChange(String methodName) {
        Date date = new Date();
        LifecycleInfo lifecycleInfo = new LifecycleInfo(methodName, date);
        mLifecycleInfo.add(lifecycleInfo);

        Toast.makeText(this, methodName + "() invoked", Toast.LENGTH_SHORT).show();
    }

    public class LifecycleAdapter extends ArrayAdapter<LifecycleInfo> {
        public LifecycleAdapter(Context c, ArrayList<LifecycleInfo> entries) {
            super(c, 0, entries);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LifecycleInfo lifecycleInfo = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.lifecycle, parent,
                        false);
            }
            TextView methodName = (TextView)convertView.findViewById(R.id.methodName);
            methodName.setText(lifecycleInfo.getMethodName());
            TextView time = (TextView)convertView.findViewById(R.id.time);


            return convertView;
        }
    }
}
