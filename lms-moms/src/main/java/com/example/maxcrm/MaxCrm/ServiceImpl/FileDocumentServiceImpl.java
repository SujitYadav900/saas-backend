package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.FileDocument;
import com.example.maxcrm.MaxCrm.Repo.FileDocumentRepo;
import com.example.maxcrm.MaxCrm.Service.FileDocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FileDocumentServiceImpl implements FileDocumentService {
    Logger logger = LoggerFactory.getLogger(FileDocumentService.class);
    @Autowired
    FileDocumentRepo fileDocumentRepo;

    @Override
    public FileDocument insert(FileDocument fileDocument) {
        logger.info("Inserting {}",fileDocument);
        return fileDocumentRepo.insert(fileDocument);
    }

    @Override
    public List<FileDocument> insertBulk(    List<FileDocument> fileDocument) {
        logger.info("Inserting Multiple{}",fileDocument);
        return fileDocumentRepo.insert(fileDocument);
    }

    @Override
    public void delete(String id) {
        logger.info("Deleting{}",id);
        fileDocumentRepo.deleteById(id);
    }

    @Override
    public List<FileDocument> getAll() {
        return null;
    }

    @Override
    public List<FileDocument> getByCreateBy(String createBy) {
        return fileDocumentRepo.getFileByCreateBy(createBy, Sort.by(Sort.Direction.DESC, "id"));
    }

    @Override
    public FileDocument getFile(String id) {
        FileDocument fd = null;
        try{
            fd = fileDocumentRepo.findById(id).get();
        }catch (Exception ex){
            logger.info("Cant find file by ID : {}",id);
        }
        return fd;
    }
}
