import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

import javax.swing.table.DefaultTableModel;

public class StudentManager {

	private ArrayList<Student> students = new ArrayList<>();

	public void addStudent(Student s) {
		students.add(s);
		updateDatabase();
		MainFrame.refreshTableModel();
	}

	public void deleteStudent(Student s) {

		int len = students.size();
		for (int i = 0; i < len; i++) {
			if (s.isEqual(students.get(i))) {
				students.remove(i);
				break;
			}
		}
		updateDatabase();
		MainFrame.refreshTableModel();
	}

	public void updateStudent(Student before, Student after) {
		int len = students.size();
		for (int i = 0; i < len; i++) {
			if (before.isEqual(students.get(i))) {
				students.get(i).setId(after.getId());
				students.get(i).setName(after.getName());
				students.get(i).setfName(after.getfName());
				students.get(i).setCollege(after.getCollege());
				students.get(i).setPhnNumber(after.getPhnNumber());
				break;
			}
		}

		updateDatabase();
		MainFrame.refreshTableModel();
	}

	public void updateDatabase() {

		File file = new File("file.txt");
		int len = students.size();
		try {
			Formatter ff = new Formatter(file);
			for (int i = 0; i < len; i++) {
				ff.format("%s\n", students.get(i).getId());
				ff.format("%s\n", students.get(i).getName());
				ff.format("%s\n", students.get(i).getfName());
				ff.format("%s\n", students.get(i).getCollege());
				ff.format("%s\n", students.get(i).getPhnNumber());
			}
			ff.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void initialize() {

		try {
			File file = new File("file.txt");
			Scanner scanner = new Scanner(file);
			students = new ArrayList<Student>();
			while (scanner.hasNextLine()) {
				students.add(new Student(Integer.parseInt(scanner.nextLine()), scanner.nextLine(), scanner.nextLine(),
						scanner.nextLine(), scanner.nextLine()));
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public DefaultTableModel getTableModel() {
		String[] str = { "Id", "Name", "F_Name", "College", "Phn_NUm" };
		DefaultTableModel dtm = new DefaultTableModel(str, 0);

		int len = students.size();
		for (int i = 0; i < len; i++) {
			String[] strings = new String[5];
			strings[0] = String.valueOf(students.get(i).getId());
			strings[1] = students.get(i).getName();
			strings[2] = students.get(i).getfName();
			strings[3] = students.get(i).getCollege();
			strings[4] = students.get(i).getPhnNumber();
			dtm.addRow(strings);
		}
		return dtm;
	}

}
