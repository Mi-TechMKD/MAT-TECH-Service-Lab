package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Fault {
    private int id;
    private String name;
    private String symptom;
    private String solution;
    private int deviceId;
}
