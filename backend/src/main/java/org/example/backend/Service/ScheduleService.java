package org.example.backend.Service;

import org.example.backend.DTO.CoupleDTO;
import org.example.backend.models.Couple;
import org.example.backend.models.Schedule;
import org.example.backend.models.StudyGroup;
import org.example.backend.repo.CoupleRepository;
import org.example.backend.repo.ScheduleRepository;
import org.example.backend.repo.StudyGroupRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private CoupleRepository coupleRepository;

    @Autowired
    private StudyGroupRepository studyGroupRepository;



    public void saveSchedule(List<CoupleDTO> couplesDTO) {
        for (CoupleDTO coupleDTO : couplesDTO) {
            StudyGroup studyGroup = studyGroupRepository.findById(coupleDTO.getIdStudyGroup())
                    .orElseThrow(() -> new EntityNotFoundException("StudyGroup not found"));


            if (coupleDTO.getIdSchedule() == null) {
                scheduleRepository.findByStudyGroupAndDate(studyGroup, coupleDTO.getDate())
                    .orElseGet(() ->{
                        return createNewSchedule(coupleDTO, studyGroup);
                    });

            }


            if (coupleDTO.getIdCouple() == null) {
                createNewCouple(coupleDTO, studyGroup);
            } else {
                updateExistingCouple(coupleDTO);
            }
        }
    }
    private Schedule createNewSchedule(CoupleDTO coupleDTO, StudyGroup studyGroup) {
            Schedule schedule = new Schedule(coupleDTO.getDate(), studyGroup);
            return scheduleRepository.save(schedule);
    }

    private void createNewCouple(CoupleDTO coupleDTO, StudyGroup studyGroup) {
        Schedule schedule;
        if (coupleDTO.getIdSchedule() == null){
            schedule = scheduleRepository.findByStudyGroupAndDate(studyGroup, coupleDTO.getDate())
                    .orElseThrow(() -> new EntityNotFoundException("Schedule not found"));
        }
        else {
            schedule = scheduleRepository.findById(coupleDTO.getIdSchedule())
                    .orElseThrow(() -> new EntityNotFoundException("Schedule not found"));
        }



        Couple couple = new Couple(
                coupleDTO.getOffice() != null?coupleDTO.getOffice():"",
                coupleDTO.getAcademicSubject() != null?coupleDTO.getAcademicSubject():"",
                coupleDTO.getProfessor() != null?coupleDTO.getProfessor():"",
                studyGroup.getTitle(),
                schedule,
                coupleDTO.getNumber()
        );
        coupleRepository.save(couple);

    }
    private void updateExistingCouple(CoupleDTO coupleDTO) {
        Couple Couple = coupleRepository.findById(coupleDTO.getIdCouple())
                .orElseThrow(() -> new EntityNotFoundException("Couple not found"));

        Couple.setOffice(coupleDTO.getOffice() != null?coupleDTO.getOffice():"");
        Couple.setAcademicSubject(coupleDTO.getAcademicSubject() != null?coupleDTO.getAcademicSubject():"");
        Couple.setProfessor(coupleDTO.getProfessor() != null?coupleDTO.getProfessor():"");
        coupleRepository.save(Couple);
    }

}
