package com.example.lib_security_demo;

import com.example.lib_security_demo.ui.EncryptContainer;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

public class MyClass {
    /**
     * 界面对象
     */
    private JFrame mSurface;

    private MyClass (JFrame mainFrame){
        mSurface = mainFrame;
    }
    public void showDialog (String message, String title) {
        JOptionPane.showMessageDialog(mSurface, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
    public static void main(String[] args) {
        JFrame mainFrame = new JFrame();
        MyClass myClass = new MyClass(mainFrame);
        mainFrame.setTitle("加解密面板");
        mainFrame.setSize(450, 350);
        mainFrame.setLocationRelativeTo(null);
        // 创建选项卡面板
        JTabbedPane tabPane = new JTabbedPane();
        Container tabEncrypt = new EncryptContainer(myClass);
        tabPane.add(tabEncrypt, "文件加密");
        mainFrame.setContentPane(tabPane);
        //显示窗口
        mainFrame.setVisible(true);
    }
}