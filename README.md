# -Painting-Tool
A Painting Tool made by myself (By Java)   自写Java绘图软件
## 项目定位及其功能：
    一个基于Java的绘图小软件，可以实现基本的绘图功能：
    1. 三种不同粗细的画笔的选择和使用
    2. 橡皮的擦除功能
    3. 整张画布的重置功能
    4. 背景颜色和画笔颜色的选择和改变
    5. 基本图形的绘制
    6. 保存所绘制图片到本地
    7. 添加水印功能和简笔画模板功能
## 项目应用：
    可以用于平时上课时来书写临时的笔记或是生活中临时的提示等，或者直接使用来进行计算、画图的草稿，或是单纯的作图、画画。
## 开发环境介绍：
    本项目程序软件使用的是Java语言，配合Eclipse开发环境，JDK使用的是Java SE-11.0.2版本
    相应的下载连接如下：
    JDK: <https://www.oracle.com/technetwork/java/javase/downloads/index.html>
    Eclipse: <https://www.eclipse.org/downloads/packages/>
    环境配置教程：<https://www.cnblogs.com/xch-yang/p/7629351.html>
## 系统业务流程：
* 用户
    * 设置画笔粗细
    * 设置画笔颜色
    * 设置背景颜色
    * 展开简笔画窗口
    * 设置水印内容
    * 退出
        * 用鼠标画线
        * 绘制图形
        * 清除图案
        * 使用橡皮
        * 保存照片
## 软件主要代码展示及说明：
### 1. 所有引用
```Java
        package Draw;

        import javax.swing.JFrame;
        import java.awt.Color;
        import java.awt.Graphics;
        import java.awt.Graphics2D;
        import java.awt.image.BufferedImage;
        import java.awt.event.MouseAdapter;
        import java.awt.event.MouseEvent;
        import java.awt.event.MouseMotionAdapter;
        import java.awt.BorderLayout;
        import javax.swing.ButtonGroup;
        import javax.swing.JButton;
        import javax.swing.JColorChooser;
        import javax.swing.JToggleButton;
        import javax.swing.JToolBar;
        import java.awt.BasicStroke;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.awt.geom.Ellipse2D;
        import java.awt.geom.Rectangle2D;
        import com.mr.util.FrameGetShape;
        import com.mr.util.ShapeWindow;
        import com.mr.util.Shapes;
        import com.mr.util.DrawImageUtil;
        import javax.swing.JMenu;
        import javax.swing.JMenuBar;
        import javax.swing.JMenuItem;
        import java.awt.AlphaComposite;
        import java.awt.Font;
        import javax.swing.JOptionPane;
        import java.awt.Image;
        import java.awt.Point;
        import java.awt.Toolkit;
        import java.awt.Cursor;
        import javax.swing.ImageIcon; 
```
### 2. 变量
```Java
        BufferedImage image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_BGR);
	Graphics gs = image.getGraphics();
	Graphics2D g = (Graphics2D) gs;
	drawPictureCanvas canvas = new drawPictureCanvas();
	Color foreColor = Color.BLACK;
	Color backgroundColor = Color.WHITE;
	int x = -1;
	int y = -1;
	boolean rubber = false;
	private JToolBar toolBar;
	private JButton eraserButton;
	private JToggleButton strokeButton1;
	private JToggleButton strokeButton2;
	private JToggleButton strokeButton3;
	private JButton backgroundButton;
	private JButton foregroundButton;
	private JButton clearButton;
	private JButton saveButton;
	private JButton shapeButton;
	private JMenuItem strokeMenuItem1;
	private JMenuItem strokeMenuItem2;
	private JMenuItem strokeMenuItem3;
	private JMenuItem clearMenuItem;
	private JMenuItem foregroundMenuItem;
	private JMenuItem backgroundMenuItem;
	private JMenuItem eraserMenuItem;
	private JMenuItem exitMenuItem;
	private JMenuItem saveMenuItem;
	private JMenuItem shuiyinMenuItem;
	private String shuiyin = "";
	private pictureWindow picWindow;
	private JButton showPicButton;
	boolean drawShape = false;
	Shapes shape;
```
#### 2.1 Button部分
```Java
    	private JButton eraserButton;
	private JToggleButton strokeButton1;
	private JToggleButton strokeButton2;
	private JToggleButton strokeButton3;
	private JButton backgroundButton;
	private JButton foregroundButton;
	private JButton clearButton;
	private JButton saveButton;
	private JButton shapeButton;
    	private JButton showPicButton;
```
#### 2.2 MenuItem部分
```Java
    	private JMenuItem strokeMenuItem1;
	private JMenuItem strokeMenuItem2;
	private JMenuItem strokeMenuItem3;
	private JMenuItem clearMenuItem;
	private JMenuItem foregroundMenuItem;
	private JMenuItem backgroundMenuItem;
	private JMenuItem eraserMenuItem;
	private JMenuItem exitMenuItem;
	private JMenuItem saveMenuItem;
	private JMenuItem shuiyinMenuItem;
```
#### 2.3 其他变量部分
```Java
    	BufferedImage image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_BGR);
	Graphics gs = image.getGraphics();
	Graphics2D g = (Graphics2D) gs;
	drawPictureCanvas canvas = new drawPictureCanvas();
	Color foreColor = Color.BLACK;
	Color backgroundColor = Color.WHITE;
	int x = -1;
	int y = -1;
	boolean rubber = false;
	private JToolBar toolBar;
	private String shuiyin = "";
	private pictureWindow picWindow;
	boolean drawShape = false;
	Shapes shape;
```
