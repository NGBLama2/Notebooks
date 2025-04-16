package com.ngbtm.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameFrame extends JFrame implements KeyListener, ActionListener {
    int[][] data = new int[4][4];
    int x = 0; // 0.jpg的行
    int y = 0; // 0.jpg的列
    final String PIC_ROOT_PATH = "image\\";
    String catelogPath = "animal\\animal1\\";
    int[][] win = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
    };
    int step = 0;
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
            if (tempArr[i] == 0) {
                x = i / 4;
                y = i % 4;
            }
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
        // 设置键盘监听事件
        this.addKeyListener(this);
    }

    private void initJMenuBar() {
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

        // 绑定条目
        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);

        // 及那个JMenuBar加入窗体中
        this.setJMenuBar(jmb);
    }

    // 初始化图片
    private void initImage() {
        // 清空原有的图片
        this.getContentPane().removeAll();
        // 判断是否胜利
        if (isVictory()) {
            JLabel winLabel = new JLabel(new ImageIcon(PIC_ROOT_PATH + "win.png"));
            winLabel.setBounds(203, 283, 197, 73);
            this.getContentPane().add(winLabel);
        }
        // 显示步数
        JLabel stepCount = new JLabel("步数："+step);
        stepCount.setBounds(50,30,100,20);
        this.getContentPane().add(stepCount);
        // 加载图片
        for (int i = 0; i < 16; i++) {
            int picNum = data[i / 4][i % 4];
            String picPath = PIC_ROOT_PATH + catelogPath + picNum + ".jpg";
            ImageIcon icon = new ImageIcon(picPath);
            JLabel jLabel = new JLabel(icon);
            jLabel.setBounds(105 * (i % 4) + 83, 105 * (i / 4) + 134, 105, 105);
            jLabel.setBorder(new BevelBorder(BevelBorder.RAISED));
            this.getContentPane().add(jLabel);
        }

        ImageIcon bg = new ImageIcon(PIC_ROOT_PATH + "background.png");
        JLabel background = new JLabel(bg);
        background.setBounds(40, 40, 508, 560);
        this.getContentPane().add(background);
        // 刷新页面
        this.getContentPane().repaint();
    }

    //    直接xy直接自增自减即可
//    private void setZeroPosition(int x,int y){
//        this.x = x;
//        this.y = y;
//    }
    public boolean isVictory() {
        for (int i = 0; i < 16; i++) {
            if (data[i / 4][i % 4] != win[i / 4][i % 4]) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(isVictory()) return;
        int keyCode = e.getKeyCode();
        this.getContentPane().removeAll();
        if (keyCode == 65) {
            JLabel all = new JLabel(new ImageIcon(PIC_ROOT_PATH + catelogPath + "all.jpg"));
            all.setBounds(83, 134, 420, 420);
            this.getContentPane().add(all);
            JLabel background = new JLabel(new ImageIcon(PIC_ROOT_PATH + "background.png"));
            background.setBounds(40, 40, 508, 560);
            this.getContentPane().add(background);
            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (isVictory()) return;
        int keyCode = e.getKeyCode();
        if (keyCode == 37) { // 左
            if (y != 3) {
                data[x][y] = data[x][y + 1]; // 将0.jpg左边的图片移动到0.jpg的位置上
                data[x][y + 1] = 0; // 0.jpg左移
//                setZeroPosition(x,y+1);
                y++;
                step++;
                initImage();
            }
        } else if (keyCode == 38) { // 上
            if (x != 3) {
                data[x][y] = data[x + 1][y]; // 将0.jpg下面的图片上移
                data[x + 1][y] = 0;
//                setZeroPosition(x+1,y);
                x++;
                step++;
                initImage();
            }
        } else if (keyCode == 39) { // 右
            if (y != 0) {
                data[x][y] = data[x][y - 1]; // 将0.jpg左边的图片右移
                data[x][y - 1] = 0;
//                setZeroPosition(x,y-1);
                y--;
                step++;
                initImage();
            }
        } else if (keyCode == 40) { // 下
            if (x != 0) {
                data[x][y] = data[x - 1][y]; // 将0.jpg上面的图片下移
                data[x - 1][y] = 0;
//                setZeroPosition(x-1,y);
                x--;
                step++;
                initImage();
            }
        } else if (keyCode == 65) {
            initImage();
        } else if (keyCode == 87) {
            for (int i = 0; i < 16; i++) {
                data[i / 4][i % 4] = i + 1;
            }
            data[3][3] = 0;
            initImage();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if(obj == replayItem){
            // 步数清零
            step = 0;
            // 打乱data
            initData();
            // 加载图片
            initImage();
        } else if (obj == closeItem) {
            System.exit(0);
        } else if (obj == accountItem) {
            JLabel qrCode = new JLabel(new ImageIcon(PIC_ROOT_PATH+"about.png"));
            JDialog about = new JDialog();
            
        }
    }
}