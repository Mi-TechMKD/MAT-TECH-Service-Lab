package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RepairGuide {
    private int id;
    private int faultId;
    private String mediaType; // image/video
    private String path;
}
