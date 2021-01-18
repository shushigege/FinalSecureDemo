package com.example.secure_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.secure_demo.bmob.MD5;
import com.example.secure_demo.bmob.RSA;
import com.example.secure_demo.bmob.ThreeDE;
import com.example.secure_demo.util.Des3Util;
import com.example.secure_demo.util.MD5Util;
import com.example.secure_demo.util.RSAUtil;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class findActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tv_find;
    private String des_name,des_id,des_address;
    private Button btn_find;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        tv_find = findViewById(R.id.tv_findall);
        btn_find=findViewById(R.id.btn_findall);
        btn_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查询最新注册的用户的相关信息
                SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
                String nameid = pref.getString("nameid", "");
                String id = pref.getString("id", "");
                String addressid = pref.getString("addressid", "");
                //开始从bmob查询name
                BmobQuery<ThreeDE> bmobQuery = new BmobQuery<>();
                bmobQuery.addWhereEqualTo("objectId", nameid);
                bmobQuery.findObjects(new FindListener<ThreeDE>() {
                    @Override
                    public void done(List<ThreeDE> list, BmobException e) {
                        if (e == null) {
                            //Toast.makeText(getApplication(), "查询成功:" + list.size(), Toast.LENGTH_SHORT).show();
                            des_name=list.get(0).get3DEafter();
                            System.out.println("查询结果1："+list.get(0).get3DEafter()+"  list:"+list );
                            System.out.println("查询结果2："+des_name);
                        } else {
                            Toast.makeText(getApplication(), "查询失败" + e, Toast.LENGTH_SHORT).show();
                            System.out.println("查询失败："+e );
                        }
                    }

                });
                //开始查询身份证
                BmobQuery<RSA> bmobQuery2 = new BmobQuery<>();
                bmobQuery2.addWhereEqualTo("objectId", id);
                bmobQuery2.findObjects(new FindListener<RSA>() {
                    @Override
                    public void done(List<RSA> list, BmobException e) {
                        if (e == null) {
                            //Toast.makeText(getApplication(), "查询成功:" + list.size(), Toast.LENGTH_SHORT).show();
                            des_id=list.get(0).getRsabefore();
                            System.out.println("查询结果1："+list.get(0).getRsaafter()+"  list:"+list );
                        } else {
                            Toast.makeText(getApplication(), "查询失败" + e, Toast.LENGTH_SHORT).show();
                            System.out.println("查询失败："+e );
                        }
                    }

                });
                //开始查住址
                BmobQuery<MD5> bmobQuery3 = new BmobQuery<>();
                bmobQuery3.addWhereEqualTo("objectId", addressid);
                bmobQuery3.findObjects(new FindListener<MD5>() {
                    @Override
                    public void done(List<MD5> list, BmobException e) {
                        if (e == null) {
                            Toast.makeText(getApplication(), "查询成功:" + list.size(), Toast.LENGTH_SHORT).show();
                            des_address=list.get(0).getMd5after();
                            System.out.println("查询结果1："+list.get(0).getMd5after()+"  list:"+list );
                            tv_find.setText("姓名:" + "  "+ Des3Util.decrypt("a",des_name)+"\n"+"身份证号码："+ des_id+"\n"+"住址："+ MD5Util.convertMD5(des_address));
                        } else {
                            //Toast.makeText(getApplication(), "查询失败" + e, Toast.LENGTH_SHORT).show();
                            System.out.println("查询失败："+e );
                        }
                    }

                });
                System.out.println("查询结果3："+des_name);


               // tv_find.setText(nameid + "  "+ Des3Util.decrypt("a",des_name));
            }
        });
    }
    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
    }
}