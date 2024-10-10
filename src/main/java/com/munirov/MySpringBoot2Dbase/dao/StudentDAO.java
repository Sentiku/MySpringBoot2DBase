package com.munirov.MySpringBoot2Dbase.dao;

import com.munirov.MySpringBoot2Dbase.entity.Student;
import com.munirov.MySpringBoot2Dbase.exception.NotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentDAO {
    List<Student>  getAllStudents();
    Student createStudent(Student student);
    Student updateStudent(Student student) throws ChangeSetPersister.NotFoundException, NotFoundException;
    Student getStudent(int id);
    int deleteStudent(int id);
}
