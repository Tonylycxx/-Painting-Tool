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
		flow.setHgap(20);
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
