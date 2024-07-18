import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// Here, define UI of Register form of new Lectures
public class RegisterFormLecture extends MyFrame {
	JTextField title;
	JTextField id;
	JTextField lectureName;
	JTextField classRoom;
	JTextField dayOfWeek;
	JTextField period;
	JTextField professor;

	class ButtonAction implements ActionListener {
		// constructor
		private JTextField name;
		private JTextField classRoom;
		private JTextField dayOfWeek;
		private JTextField period;

		public ButtonAction(JTextField name, JTextField classRoom, JTextField dayOfWeek, JTextField period) {
			this.name = name;
			this.classRoom = classRoom;
			this.dayOfWeek = dayOfWeek;
			this.period = period;
		}

		public void actionPerformed(ActionEvent e) {
			DB.insertIntoLectures(name.getText(), classRoom.getText(), DayOfWeek.convertIntToYoubi(dayOfWeek.getText()),
					period.getText());
			MyApp.initdata();
			dispose();
		}
	}

	// Constructor
	public RegisterFormLecture(String frameName) {
		super(frameName);
		JLabel titleLabel = new JLabel("新しい講義を追加");
		// JLabel idLabel = new JLabel("講義ID");
		JLabel lectureNameLabel = new JLabel("講義名");
		JLabel classRoomLabel = new JLabel("講義教室");
		JLabel dayOfWeekLabel = new JLabel("講義曜日");
		JLabel periodLabel = new JLabel("講義時限");
		// JLabel professorLabel = new JLabel("担当教員(IDか無記入)");

		id = new JTextField();
		lectureName = new JTextField();
		classRoom = new JTextField();
		dayOfWeek = new JTextField();
		period = new JTextField();
		// professor = new JTextField();

		JButton addButton = new JButton("追加");
		ButtonAction buttonListener = new ButtonAction(lectureName, classRoom, dayOfWeek, period);
		addButton.addActionListener(buttonListener);

		JPanel pane1 = new JPanel();
		pane1.add(titleLabel);
		JPanel pane3 = new JPanel();
		pane3.setLayout(new GridLayout(2, 0));
		pane3.add(lectureNameLabel);
		pane3.add(lectureName);

		JPanel pane4 = new JPanel();
		pane4.setLayout(new GridLayout(2, 0));
		pane4.add(classRoomLabel);
		pane4.add(classRoom);

		JPanel pane6 = new JPanel();
		pane6.setLayout(new GridLayout(2, 0));
		pane6.add(periodLabel);
		pane6.add(period);

		// 曜日登録用コンボボックス
		JComboBox<DayOfWeek> dayOfWeekComboBox = new JComboBox<DayOfWeek>(MyApp.dayOfWeeks);
		dayOfWeekComboBox.setSelectedIndex(-1);
		dayOfWeekComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				DayOfWeek selectedDayOfWeek = (DayOfWeek) cb.getSelectedItem();
				dayOfWeek.setText(selectedDayOfWeek.toString());
			}
		});
		this.add(dayOfWeekComboBox);
		this.pack();
		this.setVisible(true);
		
		JPanel paneDayOfWeek = new JPanel();
		paneDayOfWeek.setLayout(new GridLayout(1, 0));
		paneDayOfWeek.add(dayOfWeekLabel);
		paneDayOfWeek.add(dayOfWeekComboBox);

		JPanel mainPane = new JPanel();
		mainPane.setLayout(new GridLayout(0, 1));
		mainPane.add(pane1);
		// mainPane.add(pane2);
		mainPane.add(pane3);
		mainPane.add(pane4);
		mainPane.add(paneDayOfWeek);
		mainPane.add(pane6);
		// mainPane.add(pane7);
		mainPane.add(addButton);

		this.getContentPane().add(mainPane, BorderLayout.CENTER);
		this.setSize(500, 600);
	}

	public static void createRegisterFormLecture() {
		RegisterFormLecture registerFormLecture = new RegisterFormLecture("新しい講義を追加");
		registerFormLecture.setVisible(true);
	}
}
