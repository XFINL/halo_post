package run.halo.contrib.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import run.halo.contrib.model.ContributeRequest;
import run.halo.contrib.service.ContributeService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/contribute")
public class ContributeController {

    private final ContributeService contributeService;

    // 👉 前端页面
    @GetMapping
    public String contributePage() {
        return "contribute"; // 对应 resources/templates/contribute.html
    }

    // 👉 表单提交
    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<String> contribute(
            @RequestParam String title,
            @RequestParam String content,
            @RequestParam(required = false) String tags,
            @RequestParam(required = false) String category,
            @RequestPart(required = false) MultipartFile image,
            @RequestPart(required = false) MultipartFile attachment
    ) {
        ContributeRequest req = new ContributeRequest(title, content, tags, category, image, attachment);
        try {
            contributeService.handleContribution(req);
            return ResponseEntity.ok("投稿成功，等待管理员审核。");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("投稿失败：" + e.getMessage());
        }
    }
}
