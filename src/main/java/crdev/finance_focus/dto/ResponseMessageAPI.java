package crdev.finance_focus.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import crdev.finance_focus.enums.ResultCode;
import crdev.finance_focus.enums.ResultCodeAPI;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponseMessageAPI<T>{
    T result;
    String resultCode;
    String details;
    String message;
    int code;

    public ResponseMessageAPI(T result, ResultCodeAPI resultCode, String details, String message, int code) {
        this.result = result;
        this.resultCode = resultCode.getDescription();
        this.details = details;
        this.message = message;
        this.code = code;
    }
}
