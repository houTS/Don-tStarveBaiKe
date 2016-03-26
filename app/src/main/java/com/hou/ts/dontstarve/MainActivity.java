package com.hou.ts.dontstarve;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import BmobTable.BmobKey;
import cn.bmob.v3.Bmob;
import fragment.role_fragment;
import fragment.test_fragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bmob.initialize(getApplicationContext(), BmobKey.getApp_ID());
        setImageLoader();

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setDefaultFragment();
    }

    /*
    设置imageloader的全局配置，第三方开源库，必须设置,这里是默认，具体的imageview会再次设置
     */
    private void setImageLoader() {
        ImageLoaderConfiguration configuration=ImageLoaderConfiguration.createDefault(this);
        ImageLoader.getInstance().init(configuration);
    }

    private void setDefaultFragment() {
        getFragmentManager().beginTransaction().replace(R.id.mfra, new test_fragment()).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //设置toolbar上右彻点击显示的悬浮按钮菜单
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camara) {
            showToast("角色");
            getFragmentManager().beginTransaction().replace(R.id.mfra, new role_fragment()).commit();
        } else if (id == R.id.nav_gallery) {
            showToast("待定");
            getFragmentManager().beginTransaction().replace(R.id.mfra, new test_fragment()).commit();
        } else if (id == R.id.nav_slideshow) {
            showToast("待定2");
            getFragmentManager().beginTransaction().replace(R.id.mfra, new role_fragment()).commit();
        } else if (id == R.id.nav_manage) {
            showToast("待定3");
            getFragmentManager().beginTransaction().replace(R.id.mfra, new test_fragment()).commit();
        } else if (id == R.id.nav_share) {
            showToast("设置");
            getFragmentManager().beginTransaction().replace(R.id.mfra, new role_fragment()).commit();
        } else if (id == R.id.nav_send) {
            showToast("其它");
            getFragmentManager().beginTransaction().replace(R.id.mfra, new test_fragment()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void showToast(String mess) {
        Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
    }


    private long firstTime = 0;

    /* 思路1：记录上次点击的时间，与本次点击的时间比较，
     * 当两次时间间隔小于一定值时，退出，
     * 否则提示“再按一次退出程序”，同时更新上次点击时间
     * */
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        Log.v("this", "int" + keyCode + "  KeyEvent" + event);
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                long secondTime = System.currentTimeMillis();  //生成当前的毫秒
                if (secondTime - firstTime > 2000) {  //如果两次按键时间间隔大于2秒，则不退出
                    Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                    firstTime = secondTime;  //更新firstTime
                    return true;
                } else {   //两次按键小于2秒时，退出应用
                    System.exit(0);
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }

//    private int mBackKeyPressedTimes = 0;
//    /* 思路2：开线程延时处理  有点问题
//     * */
//    @Override
//    public void onBackPressed() {
//        // TODO Auto-generated method stub
//        if (mBackKeyPressedTimes == 0) {
//            Toast.makeText(this, "再按一次退出程序 ", Toast.LENGTH_SHORT).show();
//            mBackKeyPressedTimes = 1;
//            new Thread() {
//                @Override
//                public void run() {
//                    try {
//                        Thread.sleep(2000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    } finally {
//                        mBackKeyPressedTimes = 0;
//                    }
//                }
//            }.start();
//            return;
//        } else {
//            MainActivity.this.finish();
//        }
//        super.onBackPressed();
//    }

}
