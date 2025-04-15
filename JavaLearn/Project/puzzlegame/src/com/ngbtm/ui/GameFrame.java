package com.ngbtm.ui;

import javax.swing.*;
import java.util.Random;

public class GameFrame extends JFrame {
    int[][] data = new int[4][4];

    // 游戏主界面
    public GameFrame() {
        // 初始化窗体
        initJFrame();
        // 初始化菜单
        initJMenuBar();
        // 初始化数据
        initData();
        // 初始化图片
        initImage();
        // 设置窗体可见
        this.setVisible(true);
    }

    private void initData() {
        int[] tempArr = new int[16];

        for (int i = 0; i < 16; i++) {
            tempArr[i] = i;
        }

        Random rd = new Random();
        // 打乱数组
        for (int i = 0; i < tempArr.length; i++) {
            int index = rd.nextInt(tempArr.length);
            int temp = tempArr[i];
            tempArr[i] = tempArr[index];
            tempArr[index] = temp;
        }
        // 将一维数组变成二维数组
        for (int i = 0; i < 16; i++) {
            data[i / 4][i % 4] = tempArr[i];
        }
    }

    private void initJFrame() {
        // 设置窗体
        this.setSize(603, 680);
        // 设置窗体标题
        this.setTitle("拼图-游戏");
        // 设置窗体始终置顶
        this.setAlwaysOnTop(true);
        // 设置窗体始终剧中
        this.setLocationRelativeTo(null);
        // 取消居中显示
        this.setLayout(null);
        // 设置窗体关闭模式为关闭一个窗口则关闭程序
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initJMenuBar() {
        //创建JMenuBar对象
        JMenuBar jmb = new JMenuBar();
        // 创建JMenu对象
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于我们");

        // 创建JMenuItem对象
        // - 功能子菜单
        JMenuItem replayItem = new JMenuItem("重新开始");
        JMenuItem reLoginItem = new JMenuItem("重新登录");
        JMenuItem closeItem = new JMenuItem("退出游戏");

        // - 关于我们子菜单
        JMenuItem accountItem = new JMenuItem("关于我们");

        // 将JMenuItem加入JMenu
        // - 功能子菜单
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);

        // - 关于我们子菜单
        aboutJMenu.add(accountItem);

        // 将JMenu加入JMenuBar
        jmb.add(functionJMenu);
        jmb.add(aboutJMenu);

        // 及那个JMenuBar加入窗体中
        this.setJMenuBar(jmb);
    }

    // 初始化图片
    private void initImage() {
        // 加载图片
        for (int i = 0; i < 16; i++) {
            int picNum = data[i/4][i%4];
            String picPath = "D:\\Work\\NGBTeam\\Arsenal\\JavaLearn\\Project\\puzzlegame\\src\\image\\animal\\animal1\\" + picNum + ".jpg";
            ImageIcon icon = new ImageIcon(picPath);
            JLabel jLabel = new JLabel(icon);
            jLabel.setBounds(105 * (i % 4), 105 * (i / 4), 105, 105);
            this.getContentPane().add(jLabel);
        }
    }
}