package services.objex;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Msg {

    @Id
    @GeneratedValue
    private Long id;
    private String m;

    public Msg(String m) {
        this.m = m;
    }
}