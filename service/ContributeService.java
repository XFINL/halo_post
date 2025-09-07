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
public class
