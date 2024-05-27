package com.itbank.smartFarm.controller;

import com.itbank.smartFarm.service.DownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.nio.file.Path;

@Controller
@RequestMapping("/download")
public class DownloadController {

    @Autowired
    private DownloadService ds;

    @GetMapping
    public String downloadPage(Model model) {
        // 다운로드할 파일 이름을 설정.
        model.addAttribute("filename", "IoT.apk");
        return "download";
    }

    // 파일 다운로드 메서드
    @GetMapping("/{filename}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
        try {
            // 파일을 로드합니다.
            Path file = ds.loadFile(filename);
            if (file == null) {
                return ResponseEntity.notFound().build();
            }
            // 파일을 리소스로 변환합니다.
            Resource resource = new UrlResource(file.toUri());
            // 파일을 다운로드하도록 응답 헤더를 설정하고 파일을 응답 본문에 추가하여 반환합니다.
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
