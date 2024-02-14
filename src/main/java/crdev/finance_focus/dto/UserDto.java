package crdev.finance_focus.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserDto {
    private Long Id;
    private String username;
    private String password;
    private List<AccountDto> accounts;

}
