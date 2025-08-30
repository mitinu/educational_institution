package org.example.backend.Service;

import org.example.backend.DTO.CoupleRequest;
import org.example.backend.DTO.SchedulesRequest;
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

import java.util.ArrayList;
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


    public void saveSchedule(List<SchedulesRequest> scheduleRequests) {
        for (SchedulesRequest request : scheduleRequests) {
            if (request.getId() == null) {
                createNewSchedule(request);
            } else {
                updateExistingSchedule(request);
            }
        }
    }
    private void createNewSchedule(SchedulesRequest request) {

        StudyGroup studyGroup = studyGroupRepository.findById(request.getIdGroup())
                .orElseThrow(() -> new EntityNotFoundException("StudyGroup not found"));

        List<Couple> couples = createCouplesFromRequest(request.getCouplesRequest());
        coupleRepository.saveAll(couples);

        Schedule schedule = new Schedule(couples, request.getDate(), studyGroup);
        scheduleRepository.save(schedule);
    }
    private void updateExistingSchedule(SchedulesRequest request) {
        Schedule existingSchedule = scheduleRepository.findById(request.getId())
                .orElseThrow(() -> new EntityNotFoundException("Schedule not found"));

        List<Couple> existingCouples = existingSchedule.getListCouple();
        List<CoupleRequest> newCoupleRequests = request.getCouplesRequest();

        for (int i = 0; i < 6; i++) {
            Couple existingCouple = existingCouples.get(i);
            CoupleRequest newRequest = newCoupleRequests.get(i);
            System.out.println(existingCouple.getOffice() +"\t"+ newRequest.getOffice());
            existingCouple.setOffice(newRequest.getOffice() != null?newRequest.getOffice():"");
            existingCouple.setAcademicSubject(newRequest.getAcademicSubject() != null?newRequest.getAcademicSubject():"");
            existingCouple.setProfessor(newRequest.getProfessor() != null?newRequest.getProfessor():"");
        }

        coupleRepository.saveAll(existingCouples);
    }
    private List<Couple> createCouplesFromRequest(List<CoupleRequest> couplesRequest) {
        List<Couple> couples = new ArrayList<>();
        for (CoupleRequest couplerequest : couplesRequest) {

            couples.add(new Couple(
                    couplerequest.getOffice() != null?couplerequest.getOffice():"",
                    couplerequest.getAcademicSubject() != null?couplerequest.getAcademicSubject():"",
                    couplerequest.getProfessor() != null?couplerequest.getProfessor():""
            ));
        }

        return couples;
    }
}
