package demo.domain;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
public class Message implements Serializable {
    private Long id;
    private String msg;
    private Date sendTime;
}
