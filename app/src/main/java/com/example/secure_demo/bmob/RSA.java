package com.example.secure_demo.bmob;

import cn.bmob.v3.BmobObject;

public class RSA extends BmobObject {
    private String rsabefore;//要加密的字符串
    private String rsaafter;//加密完的字符串

    public String getRsabefore(){
        return rsabefore;
    }

    public void  setRsabefore(String rsabefore){
        this.rsabefore = rsabefore;
    }

    public String getRsaafter(){
        return rsaafter;
    }

    public void setRsaafter(String rsaafter){
        this.rsaafter = rsaafter;
    }
}
