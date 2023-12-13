package com.petroli.gestionefasipetroli.services;

import com.petroli.gestionefasipetroli.entities.RiepilogoFile;
import com.petroli.gestionefasipetroli.repositories.RiepilogoFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class RiepilogoFileService {
    @Autowired
    private RiepilogoFileRepository fileDBRepository;

    public RiepilogoFile store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        RiepilogoFile FileDB = new RiepilogoFile(fileName, file.getContentType(), file.getBytes());
        return FileDB;
      //  return fileDBRepository.save(FileDB);
    }
    public RiepilogoFile getFile(long id) {
        return fileDBRepository.findById(id).get();
    }

    public Stream<RiepilogoFile> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }
}
