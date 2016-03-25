package com.hou.ts.dontstarve;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import BmobTable.allRole;

/**
 * Created by Administrator on 2016/3/25.
 */
public class roleShow extends Activity {

    ImageView roleImg;
    TextView roleName, HP, SAN, HUN, DMG, hunNeed, roleExp, special, roleInfo;
    allRole role;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.role_show);
        Intent intent = getIntent();
        role = (allRole) intent.getSerializableExtra("role");
        initView();
        initData();
    }

    private void initView() {
        roleImg = (ImageView) findViewById(R.id.role_img);
        roleName = (TextView) findViewById(R.id.role_name);
        HP = (TextView) findViewById(R.id.role_hp);
        SAN = (TextView) findViewById(R.id.role_san);
        HUN = (TextView) findViewById(R.id.role_hun);
        DMG = (TextView) findViewById(R.id.role_dmg);
        hunNeed = (TextView) findViewById(R.id.role_hunNeed);
        roleExp = (TextView) findViewById(R.id.expNeed);
        special = (TextView) findViewById(R.id.special);
        roleInfo = (TextView) findViewById(R.id.info);
    }

    private void initData() {
        role.getRoleImg().loadImage(this,roleImg);
        roleName.setText(role.getRoleName());
        HP.setText(role.getHP());
        SAN.setText(role.getSAN());
        HUN.setText(role.getHUN());
        DMG.setText(role.getDMG());
        hunNeed.setText(role.getHunNeed());
        roleExp.setText(role.getRoleExp());
        special.setText(role.getSpecial());
        roleInfo.setText(role.getRoleInfo());
    }
}
