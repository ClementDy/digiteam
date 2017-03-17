package glp.digiteam.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import glp.digiteam.entity.student.Mission;
import glp.digiteam.entity.student.Student;
import glp.digiteam.entity.student.Training;
import glp.digiteam.repository.MissionRepository;
import glp.digiteam.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	MissionRepository missionRepository;
	
	public Student saveStudentProfile(Student student) {
		return studentRepository.save(student);
	}

	public Student getStudentByNip(Integer nip) {
		if (studentRepository.findByNip(nip) != null) {
			return studentRepository.findByNip(nip);
		}
		return null;
	}

	public void unpublishProfil(Student student) {
		student.setStatut("register");
		studentRepository.save(student);
	}

	public List<Student> getAllCandidature() {
		List<Student> allStudent = studentRepository.findPublishedCandidature();
		return allStudent;
	}

	public List<Student> findWithParameter(String name, String formation, String mission) {

		if (!name.isEmpty() && formation.isEmpty() && mission.isEmpty()) {

			List<Student> listCandidatures = findByName(name);
			return listCandidatures;

		}
		if (name.isEmpty() && !formation.isEmpty() && mission.isEmpty()) {

			List<Student> listCandidatures = findWithTraining(formation);
			return listCandidatures;
		}

		String[] splited = name.split("\\s+");
		List<Student> allStudent;
		if (splited.length > 1) {
			allStudent = studentRepository.findWithFirstNameLastName(splited[0], splited[1]);
		} else if (!name.isEmpty()) {
			allStudent = studentRepository.findWithName(splited[0]);
		} else {
			allStudent = studentRepository.findPublishedCandidature();
		}

		if (name.isEmpty() && formation.isEmpty() && !mission.isEmpty()) {

			List<Student> students = new ArrayList<>();
			Mission missionFind = missionRepository.findOne(Long.parseLong(mission));
			if (missionFind != null)
				for (Student student : allStudent) {
					if (student.getWish().getMissions().contains(missionFind)) {
						if (!students.contains(student)) {
							students.add(student);
						}
					}
				}
			return students;
		}

		List<Student> students = new ArrayList<>();
		for (Student student : allStudent) {
			System.out.println("*****");
			List<Training> t = student.getTrainings();
			for (Training training : t) {
				System.out.println(training.getName().contains(formation.toUpperCase()));
				if (training.getName().contains(formation.toUpperCase())) {
					if (!mission.isEmpty()) {
						Mission missionFind = missionRepository.findOne(Long.parseLong(mission));
						if (missionFind != null)

							for (Mission missionl : student.getWish().getMissions()) {
								if (missionl == missionFind && !students.contains(student)) {
									students.add(student);
								}
							}
					} else {
						if (!students.contains(student)) {
							students.add(student);
						}

					}

				}
			}
			return students;
		}
		return students;
	}
	
	public Iterable<Student> findAll() {
		return studentRepository.findAll();
	}

	public List<Student> findByName(String name) {
		String[] splited = name.split("\\s+");
		if (splited.length > 1) {
			return studentRepository.findWithFirstNameLastName(splited[0], splited[1]);
		}
		return studentRepository.findWithName(splited[0]);
	}

	public List<Student> findWithTraining(String formation) {
		List<Student> allStudent = studentRepository.findPublishedCandidature();
		List<Student> students = new ArrayList<>();
		for (Student student : allStudent) {
			List<Training> t = student.getTrainings();
			for (Training training : t) {
				if (training.getName().contains(formation.toUpperCase())) {
					if (!students.contains(student)) {
						students.add(student);
					}
				}
			}
		}
		return students;
	}

}
