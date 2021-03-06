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
	

![No.1](https://github.com/Tonylycxx/-Painting-Tool/blob/master/Img/1.png)

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
### 3. 逻辑部分
#### 3.1 橡皮

	定义布尔变量 rubber 并初始化为"false"。
	
```Java
	boolean rubber = false;
```

	为橡皮的按钮 "eraserButton" 添加监听器，并且通过布尔值 rubber 的取值来改变软件中相应按钮的功能和标志（包括按钮颜色）。

```Java
		eraserButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent arg0) {
				if (rubber) {
					eraserButton.setToolTipText("Eraser");
					eraserButton.setIcon(new ImageIcon("src/Draw/icon/橡皮.png"));
					eraserMenuItem.setText("Eraser");
					g.setColor(foreColor);
					rubber = false;
				} else {
					eraserMenuItem.setText("Draw");
					eraserButton.setToolTipText("Draw");
					eraserButton.setIcon(new ImageIcon("src/Draw/icon/画笔.png"));
					g.setColor(backgroundColor);
					rubber = true;
				} // the end of else
			} // the end of actionPerformed()
		}); // the end of eraserButton.addActionListener()
```
效果如下：  

![No.2](https://github.com/Tonylycxx/-Painting-Tool/blob/master/Img/2.png) ![No.3](https://github.com/Tonylycxx/-Painting-Tool/blob/master/Img/3.png)

#### 3.2 水印
	定义字符串变量 "shuiyin" 并初始化为空字符串，这是为了在后面的创建水印函数中避免并没有输入水印却按下 "确定" 按钮的情况。
```Java
	private String shuiyin = "";
```
	通过如下函数调出水印设置窗口，并将总窗口名称处加上水印的内容。
	
```Java
	shuiyinMenuItem.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			shuiyin = JOptionPane.showInputDialog(drawPictureFrame.this, "你想添加什么水印？");
			if (null == shuiyin) {
				shuiyin = "";
			} else {
				setTitle("画图程序(水印内容: [ " + shuiyin + " ] )");
			} // the end of else
		}// the end of actionPerformed()
	});// the end of shuiyinMenuItem.addActionListener()
```
	
	    首先判断输入的字符串是否为空，如若不是空，那么进行函数中的操作，设置水印字体为楷体，大小以及旋转角度，颜色为灰色并设置透明度。   
	这里存在问题，即旋转时，画布也会随之转动。故在函数最后再将画布旋转回来（即所绘制的画面）并将透明度选择为不透明，背景颜色改好。

```Java
	private void addWatermark() {
		if (!"".equals(shuiyin.trim())) {
			g.rotate(Math.toRadians(-30));
			Font font = new Font("楷体", Font.BOLD, 72);
			g.setFont(font);
			g.setColor(Color.GRAY);
			AlphaComposite alpha = AlphaComposite.SrcOver.derive(0.4f);
			g.setComposite(alpha);
			g.drawString(shuiyin, 150, 500);
			canvas.repaint();
			g.rotate(Math.toRadians(30));
			alpha = AlphaComposite.SrcOver.derive(1f);
			g.setComposite(alpha);
			g.setColor(foreColor);
		} // the end of if
	}// the end of addWatermark()
```
效果如下：   
![No.4](https://github.com/Tonylycxx/-Painting-Tool/blob/master/Img/4.png)
![No.5](https://github.com/Tonylycxx/-Painting-Tool/blob/master/Img/5.png)   

#### 3.3 绘制基础图形
	定义布尔量 "drawShape" 并初始化为 "false" 。
````Java
	boolean drawShape = false;
````
	如果布尔量 "drawShape" 为 "true" ，则分类讨论，并选出相应的绘图坐标和图形的大小。  
	本软件默认绘制图形时，鼠标点击处为要绘制图形的几何中心处。

```Java
	public void mousePressed(MouseEvent e) {
		if (drawShape) {
			switch (shape.getType()) {
			case Shapes.YUAN:
				int yuanX = e.getX() - shape.getWidth() / 2;
				int yuanY = e.getY() - shape.getHeigth() / 2;
				Ellipse2D yuan = new Ellipse2D.Double(yuanX, yuanY, shape.getWidth(), shape.getHeigth());
				g.draw(yuan);
				break;
			case Shapes.FANG:
				int fangX = e.getX() - shape.getWidth() / 2;
				int fangY = e.getY() - shape.getHeigth() / 2;
				Rectangle2D fang = new Rectangle2D.Double(fangX, fangY, shape.getWidth(), shape.getHeigth());
				g.draw(fang);
				break;
			}// the end of switch
			canvas.repaint();
			drawShape = false;
		}
	}
```
效果如下：   

![No.7](https://github.com/Tonylycxx/-Painting-Tool/blob/master/Img/7.png)
![No.8](https://github.com/Tonylycxx/-Painting-Tool/blob/master/Img/8.png)   

#### 3.4 简笔画窗口
	通过使用新建的类 "pictureWindow.java" 来设置子窗口的大小、位置和其中图片的显示。
	代码如下：
```Java
	package Draw;

	import java.awt.BorderLayout;
	import java.awt.Container;
	import java.awt.FlowLayout;
	import java.awt.Image;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.io.File;
	import javax.swing.ImageIcon;
	import javax.swing.JButton;
	import javax.swing.JPanel;
	import javax.swing.JWindow;
	import com.mr.util.BackgroundPanel;
	
	public class pictureWindow extends JWindow{
		
		private JButton changeButton;
		private JButton hiddenButton;
		private BackgroundPanel centerPanel;
		File list[];
		int index;
		drawPictureFrame frame;
		
		public pictureWindow(drawPictureFrame frame) {
			this.frame = frame;
			setSize(400, 460);
			init();
			addListener();
		}//the end of pictureWindow()
		
		private void init() {
			Container c = getContentPane();
			File dir = new File("src/Draw/picture");
			list = dir.listFiles();
			centerPanel = new BackgroundPanel(getListImage());
			c.add(centerPanel, BorderLayout.CENTER);
			FlowLayout flow = new FlowLayout(FlowLayout.RIGHT);
			flow.setHgap(30);
			JPanel southPanel = new JPanel();
			southPanel.setLayout(flow);
			changeButton = new JButton("Next mode");
			southPanel.add(changeButton);
			hiddenButton = new JButton("Hide");
			southPanel.add(hiddenButton);
			c.add(southPanel, BorderLayout.SOUTH);
		}//the end of init()
		
		private void addListener() {
			hiddenButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					frame.initShowPicButton();
				}//the end of actionPerformed()
			});//the end of hiddenButton.addActionListener()
			changeButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					centerPanel.setImage(getListImage());
				}//the end of actionPerformed()
			});//the end of changeButton.addActionListener()
		}//the end of addListener()
		
		private Image getListImage() {
			String imgPath = list[index].getAbsolutePath();
			ImageIcon image = new ImageIcon(imgPath);
			index++;
			if(index >= list.length) {
				index = 0;
			}//the end of if
			return image.getImage();
		}//the end of getListImage()
	
	}//the end of PictureWindow()

	
```
效果如下：   

![No.6](https://github.com/Tonylycxx/-Painting-Tool/blob/master/Img/6.png)   


## 作者
	刘禹辰 18373276   
	联系方式：
		1. 手机：13693146399
		2. 邮箱：2290549567@qq.com
