package com.munirov.MySpringBoot2Dbase.dao;

import com.munirov.MySpringBoot2Dbase.entity.Discipline;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface DisciplineDAO {
    List<Discipline> getAllDisciplines();

    Discipline createDiscipline(Discipline Discipline);

    Discipline updateDiscipline(Discipline Discipline) throws ChangeSetPersister.NotFoundException;

    Discipline getDiscipline(int id);

    int deleteDiscipline(int id);
}
