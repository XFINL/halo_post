package run.halo.contrib.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import run.halo.app.core.extension.content.Post;
import run.halo.app.core.extension.content.PostRequest;
import run.halo.app.core.extension.content.PostService;
import run.halo.contrib.model.ContributeRequest;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContributeService {

    private final PostService postService;

    public void handleContribution(ContributeRequest request) throws Exception {
        // 1. 上传图片、附件（如果有） -> 这里你可以接 AttachmentService
        String thumbnail = null;
        if (request.getImage() != null && !request.getImage().isEmpty()) {
            // TODO: 使用 attachmentService.upload() 保存图片，获取 URL
            thumbnail = "mock://image/" + request.getImage().getOriginalFilename();
        }

        // 2. 组装 PostRequest
        PostRequest postReq = new PostRequest();
        Post post = new Post();

        post.setSpec(new Post.PostSpec());
        post.getSpec().setTitle(request.getTitle());
        post.getSpec().setSlug(request.getTitle().toLowerCase().replace(" ", "-"));
        post.getSpec().setPublishTime(Instant.now());
        post.getSpec().setStatus(Post.Status.DRAFT); // 草稿
        post.getSpec().setExcerpt(request.getContent().substring(0, Math.min(100, request.getContent().length())));
        post.getSpec().setHeadImage(thumbnail);

        // 分类
        if (request.getCategory() != null) {
            post.getSpec().setCategories(List.of(request.getCategory()));
        }

        // 标签
        if (request.getTags() != null) {
            post.getSpec().setTags(List.of(request.getTags().split(",")));
        }

        // 内容
        post.setContent(request.getContent());

        postReq.setPost(post);

        // 3. 调用 Halo 的 PostService 保存文章
        postService.create(postReq);
    }
}
