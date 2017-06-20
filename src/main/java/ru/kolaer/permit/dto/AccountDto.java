package ru.kolaer.permit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kolaer.permit.entity.RoleEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by danilovey on 24.04.2017.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private Long employeeId;
    private String username;
    private String password;
    private List<RoleEntity> roles = new ArrayList<>();


}
