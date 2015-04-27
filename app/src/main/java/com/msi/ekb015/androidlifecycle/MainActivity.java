package com.msi.ekb015.androidlifecycle;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class MainActivity extends ActionBarActivity {
    public static String LC_KEY = "com.msi.ekb015.LC_KEY";
    public static String TM_KEY = "com.msi.ekb015.TM_KEY";
    private ArrayList<LifecycleInfo> mLifecycleInfo;
    private LifecycleAdapter mAdapter;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> times = new ArrayList<>();
        for (LifecycleInfo info : mLifecycleInfo) {
            names.add(info.getMethodName());
            times.add(info.getTimeStamp());
        }
        outState.putStringArrayList(LC_KEY, names);
        outState.putStringArrayList(TM_KEY, times);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        ArrayList<String> names = savedInstanceState.getStringArrayList(LC_KEY);
        ArrayList<String> times = savedInstanceState.getStringArrayList(TM_KEY);
        if (names != null) {
            for (int i = 0; i < names.size(); i++) {
                LifecycleInfo info = new LifecycleInfo(names.get(i), times.get(i));
                mLifecycleInfo.add(info);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLifecycleInfo = new ArrayList<>();
        /*
        else {
            mLifecycleInfo = new ArrayList<>();
            ArrayList<String> names = savedInstanceState.getStringArrayList(LC_KEY);
            ArrayList<String> times = savedInstanceState.getStringArrayList(TM_KEY);
            if (names != null) {
                for (int i = 0; i < names.size(); i++) {
                    LifecycleInfo info = new LifecycleInfo(names.get(i), times.get(i));
                    mLifecycleInfo.add(info);
                }
            }
        }
        */

        //record it
        recordLifecycleChange("onCreate");

        ListView listView = (ListView)findViewById(R.id.lifecycleList);
        mAdapter = new LifecycleAdapter(this, mLifecycleInfo);
        listView.setAdapter(mAdapter);
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
        SimpleDateFormat simpleDate = new SimpleDateFormat("HH:mm:ss.SSS");
        String timeStamp = simpleDate.format(date);
        LifecycleInfo lifecycleInfo = new LifecycleInfo(methodName, timeStamp);
        mLifecycleInfo.add(lifecycleInfo);

        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }

        //Toast.makeText(this, methodName + "() invoked", Toast.LENGTH_SHORT).show();
        Log.d(LC_KEY, methodName + "() invoked");
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
            time.setText(lifecycleInfo.getTimeStamp());

            return convertView;
        }
    }
}
