package com.munirov.MySpringBoot2Dbase.controller;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.munirov.MySpringBoot2Dbase.entity.Discipline;
import com.munirov.MySpringBoot2Dbase.exception.CreationException;
import com.munirov.MySpringBoot2Dbase.exception.NotFoundException;
import com.munirov.MySpringBoot2Dbase.service.DisciplineService;

import java.util.List;


@Slf4j
@RestController
@RequestMapping(value = "/api")
public class DisciplineController {

    @Autowired
    private DisciplineService disciplineService;

    @GetMapping("/disciplines")
    public List<Discipline> allDisciplines() {
        return disciplineService.getAllDisciplines();
    }

    @GetMapping("/disciplines/{id}")
    public ResponseEntity<?> getDiscipline(@PathVariable("id") int id) {
        JSONObject resp = new JSONObject();
        Discipline discipline;

        try {
            discipline = disciplineService.getDiscipline(id);
        } catch (NotFoundException e) {
            String message = String.format("discipline with id=%d not found.", id);
            resp.put("message", message);
            return new ResponseEntity<>(resp.toMap(), HttpStatus.NOT_FOUND);
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException(e);
        }

        return new ResponseEntity<>(discipline, HttpStatus.OK);
    }

    @PostMapping("/disciplines")
    public ResponseEntity<?> saveDiscipline(@RequestBody Discipline discipline) {
        try {
            discipline = disciplineService.createDiscipline(discipline);
        } catch (CreationException e) {
            JSONObject resp = new JSONObject();
            resp.put("message", "id field not allowed");
            return new ResponseEntity<>(resp.toMap(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(discipline, HttpStatus.CREATED);
    }

    @PutMapping("/disciplines")
    public ResponseEntity<?> updateDiscipline(@RequestBody Discipline discipline) {
        JSONObject resp = new JSONObject();
        HttpStatus httpStatus;
        String message;

        try {
            discipline = disciplineService.updateDiscipline(discipline);
        } catch (NotFoundException e) {
            message = String.format("discipline with id=%d not found.", discipline.getId());
            resp.put("message", message);
            httpStatus = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(resp.toMap(), httpStatus);
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException(e);
        }

        return new ResponseEntity<>(discipline, HttpStatus.OK);
    }

    @DeleteMapping("/disciplines/{id}")
    public ResponseEntity<?> deleteDiscipline(@PathVariable("id") int id) {
        int deleted;
        JSONObject resp = new JSONObject();
        HttpStatus httpStatus;
        String message;

        try {
            deleted = disciplineService.deleteDiscipline(id);
            message = String.format("discipline with id=%d deleted.", id);
            httpStatus = HttpStatus.GONE;
        } catch (ChangeSetPersister.NotFoundException e) {
            deleted = 0;
            message = String.format("discipline with id=%d not found.", id);
            httpStatus = HttpStatus.NOT_FOUND;
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }

        resp.put("message", message);
        resp.put("deleted_count", deleted);
        return new ResponseEntity<>(resp.toMap(), httpStatus);
    }

}
