package org.example.backend.DTO;

public class AccountDTO {
    private String fullName;
    private short accessLevel;

    public AccountDTO() {}

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public short getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(short accessLevel) {
        this.accessLevel = accessLevel;
    }
}
