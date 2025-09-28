package org.example.backend.DTO;

public class GroupDTO {
    private String title;
    private int course;

    // Конструкторы, геттеры и сеттеры
    public GroupDTO() {}

    public GroupDTO(String title, int course) {
        this.title = title;
        this.course = course;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public int getCourse() { return course; }
    public void setCourse(int course) { this.course = course; }
}