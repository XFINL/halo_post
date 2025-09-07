package run.halo.contrib;

import org.springframework.context.annotation.Configuration;
import run.halo.app.plugin.Plugin;

@Configuration
public class ContributePlugin implements Plugin {

    @Override
    public void start() {
        System.out.println("Halo 前台投稿插件已启动");
    }

    @Override
    public void stop() {
        System.out.println("Halo 前台投稿插件已停止");
    }
}
