package org.example.backend.controllers;

import org.example.backend.Service.ScheduleService;
import org.example.backend.DTO.GroupRequest;
import org.example.backend.DTO.SchedulesRequest;
import org.example.backend.DTO.ScheduleResponse;
import org.example.backend.Service.StudyGroupService;
import org.example.backend.models.Schedule;
import org.example.backend.models.StudyGroup;
import org.example.backend.repo.ScheduleRepository;
import org.example.backend.repo.StudyGroupRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestApiController {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private StudyGroupRepository studyGroupRepository;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private StudyGroupService studyGroupService;

    @PostMapping("/getSchedule")
    public ResponseEntity<?> getSchedule(@RequestBody List<String> dates) {

        ScheduleResponse scheduleResponse = new ScheduleResponse();
        for (String date : dates) {
            List<Schedule> schedules = scheduleRepository.findByDate(date);
            scheduleResponse.add(schedules);
        }
        return ResponseEntity.ok().body(scheduleResponse);
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
            return ResponseEntity.ok().body(scheduleRepository.findByStudyGroup(group));
        }
        catch (EntityNotFoundException e){
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PostMapping("/addGroup")
    public ResponseEntity<?> addGroup(@RequestBody GroupRequest groupRequest) {
        List<String> errors = studyGroupService.validation(groupRequest);
        if (!errors.isEmpty()){
            return ResponseEntity.badRequest().body(errors);
        }
        else {
            studyGroupService.saveStudyGroup(groupRequest);
            return ResponseEntity.ok().build();
        }

    }
    @PostMapping("/setSchedule")
    public ResponseEntity<?> setSchedule(@RequestBody List<SchedulesRequest> schedules) {
        scheduleService.saveSchedule(schedules);
        return ResponseEntity.ok().build();
    }
}