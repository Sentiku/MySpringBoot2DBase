package com.munirov.MySpringBoot2Dbase.service;


import com.munirov.MySpringBoot2Dbase.entity.Discipline;
import com.munirov.MySpringBoot2Dbase.exception.CreationException;
import com.munirov.MySpringBoot2Dbase.exception.NotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface DisciplineService {
    List<Discipline> getAllDisciplines();

    Discipline createDiscipline(Discipline Discipline) throws CreationException;

    Discipline updateDiscipline(Discipline Discipline) throws NotFoundException, ChangeSetPersister.NotFoundException;

    Discipline getDiscipline(int id) throws ChangeSetPersister.NotFoundException, NotFoundException;

    int deleteDiscipline(int id) throws ChangeSetPersister.NotFoundException, NotFoundException;
}
