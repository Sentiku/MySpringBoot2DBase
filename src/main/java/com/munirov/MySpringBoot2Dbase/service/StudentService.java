package com.munirov.MySpringBoot2Dbase.service;

import com.munirov.MySpringBoot2Dbase.entity.Student;
import com.munirov.MySpringBoot2Dbase.exception.CreationException;
import com.munirov.MySpringBoot2Dbase.exception.NotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    List<Student> getAllStudents();

    Student createStudent(Student student) throws CreationException;

    Student updateStudent(Student student) throws NotFoundException, ChangeSetPersister.NotFoundException;

    Student getStudent(int id) throws NotFoundException;

    int deleteStudent(int id) throws NotFoundException;
}
