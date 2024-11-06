package exercise.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDate;

// BEGIN
@Getter
@Setter
public class GuestCreateDTO {

    @NonNull
    private String name;

    @Email
    private String email;

    @Size(min = 11, max = 13)
    @Pattern(regexp = "^\\+\\d*$")
    private String phoneNumber;

    @Pattern(regexp = "^.{4}$")
    private String clubCard;

    @Future
    private LocalDate cardValidUntil;
}
// END
