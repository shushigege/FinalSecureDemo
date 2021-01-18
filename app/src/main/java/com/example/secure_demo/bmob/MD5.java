package com.example.secure_demo.bmob;

import cn.bmob.v3.BmobObject;

public class MD5 extends BmobObject {
    private String md5before;//要加密的字符串
    private String md5after;//加密完的字符串

    public String getMd5before(){
        return md5before;
    }

    public void  setMd5before(String md5before){
        this.md5before = md5before;
    }

    public String getMd5after(){
        return md5after;
    }

    public void setMd5after(String md5after){
        this.md5after = md5after;
    }
}
