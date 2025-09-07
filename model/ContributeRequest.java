package run.halo.contrib.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
public class ContributeRequest {
    private String title;
    private String content;
    private String tags;
    private String category;
    private MultipartFile image;
    private MultipartFile attachment;
}
