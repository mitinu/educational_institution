package org.example.backend.Service;


import org.example.backend.DTO.GroupDTO;
import org.example.backend.models.StudyGroup;
import org.example.backend.repo.StudyGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class StudyGroupService {


    @Autowired
    private StudyGroupRepository studyGroupRepository;

    public List<String> validation(GroupDTO groupDTO){
        String title = groupDTO.getTitle().trim();
        Integer course = groupDTO.getCourse();
        List<String> errors = new ArrayList<>();
        if (title == null || title.isEmpty()) {
            errors.add("Название группы не может быть пустым");
        }
        if (course == null || course < 1 || course > 4) {
            errors.add("Курс должен быть от 1 до 4");
        }
        if (studyGroupRepository.existsByTitle(title)) {
            errors.add("Группа с таким названием уже существует");
        }
        return errors;
    }




    public void saveStudyGroup(GroupDTO groupDTO){
        String title = groupDTO.getTitle().trim();
        int course = groupDTO.getCourse();

        StudyGroup group = new StudyGroup(title, course);
        studyGroupRepository.save(group);
    }
}
