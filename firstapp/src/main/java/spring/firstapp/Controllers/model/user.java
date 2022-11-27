package spring.firstapp.Controllers.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class user {
    public Long id;
    public String name;
    public String username;
    

    public Long getId() {
        return id;
    }

}
