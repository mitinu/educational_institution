package org.example.backend.controllers;

import org.example.backend.DTO.*;
import org.example.backend.Service.ScheduleService;
import org.example.backend.Service.StudyGroupService;
import org.example.backend.models.Couple;
import org.example.backend.models.Schedule;
import org.example.backend.models.StudyGroup;
import org.example.backend.repo.CoupleRepository;
import org.example.backend.repo.ScheduleRepository;
import org.example.backend.repo.StudyGroupRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RestApiController {

    @Value("${app.schedule.max-days-displayed}")
    private int maxNumberDaysDisplayed;


    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private StudyGroupRepository studyGroupRepository;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private StudyGroupService studyGroupService;

    @Autowired
    private CoupleRepository coupleRepository;

    @PostMapping("/getSchedule")
    public ResponseEntity<?> getSchedule(@RequestBody List<String> dates) {

        List<CoupleDTO> couplesDTO = new ArrayList<>();
        for (String date : dates) {
            System.out.println(date);
            List<Couple> Couples = coupleRepository.findBySchedule_Date(LocalDate.parse(date));
            for(Couple couple: Couples){
                couplesDTO.add(new CoupleDTO(couple));
            }

        }
        return ResponseEntity.ok().body(couplesDTO);
    }

    @GetMapping("/getGroups")
    public ResponseEntity<?> getGroups() {
        return ResponseEntity.ok().body(studyGroupRepository.findAll());
    }

    @GetMapping("/getScedulesGroup/{groupId}")
    public ResponseEntity<?> getScedulesGroup(@PathVariable String groupId) {
        try {
            StudyGroup group = studyGroupRepository.findById(Long.parseLong(groupId))
                    .orElseThrow(() -> new EntityNotFoundException("StudyGroup not found"));

            List<Schedule> schedules = scheduleRepository.findByStudyGroup(group)
                    .orElseThrow(() -> new EntityNotFoundException("schedules not found"));

            List<ScheduleDTO> schedulesDTO = new ArrayList<>();

            for (Schedule schedule: schedules){
                if (!schedule.getDate().isBefore(LocalDate.now())&&schedulesDTO.size()<maxNumberDaysDisplayed){
                    List<Couple> couples = coupleRepository.findBySchedule(schedule)
                            .orElseThrow(() -> new EntityNotFoundException("couple not found"));
                    schedulesDTO.add(new ScheduleDTO(schedule, couples));
                }
            }


            return ResponseEntity.ok().body(schedulesDTO);
        }
        catch (EntityNotFoundException e){
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PostMapping("/addGroup")
    public ResponseEntity<?> addGroup(@RequestBody GroupDTO groupDTO) {
        List<String> errors = studyGroupService.validation(groupDTO);
        if (!errors.isEmpty()){
            return ResponseEntity.badRequest().body(errors);
        }
        else {
            studyGroupService.saveStudyGroup(groupDTO);
            return ResponseEntity.ok().build();
        }

    }
    @PostMapping("/setSchedule")
    public ResponseEntity<?> setSchedule(@RequestBody List<CoupleDTO> couplesDTO) {
        scheduleService.saveSchedule(couplesDTO);
        return ResponseEntity.ok().build();
    }
}