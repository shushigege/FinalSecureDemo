package com.example.secure_demo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.secure_demo.bmob.MD5;
import com.example.secure_demo.bmob.RSA;
import com.example.secure_demo.bmob.ThreeDE;
import com.example.secure_demo.util.Des3Util;
import com.example.secure_demo.util.MD5Util;
import com.example.secure_demo.util.RSAUtil;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends AppCompatActivity {

    private EditText et_id;
    private EditText et_address;
    private EditText et_name;
    private Button btn_register;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        et_id = (EditText) findViewById(R.id.et_id);
        et_name = (EditText) findViewById(R.id.et_name);
        et_address = (EditText) findViewById(R.id.et_address);
        btn_register=findViewById(R.id.btn_register);
        Bmob.initialize(this,"a09b8b6aaf8db35c01035bce3f9012af");
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_name.getText().toString();
                String id= et_id.getText().toString();
                String address = et_address.getText().toString();
                MD5 md5 = new MD5();
                RSA rsa = new RSA();
                ThreeDE threeDE = new ThreeDE();
                SharedPreferences.Editor editor=getSharedPreferences("data",MODE_PRIVATE).edit();

                if (name == null || id == null || address == null|| name.length() <= 0|| id.length() <= 0|| address.length() <= 0)
                {
                    Toast.makeText(getApplication(), "请完善注册信息", Toast.LENGTH_LONG).show();
                }
                else{
                    //md5加密
                    String Md5_address= MD5Util.convertMD5(address);
                    //String Md5_address= MD5Util.encrypBy(address);
                    md5.setMd5before(address);
                    md5.setMd5after(Md5_address);
                    md5.save(new SaveListener<String>() {
                        @Override
                        public void done(String addressid, BmobException e) {
                            if (e == null) {
                               //Toast.makeText(getApplication(), "添加数据成功" + addressid, Toast.LENGTH_SHORT).show();
                                editor.putString("addressid",addressid);

                            } else {
                                Toast.makeText(getApplication(), "创建数据失败" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                //rsa加密
                String RSA_id = RSAUtil.encodeRSA(null, id);
                rsa.setRsabefore(id);
                rsa.setRsaafter(RSA_id);
                rsa.save(new SaveListener<String>() {
                    @Override
                    public void done(String id_id, BmobException e) {
                        if (e == null) {
                            //Toast.makeText(getApplication(), "添加数据成功" + id_id, Toast.LENGTH_SHORT).show();
                            editor.putString("id",id_id);
                        } else {
                            Toast.makeText(getApplication(), "创建数据失败" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                //3DES加密
                String key = "a";
                String ThreeDES_name = Des3Util.encrypt(key, name);
                String deThreeDES_name = Des3Util.decrypt(key, ThreeDES_name);//解密的结果
                threeDE.set3DEbefore(name);
                threeDE.set3DEafter(ThreeDES_name);
                threeDE.setSolvedString(deThreeDES_name);
                threeDE.save(new SaveListener<String>() {
                    @Override
                    public void done(String name_id, BmobException e) {
                        if (e == null) {
                            Toast.makeText(getApplication(), "添加数据成功" + name_id, Toast.LENGTH_SHORT).show();
                            editor.putString("nameid",name_id);
                            editor.apply();
                            finish();//返回到
                        } else {
                            Toast.makeText(getApplication(), "创建数据失败" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

    }
}
