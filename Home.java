import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends MyFrame {

	// Definiton of constructor
	public Home(String title) {
		super(); // Constructor of super class
		this.setTitle(title); // Here, set the name of the frame.
	}

	// Instance variable
	public static Home home = new Home("講義管理システム"); // This panel is a super panel among all panel in the Home screen.
	public static JPanel cardPanel;
	public static CardLayout layout; // For switching the screen. This is the key point of switching of screen.

	// Iternal class
	class ButtonAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			layout.show(home, command);
		}
	}

	// Here, create component of Home UI
	public Component createComponents() {
		JLabel label1 = new JLabel("講義管理システム");
		JLabel label2 = new JLabel("ラクタ");
		label1.setHorizontalAlignment(JLabel.CENTER);
		label2.setHorizontalAlignment(JLabel.CENTER);
		Font font1 = label1.getFont();
		label1.setFont(font1.deriveFont(Font.BOLD, 24));
		Font font2 = label2.getFont();
		label2.setFont(font2.deriveFont(Font.BOLD, 24));

		JButton lectureButton = new JButton("講義");
		JButton studentButton = new JButton("学生");
		JButton professorButton = new JButton("教員");
		ButtonAction buttonActionListener = new ButtonAction();
		lectureButton.addActionListener(buttonActionListener);
		lectureButton.setActionCommand("講義");
		studentButton.addActionListener(buttonActionListener);
		studentButton.setActionCommand("学生");
		professorButton.addActionListener(buttonActionListener);
		professorButton.setActionCommand("教員");
		
		JPanel subPanel = new JPanel();
		subPanel.setLayout(new GridLayout(1,0));
		subPanel.add(lectureButton);
		subPanel.add(studentButton);
		subPanel.add(professorButton);

		JPanel mainPannel = new JPanel();
		mainPannel.setLayout(new GridLayout(0,1));
		mainPannel.add(label1);
		mainPannel.add(label2);
		mainPannel.add(subPanel);

		// テスト部分
		LectureList lectureList = new LectureList();
		cardPanel = new JPanel();
		layout = new CardLayout();
		cardPanel.setLayout(layout);
		cardPanel.add(lectureList.createComponent(), "講義");
		mainPannel.add(cardPanel);

		return mainPannel;
	}

	// class method
	// Here, create home screen as an entity
	public static void createHome() {
		Component contents = home.createComponents();
		home.getContentPane().add(contents, BorderLayout.CENTER);
		home.setVisible(true);
	}
}