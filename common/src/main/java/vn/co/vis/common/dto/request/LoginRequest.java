package vn.co.vis.common.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NotEmpty(message = "userName{validate.required}")
    @Size(max = 30, message = "userName{validate.over.length}")
    private String userName;
    @NotEmpty(message = "password{validate.required}")
    @Size(max = 30, message = "password{validate.over.length}")
    private String password;

}
