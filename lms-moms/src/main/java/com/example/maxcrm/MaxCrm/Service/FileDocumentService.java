package com.example.maxcrm.MaxCrm.Service;

import com.example.maxcrm.MaxCrm.Dao.FileDocument;

import java.util.List;

public interface FileDocumentService {

    FileDocument insert(FileDocument fileDocument);
    List<FileDocument> insertBulk(    List<FileDocument> fileDocument);
    void delete(String id);
    List<FileDocument> getAll();
    List<FileDocument> getByCreateBy(String createBy);
    FileDocument getFile(String id);
}
