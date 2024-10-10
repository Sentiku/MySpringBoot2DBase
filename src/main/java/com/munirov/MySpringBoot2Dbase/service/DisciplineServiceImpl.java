package com.munirov.MySpringBoot2Dbase.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import com.munirov.MySpringBoot2Dbase.dao.DisciplineDAO;
import com.munirov.MySpringBoot2Dbase.entity.Discipline;
import com.munirov.MySpringBoot2Dbase.exception.CreationException;
import com.munirov.MySpringBoot2Dbase.exception.NotFoundException;

import java.util.List;
@Service
public class DisciplineServiceImpl implements  DisciplineService {
    @Autowired
    private DisciplineDAO DisciplineDAO;

    @Override
    @Transactional
    public List<Discipline> getAllDisciplines() {
        return DisciplineDAO.getAllDisciplines();
    }

    @Override
    @Transactional
    public Discipline createDiscipline(Discipline Discipline) throws CreationException {
        if (Discipline.getId() != 0) {
            throw new CreationException("attempt to create instance with id field");
        }
        return DisciplineDAO.createDiscipline(Discipline);
    }

    @Override
    @Transactional
    public Discipline updateDiscipline(Discipline Discipline) throws NotFoundException, ChangeSetPersister.NotFoundException {
        return DisciplineDAO.updateDiscipline(Discipline);
    }

    @Override
    @Transactional
    public Discipline getDiscipline(int id) throws NotFoundException {
        Discipline Discipline = DisciplineDAO.getDiscipline(id);
        if (Discipline == null) {
            throw new NotFoundException("Discipline not found.");
        }
        return Discipline;
    }

    @Override
    @Transactional
    public int deleteDiscipline(int id) throws NotFoundException {
        int deleted = DisciplineDAO.deleteDiscipline(id);
        if (deleted == 0) {
            throw new NotFoundException("Discipline not found.");
        }
        return deleted;
    }
}
