package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString

public class ContactDTO {
    String name;
    String lastName;
    String phone;
    String email;
    String description;
    String address;
}
