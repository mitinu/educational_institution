package org.example.backend.controllers;

import org.example.backend.DTO.*;
import org.example.backend.Service.AccountService;
import org.example.backend.Service.ScheduleService;
import org.example.backend.Service.StudyGroupService;
import org.example.backend.models.Account;
import org.example.backend.models.Couple;
import org.example.backend.models.Schedule;
import org.example.backend.models.StudyGroup;
import org.example.backend.repo.AccountRepository;
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

    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountRepository accountRepository;

    @PostMapping("/getSchedule")
    public ResponseEntity<?> getSchedule(@RequestBody List<String> dates) {
        short status = 200;
        String message = "вы получили полный список пар";

        List<CoupleDTO> couplesDTO = new ArrayList<>();
        for (String date : dates) {
            try {
                List<Couple> Couples = coupleRepository.findBySchedule_Date(LocalDate.parse(date))
                        .orElseThrow(() -> new EntityNotFoundException("couple not found"));
                for(Couple couple: Couples){
                    couplesDTO.add(new CoupleDTO(couple));
                }
            }
            catch (EntityNotFoundException e){
                status = 207;
                message = "вы получили частичный список пар";
            }
        }
        if (couplesDTO.size()<=0){
            status = 204;
            message = "список пар пуст";
        }
        Response<List<CoupleDTO>> response = new Response<List<CoupleDTO>>(status, message, couplesDTO);
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping("/getGroups")
    public ResponseEntity<?> getGroups() {
        short status = 200;
        String message = "список групп";
        Response response;
        try{
            Iterable<StudyGroup> studyGroups = studyGroupRepository.findAll();
            if (!studyGroups.iterator().hasNext()) {
                status = 204;
                message = "список групп пуст";
            }
            response = new Response<Iterable<StudyGroup>>(status, message, studyGroups);

        }
        catch (EntityNotFoundException e){
            status = 400;
            message = "неиндифицированная ошибка";
            response = new Response<EntityNotFoundException>(status, message, e);
        }

        return ResponseEntity.status(status).body(response);
    }
    @GetMapping("/getAccounts")
    public ResponseEntity<?> getAccount() {
        short status = 200;
        String message = "список учетных записей";

        Iterable<Account> accounts = accountRepository.findAll();
        if (!accounts.iterator().hasNext()) {
            status = 204;
            message = "список учетных записей пуст";
        }

        Response<Iterable<Account>> response = new Response<Iterable<Account>>(status, message, accounts);
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping("/getSchedulesGroup/{groupId}")
    public ResponseEntity<?> getScedulesGroup(@PathVariable String groupId) {
        short status = 200;
        String message = "расписание";

        List<ScheduleDTO> schedulesDTO = new ArrayList<>();

        try {
            StudyGroup group = studyGroupRepository.findById(Long.parseLong(groupId))
                    .orElseThrow(() -> new EntityNotFoundException("StudyGroup not found"));

            List<Schedule> schedules = scheduleRepository.findByStudyGroup(group)
                    .orElseThrow(() -> new EntityNotFoundException("schedules not found"));


            for (Schedule schedule: schedules){
                if (!schedule.getDate().isBefore(LocalDate.now())&&schedulesDTO.size()<maxNumberDaysDisplayed){
                    List<Couple> couples = coupleRepository.findBySchedule(schedule)
                            .orElseThrow(() -> new EntityNotFoundException("couple not found"));
                    schedulesDTO.add(new ScheduleDTO(schedule, couples));
                }
            }

            Response<List<ScheduleDTO>> response = new Response<List<ScheduleDTO>>(status, message, schedulesDTO);

            return ResponseEntity.status(status).body(response);
        }
        catch (EntityNotFoundException e){
            status = 400;
            message = "error";//TODO идифицировать ошибку
            Response<EntityNotFoundException> response = new Response<EntityNotFoundException>(status, message, e);

            return ResponseEntity.status(status).body(response);
        }
    }

    @PostMapping("/addGroup")
    public ResponseEntity<?> addGroup(@RequestBody GroupDTO groupDTO) {
        short status = 200;
        String message = "группа создана";

        List<String> errors = studyGroupService.validation(groupDTO);
        if (!errors.isEmpty()){
            status = 422;
            message = "ошибка валидации";
            Response<List<String>> response = new Response<List<String>>(status, message, errors);
            return ResponseEntity.status(status).body(response);
        }
        else {
            studyGroupService.saveStudyGroup(groupDTO);
            Response<Object> response = new Response<Object>(status, message, null);
            return ResponseEntity.status(status).body(response);
        }

    }
    @PostMapping("/addAccount")
    public ResponseEntity<?> addAccount(@RequestBody AccountDTO accountDTO) {
        short status = 200;
        String message = "учетная запись создана";

        List<String> errors = accountService.validation(accountDTO);
        if (!errors.isEmpty()){
            status = 422;
            message = "ошибка валидации";
            Response<List<String>> response = new Response<List<String>>(status, message, errors);
            return ResponseEntity.status(status).body(response);
        }
        else {
            accountService.saveStudyGroup(accountDTO);
            Response<Object> response = new Response<Object>(status, message, null);
            return ResponseEntity.status(status).body(response);
        }
    }
    @PostMapping("/setSchedule")
    public ResponseEntity<?> setSchedule(@RequestBody List<CoupleDTO> couplesDTO) {
        short status = 200;
        String message = "расисание изменино";

        scheduleService.saveSchedule(couplesDTO);
        Response<Object> response = new Response<Object>(status, message, null);

        return ResponseEntity.status(status).body(response);
    }
}