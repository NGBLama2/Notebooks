package com.ngbtm.ui;

import javax.swing.*;

public class LoginFrame extends JFrame{
    // 登录界面
    public LoginFrame(){
        // 设置窗体
        this.setSize(488,430);
        this.setVisible(true);
        // 设置窗体标题
        this.setTitle("拼图-登录");
        // 设置窗体始终置顶
        this.setAlwaysOnTop(true);
        // 设置窗体始终剧中
        this.setLocationRelativeTo(null);
        // 设置窗体关闭模式为关闭一个窗口则关闭程序
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}