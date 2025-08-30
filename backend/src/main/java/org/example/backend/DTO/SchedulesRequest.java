package org.example.backend.DTO;

import java.util.List;

public class SchedulesRequest {
    private List<CoupleRequest> couplesRequest;
    private String date;
    private Long idGroup;
    private Long id;

    public SchedulesRequest() {}

    public SchedulesRequest(List<CoupleRequest> couplesRequest, String date, Long idGroup, Long id) {
        this.couplesRequest = couplesRequest;
        this.date = date;
        this.idGroup = idGroup;
        this.id = id;
    }

    public List<CoupleRequest> getCouplesRequest() {
        return couplesRequest;
    }

    public void setCouplesRequest(List<CoupleRequest> couplesRequest) {
        this.couplesRequest = couplesRequest;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(Long idGroup) {
        this.idGroup = idGroup;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
