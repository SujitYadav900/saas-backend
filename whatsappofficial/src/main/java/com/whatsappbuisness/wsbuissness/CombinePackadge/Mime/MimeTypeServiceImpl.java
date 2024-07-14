package com.whatsappbuisness.wsbuissness.CombinePackadge.Mime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MimeTypeServiceImpl implements MimeTypeService{
    @Autowired
    MimeTypeRepo mimeTypeRepo;
    @Override
    public MimeTypeDao findById(String ext) {
        return mimeTypeRepo.findById(ext).get();
    }
}
