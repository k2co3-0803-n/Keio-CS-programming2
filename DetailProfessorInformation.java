import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DetailProfessorInformation extends MyFrame {
    public DetailProfessorInformation(String frameName, Professor professor) {
        super(frameName);
        JLabel idLabel = new JLabel("Professor ID: " + professor.getProfessorID());
        JLabel professorNameLabel = new JLabel("Professor Name: " + professor.getName());

        JTextField professorName = new JTextField(professor.getName());

        JButton editButton = new JButton("edit");
        EditButtonAction buttonListener = new EditButtonAction(professor.getProfessorID(), professorName);
        editButton.addActionListener(buttonListener);
        JButton deleteButton = new JButton("delete");
        DeleteButtonAction deleteButtonActionListener = new DeleteButtonAction(professor);
        deleteButton.addActionListener(deleteButtonActionListener);

        FlowLayout centerLayout = new FlowLayout(FlowLayout.CENTER);

        JPanel pane1 = new JPanel(centerLayout);
        pane1.setLayout(new GridLayout(1, 0));
        pane1.add(idLabel);
        JPanel pane2 = new JPanel(centerLayout);
        pane2.setLayout(new GridLayout(1, 0));
        pane2.add(professorNameLabel);
        pane2.add(professorName);
        JPanel pane6 = new JPanel(centerLayout);
        pane6.setLayout(new GridLayout(2, 0));
        pane6.add(editButton);
        pane6.add(deleteButton);
        JPanel mainPane = new JPanel(centerLayout);
        mainPane.setLayout(new GridLayout(0, 1));
        mainPane.add(pane1);
        mainPane.add(pane2);
        mainPane.add(pane6);

        this.getContentPane().add(mainPane, BorderLayout.CENTER);
        this.setSize(500, 600);
    }

    class EditButtonAction implements ActionListener {
        private String id;
        private JTextField professorName;

        public EditButtonAction(String id, JTextField professorName) {
            this.id = id;
            this.professorName = professorName;
        }

        public void actionPerformed(ActionEvent e) {
            String name = professorName.getText();
            DB.updateTeacher(id, name);
            MyApp.initData();
            dispose();
        }
    }

    class DeleteButtonAction implements ActionListener {
        private Professor professor; // Field to hold lecture information

        // Constructor to receive lecture information
        public DeleteButtonAction(Professor professor) {
            this.professor = professor;
        }

        public void actionPerformed(ActionEvent e) {
            int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this professor?",
                    "Confirmation", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                DB.deleteFromTeacher(professor.getProfessorID());
                MyApp.initData();
                dispose();
            }
        }
    }

    public static void createDetailProfessorInformation(Professor professor) {
        DetailProfessorInformation detailProfessorInformation = new DetailProfessorInformation(
                "Detail Professor Information", professor);
        detailProfessorInformation.setVisible(true);
    }
}
