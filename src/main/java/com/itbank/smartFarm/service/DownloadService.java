package com.itbank.smartFarm.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class DownloadService {

    // 다운로드 디렉토리 주입
    @Value("${file.download-dir}")
    private String downloadDir;

    // 지정된 파일 이름을 사용하여 파일 경로를 로드하는 메서드
    public Path loadFile(String filename) {
        try {
            // 파일 저장 경로를 설정
            Path file = Paths.get(downloadDir).resolve(filename);
            // 파일이 존재 여부 확인
            if (Files.exists(file)) {
                return file;
            } else {
                throw new IOException("File not found");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
