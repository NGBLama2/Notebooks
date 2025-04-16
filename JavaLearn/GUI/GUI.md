#Java/Java基础/GUI 
# JFrame
# JMenuBar
`JMnuBar`就是窗体上方的菜单栏
	- `JMenuBar`代表的是这一条栏
	- `JMenuBar`下面的一级就是JMenu，代表的是一个个菜单
	- `JMenu`下面的一级就是JMenuItem，代表的是一个个功能
![image.png](https://pic.hibugs.net/NGBTEAM/20250406231023889.png)
## 菜单的制作过程
1. 先创建`JMenuBar`
2. 再创建`JMenu`
3. 再创建`JMenuItem`
4. 把`JMenuItem`放到`JMenu`里面
5. 再把`JMenu`放到`JMenuBar`里面
6. 最后再把JMenuBar添加到整个JFrame界面中
# JLabel管理区域
JLabel可以想象成是一个管理者，他可以管理：图片、文字
	- 类似于div
## ImageIcon
ImageIcon对象是用来存放图片的，一般创建ImageIcon对象来装载图片
### 创建图片ImageIcon
```
// 添加图片的流程：
1. 创建ImageIcon对象，将图片地址给到他
2. 创建JLabel，将ImageIcon对象用JLabel对象包裹
3. 将JLabel添加到JFrame窗体中

ImageIcon icon = new ImageIcon("文件地址");
JLabel jLabel = new JLabel(icon);
this.add(jLabel)
```
#### IDEA获取文件地址
右键，在菜单中选择`Copy Path`
![image.png](https://pic.hibugs.net/NGBTEAM/20250411231116173.png)

选择Absolute Path（绝对路径）
![image.png](https://pic.hibugs.net/NGBTEAM/20250411231109741.png)
### 指定图片位置
JLabel中有一个方法——`setBounds()`用来设置JLabel在窗体中的位置，但是如果只是使用该方法，图片依然会居中显示在窗体中（窗体的默认显示方式是居中显示）

窗体一共分为三块，最下面的就是隐藏的容器，他是用来装载所有组件的，并且他提供了`getContentPane()`来获取他
![image.png](https://pic.hibugs.net/NGBTEAM/20250411231347407.png)

如果我们希望我们设置的JLabel的位置生效，首先我们需要使用`setLayout(null)`将容器中默认的居中显示改为null，然后再获取隐藏容器，代码如下：在初始化窗体的时候将窗体的布局设置为null
![](https://pic.hibugs.net/NGBTEAM/20250412003410688.png)

再去到初始化图片的方法中，使用`setBounds(x,y,width,height)`指定坐标位置以及长宽，再使用`getContentPane()`获取隐藏窗体，再将JLabel加入隐藏窗体
```
// initFrame()
jFrame.setLayout(null); // 取消默认居中的布局
// initImage()
jLabel.setBounds(x,y,width,height); // 设置坐标、长宽
this.getContentPane().add(jLabel); // 获取隐藏窗体，并且将JLabel加入隐藏窗体
```
#### 如何判断坐标
是以左上角的点为坐标点，xy轴的方向如下：
![image.png](https://pic.hibugs.net/NGBTEAM/20250412004803324.png)
## 细节
### 细节一
先加载的图片在上方，后加载的图片在下方
### 细节二
相对路径是以项目文件夹为起点，而不是当前.java文件，如下图，我要读取这个1.jpg的图片，应该是一puzzlegame为起点，puzzlegame下的image下的animal1下的1.jpg —— `image/animal/animal1/1.jpg`
![image.png](https://pic.hibugs.net/NGBTEAM/20250416103714307.png)

# 事件
事件源：按钮、图片、窗体...
	- 被操作的组件

事件：某些操作
	- 鼠标单击、鼠标划入...

绑定监听：当事件源上发生了事件，则执行某段代码
	 - 常见的监听：
		 - KeyListener：键盘监听
		 - MouseListener：鼠标监听
		 - ActionListener：动作监听
## ActionListener
### 事件监听的三种方式
#### 方式一（创建一个新的类）
创建一个类，实现监听的接口，重写`actionPerformed方法`
![image.png](https://pic.hibugs.net/NGBTEAM/20250415232019472.png)

在代码中使用`组件.addActionListener(new MyActionListener)`，如下图
![image.png](https://pic.hibugs.net/NGBTEAM/20250415232135016.png)

#### 方式二（使用匿名类）
往往每个按钮对应的功能都是不一样的，如果为了只会用到一次的功能去编写一个类，久而久之代码文件会变得非常多（冗余），所以可以使用匿名类（正好是只用一次的代码）
![image.png](https://pic.hibugs.net/NGBTEAM/20250415232321813.png)
#### 方式三
直接在该类中实现`ActionListener`
![image.png](https://pic.hibugs.net/NGBTEAM/20250415232443052.png)

再去重写方法
![image.png](https://pic.hibugs.net/NGBTEAM/20250415232513636.png)

调用的时候传入this，让其调用本类方法
![image.png](https://pic.hibugs.net/NGBTEAM/20250415232617812.png)

## MouseListener
鼠标监听中将鼠标分为了四种状态：
	- 划入（mouseEntered）
	- 按下（mousePressed）
	- 松开（mouseReleased）
	- 划出（mouseExited）
	- 单击（mouseClicked）
其中，按下和松开又被合并称为单击
### 鼠标单击事件的监听
	 1. 动作监听
	 2. 鼠标监听中的单击事件
	 3. 鼠标监听中的松开事件
以上三种方式都能监听鼠标单击事件
### 事件监听的三种方式
同ActionListener中的三种方式
## KeyListener
键盘监听：
	- 按下（keyPressed）
	- 松开（keyReleased）
	- 键入（keyTyped）（用的少）
#### 细节1
如果我们按下一个按键没有松开，那么会重复的去调用keyPressed方法
### 细节2
使用`getKeyCode()`方法可以得到具体是哪个按键，每一个按键都有一个编号与之对应