package org.example.backend.Service;

import org.example.backend.DTO.AccountDTO;
import org.example.backend.models.Account;
import org.example.backend.models.StudyGroup;
import org.example.backend.repo.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<String> validation(AccountDTO accountDTO){
        List<String> errors = new ArrayList<>();

        String fullName = accountDTO.getFullName().trim();
        short accessLevel = accountDTO.getAccessLevel();

        if (fullName == null || fullName.isEmpty()) {
            errors.add("полное имя не может быть пустым");
        }
        if (accessLevel < 1 || accessLevel > 4) {
            errors.add("уровней доступа  от 1 до 4");
        }
        return errors;
    }

    public void saveStudyGroup(AccountDTO accountDTO){
        String fullName = accountDTO.getFullName().trim();
        short accessLevel = accountDTO.getAccessLevel();

        Account account = new Account(fullName, accessLevel);
        accountRepository.save(account);
    }
}
