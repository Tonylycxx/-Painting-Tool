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

public class drawPictureFrame extends JFrame implements FrameGetShape {

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

	public drawPictureFrame() {
		setResizable(true);
		setTitle("画图程序(水印内容: [ " + shuiyin + " ] )");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 20, 815, 672);
		init();
		addListener();
	}// the end of drawPictureFrame

	private void init() {
		g.setColor(backgroundColor);
		g.fillRect(0, 0, 800, 600);
		g.setColor(foreColor);
		canvas.setImage(image);
		getContentPane().add(canvas);

		toolBar = new JToolBar();
		getContentPane().add(toolBar, BorderLayout.NORTH);
//		showPicButton = new JButton("Show the mode");
		showPicButton = new JButton();
		showPicButton.setToolTipText("Show the mode");
		showPicButton.setIcon(new ImageIcon("src/Draw/icon/展开.png"));
		toolBar.add(showPicButton);

//		saveButton = new JButton("Save");
		saveButton = new JButton();
		saveButton.setToolTipText("Save");
		saveButton.setIcon(new ImageIcon("src/Draw/icon/保存.png"));
		toolBar.add(saveButton);
		toolBar.addSeparator(); // 添加分割线

//		strokeButton1 = new JToggleButton("Line 1");
		strokeButton1 = new JToggleButton();
		strokeButton1.setToolTipText("Show the mode");
		strokeButton1.setIcon(new ImageIcon("src/Draw/icon/1像素线条.png"));
		strokeButton1.setSelected(true);
		toolBar.add(strokeButton1);
//		strokeButton2 = new JToggleButton("Line 2");
		strokeButton2 = new JToggleButton();
		strokeButton2.setToolTipText("Show the mode");
		strokeButton2.setIcon(new ImageIcon("src/Draw/icon/2像素线条.png"));
		toolBar.add(strokeButton2);
//		strokeButton3 = new JToggleButton("Line 3");
		strokeButton3 = new JToggleButton();
		strokeButton3.setToolTipText("Show the mode");
		strokeButton3.setIcon(new ImageIcon("src/Draw/icon/4像素线条.png"));
		toolBar.add(strokeButton3);
		ButtonGroup strokeGroup = new ButtonGroup();
		strokeGroup.add(strokeButton1);
		strokeGroup.add(strokeButton2);
		strokeGroup.add(strokeButton3);
		toolBar.add(strokeButton3);
		toolBar.addSeparator();
//		backgroundButton = new JButton("Background Color");
		backgroundButton = new JButton();
		backgroundButton.setToolTipText("Show the mode");
		backgroundButton.setIcon(new ImageIcon("src/Draw/icon/背景色.png"));
		toolBar.add(backgroundButton);
//		foregroundButton = new JButton("Foreground Color");
		foregroundButton = new JButton();
		foregroundButton.setToolTipText("Show the mode");
		foregroundButton.setIcon(new ImageIcon("src/Draw/icon/前景色.png"));
		toolBar.add(foregroundButton);
		toolBar.addSeparator();
//		shapeButton = new JButton("Shapes");
		shapeButton = new JButton();
		shapeButton.setToolTipText("Show the mode");
		shapeButton.setIcon(new ImageIcon("src/Draw/icon/形状.png"));
		toolBar.add(shapeButton);
//		clearButton = new JButton("Clear");
		clearButton = new JButton();
		clearButton.setToolTipText("Show the mode");
		clearButton.setIcon(new ImageIcon("src/Draw/icon/清除.png"));
		toolBar.add(clearButton);
//		eraserButton = new JButton("Eraser");
		eraserButton = new JButton();
		eraserButton.setToolTipText("Show the mode");
		eraserButton.setIcon(new ImageIcon("src/Draw/icon/橡皮.png"));
		toolBar.add(eraserButton);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu systemMenu = new JMenu("System");
		menuBar.add(systemMenu);
		shuiyinMenuItem = new JMenuItem("Water Mark");
		systemMenu.add(shuiyinMenuItem);
		saveMenuItem = new JMenuItem("Save");
		systemMenu.add(saveMenuItem);
		systemMenu.addSeparator();
		exitMenuItem = new JMenuItem("Exit");
		systemMenu.add(exitMenuItem);
		JMenu strokeMenu = new JMenu("Line Styles");
		menuBar.add(strokeMenu);
		strokeMenuItem1 = new JMenuItem("Line 1");
		strokeMenu.add(strokeMenuItem1);
		strokeMenuItem2 = new JMenuItem("Line 2");
		strokeMenu.add(strokeMenuItem2);
		strokeMenuItem3 = new JMenuItem("Line 3");
		strokeMenu.add(strokeMenuItem3);

		JMenu colorMenu = new JMenu("Color");
		menuBar.add(colorMenu);
		foregroundMenuItem = new JMenuItem("Front Color");
		colorMenu.add(foregroundMenuItem);
		backgroundMenuItem = new JMenuItem("Background Color");
		colorMenu.add(backgroundMenuItem);

		JMenu editMenu = new JMenu("Edit");
		menuBar.add(editMenu);
		clearMenuItem = new JMenuItem("Clear");
		editMenu.add(clearMenuItem);
		eraserMenuItem = new JMenuItem("Eraser");
		editMenu.add(eraserMenuItem);

		picWindow = new pictureWindow(drawPictureFrame.this);

	}// the end of init()

	private void addListener() {
		canvas.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(final MouseEvent e) {
				if (x > 0 && y > 0) {
					if (rubber) { // if you want to rub
						g.setColor(backgroundColor);
						g.fillRect(x, y, 10, 10);
					} else { // if you want to paint
						g.drawLine(x, y, e.getX(), e.getY());
					} // the end of else
				} // the end of if
				x = e.getX();// last-time X
				y = e.getY();// last-time Y
				canvas.repaint();// refresh the canvas
			}// the end of mouseDragged()

			public void mouseMoved(final MouseEvent arg0) {
				if (rubber) {
					Toolkit kit = Toolkit.getDefaultToolkit();
					Image img = kit.createImage("src/Draw/icon/鼠标橡皮.png");
					Cursor c = kit.createCustomCursor(img, new Point(0, 0), "clear");
					setCursor(c);
				} else {
					setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
				} // the end of else
			}// the end of mouseMoved()

		});// the end of canvas.addMouseMotionListener()
		canvas.addMouseListener(new MouseAdapter() {
			public void mouseReleased(final MouseEvent arg0) {
				x = -1;
				y = -1;
			}// the end of mouseReleased

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
		});// the end of canvas.addMouseListener()

		toolBar.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(final MouseEvent arg0) {
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}// the end of mouseMoved()
		});// the end of toolBar.addMouseMotionListener()

		strokeButton1.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent arg0) {
				BasicStroke bs = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
				g.setStroke(bs);
			}// the end of actionPerformed
		});// the end of strokeButton1.addActionListener()

		strokeButton2.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent arg0) {
				BasicStroke bs = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
				g.setStroke(bs);
			}// the end of actionPeformed
		});// the end of strokeButton2.addActionListener()

		strokeButton3.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent arg0) {
				BasicStroke bs = new BasicStroke(4, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
				g.setStroke(bs);
			}// the end of actionPeformed
		});// the end of strokeButton3.addActionListener()

		backgroundButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent arg0) {
				Color bgColor = JColorChooser.showDialog(drawPictureFrame.this, "选择颜色对话框", Color.CYAN);
				if (bgColor != null) {
					backgroundColor = bgColor;
				}
				backgroundButton.setBackground(backgroundColor);
				g.setColor(backgroundColor);
				g.fillRect(0, 0, 800, 600);
				g.setColor(foreColor);
				canvas.repaint();
			}// the end of actionPerformed()
		});// the end of backgroundButton.addActionListener()

		foregroundButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent arg0) {
				Color fColor = JColorChooser.showDialog(drawPictureFrame.this, "选择颜色对话框", Color.CYAN);
				if (fColor != null) {
					foreColor = fColor;
				}
//				foregroundButton.setForeground(foreColor);
				foregroundButton.setBackground(foreColor);
				g.setColor(foreColor);
			}// the end of actionPerformed
		});// the end of foregroundButton.addActionListener()

		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent arg0) {
				g.setColor(backgroundColor);
				g.fillRect(0, 0, 800, 600);
				g.setColor(foreColor);
				canvas.repaint();
			}// the end of actionPerformed
		});// the end of clearButton.addActionListener()

		eraserButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent arg0) {
//				if(eraserButton.getText().equals("Eraser")) {
//					rubber = true;
//					eraserButton.setText("Draw");
//				}else {
//					rubber = false;
//					eraserButton.setText("Eraser");
//					g.setColor(foreColor);
//				}//the end of else
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
			}// the end of actionPerformed()
		});// the end of eraserButton.addActionListener()

		shapeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShapeWindow shapeWindow = new ShapeWindow(drawPictureFrame.this);
				int shapeButtonWidth = shapeButton.getWidth();
				int shapeWindowWidth = shapeWindow.getWidth();
				int shapeButtonX = shapeButton.getX();
				int shapeButtonY = shapeButton.getY();
				int shapeWindowX = getX() + shapeButtonX - (shapeWindowWidth - shapeButtonWidth) / 2;
				int shapeWinowY = getY() + shapeButtonY + 80;
				shapeWindow.setLocation(shapeWindowX, shapeWinowY);
				shapeWindow.setVisible(true);
			}// the end of actionPerformed()
		});// the end of shapeButton.addActionListener()

		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent arg0) {
				addWatermark();
				DrawImageUtil.saveImage(drawPictureFrame.this, image);
			}
		});// the end of saveButton.addActionListener()

		exitMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				System.exit(0);
			}// the end of actionPerformed
		});// the end of exitMenuItem.addActionListener()

		eraserMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
//				if(eraserMenuItem.getText().equals("Eraser")) {
//					rubber = true;
//					eraserMenuItem.setText("Draw");
//					eraserButton.setText("Draw");
//				}else {
//					rubber = false;
//					eraserMenuItem.setText("Eraser");
//					eraserButton.setText("Eraser");
//					g.setColor(foreColor);
//				}//the end of else
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
			}// the end of actionPerformed
		});// the end of eraserMenuItem.addActionListener()

		clearMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				g.setColor(backgroundColor);
				g.fillRect(0, 0, 800, 600);
				g.setColor(foreColor);
				canvas.repaint();
			}// the end of actionPerformed
		});// the end of clearMenuItem.addActionListener()

		strokeMenuItem1.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				BasicStroke bs = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
				g.setStroke(bs);
				strokeButton1.setSelected(true);
			}// the end of actionPerformed
		});// the end of strokeMenuItem1.addActionListener()

		strokeMenuItem2.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				BasicStroke bs = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
				g.setStroke(bs);
				strokeButton2.setSelected(true);
			}// the end of actionPeformed
		});// the end of strokeMenuItem2.addActionListener()

		strokeMenuItem3.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				BasicStroke bs = new BasicStroke(4, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
				g.setStroke(bs);
				strokeButton3.setSelected(true);
			}// the end of actionPeformed
		});// the end of strokeMenuItem3.addActionListener()

		backgroundMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				Color bgColor = JColorChooser.showDialog(drawPictureFrame.this, "选择颜色对话框", Color.CYAN);
				if (bgColor != null) {
					backgroundColor = bgColor;
				}
				backgroundButton.setBackground(backgroundColor);
				g.setColor(backgroundColor);
				g.fillRect(0, 0, 800, 600);
				g.setColor(foreColor);
				canvas.repaint();
			}// the end of actionPerformed()
		});// the end of backgroundMenuItem.addActionListener()

		foregroundMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				Color fColor = JColorChooser.showDialog(drawPictureFrame.this, "选择颜色对话框", Color.CYAN);
				if (fColor != null) {
					foreColor = fColor;
				}
//				foregroundButton.setForeground(foreColor);
				foregroundButton.setBackground(foreColor);
				g.setColor(foreColor);
			}// the end of actionPerformed
		});// the end of foregroundMenuItem.addActionListener()

		saveMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				addWatermark();
				DrawImageUtil.saveImage(drawPictureFrame.this, image);
			}
		});// the end of saveButton.addActionListener()

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

		showPicButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isVisible = picWindow.isVisible();
				if (isVisible) {
//					showPicButton.setText("Show the mode");
					showPicButton.setToolTipText("Show the mode");
					showPicButton.setIcon(new ImageIcon("src/Draw/icon/展开.png"));
					picWindow.setVisible(false);
				} else {
//					showPicButton.setText("Hide the mode");
					showPicButton.setToolTipText("Hide the mode");
					showPicButton.setIcon(new ImageIcon("src/Draw/icon/隐藏.png"));
					picWindow.setLocation(getX() - picWindow.getWidth() - 5, getY());
					picWindow.setVisible(true);
				} // the end of else
			}// the end of actionPerformed()
		});// the end of showPicButton.addActionListener()

	}// addListener()

	public void initShowPicButton() {
//		showPicButton.setText("Show the mode");
		showPicButton.setToolTipText("Show the mode");
		showPicButton.setIcon(new ImageIcon("src/Draw/icon/展开.png"));
	}// the end of iniShowPicButton()

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

	public void getShape(Shapes shape) {
		this.shape = shape;
		drawShape = true;
	}// the end of getShape()

	// The start of main()
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		drawPictureFrame frame = new drawPictureFrame();
		frame.setVisible(true);
	}// The end of main()

}// The end of drawPoctureFrame
