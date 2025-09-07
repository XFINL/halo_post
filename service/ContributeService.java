package run.halo.contrib.service;

import org.springframework.stereotype.Service;
import run.halo.contrib.model.ContributeRequest;

@Service
public class ContributeService {

    public void handleContribution(ContributeRequest request) {
        // 这里暂时只是模拟保存
        // 以后可以接 Halo 的 Post API 把投稿写入数据库
        System.out.println("收到投稿：");
        System.out.println("标题：" + request.getTitle());
        System.out.println("内容：" + request.getContent());
        System.out.println("分类：" + request.getCategory());
        System.out.println("标签：" + request.getTags());
        if (request.getImage() != null) {
            System.out.println("图片：" + request.getImage().getOriginalFilename());
        }
        if (request.getAttachment() != null) {
            System.out.println("附件：" + request.getAttachment().getOriginalFilename());
        }
    }
}
