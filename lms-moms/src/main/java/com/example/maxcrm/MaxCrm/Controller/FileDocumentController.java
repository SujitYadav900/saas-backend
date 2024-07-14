package com.example.maxcrm.MaxCrm.Controller;

import com.example.maxcrm.MaxCrm.Dao.FileDocument;
import com.example.maxcrm.MaxCrm.Dao.UserMasterDao;
import com.example.maxcrm.MaxCrm.Service.FileDocumentService;
import com.example.maxcrm.MaxCrm.Service.LeadMasterService;
import com.example.maxcrm.MaxCrm.Utility.UtilityClass;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping("/api/file")
public class FileDocumentController {
    @Autowired
    FileDocumentService fileDocumentService;
    @Autowired
    LeadMasterService leadMasterService;
    @PostMapping("/insertfile")
    public FileDocument insertFile(@RequestParam("file") MultipartFile multipartFile, Authentication authentication) throws Exception {
        FileDocument fileDocument=new FileDocument();
        UserMasterDao u = (UserMasterDao) authentication.getPrincipal();


        byte[] bytes=  multipartFile.getBytes();
        int maxSize= Integer.parseInt(UtilityClass.propertyService.findProperty("Fileoperation","maxFileSizeInByte"));

        if(bytes.length>maxSize)
        {
            throw new Exception("Maximum Size Exception!!Only Allowed Limit is "+(maxSize/1000000)+" MB");
        }
        fileDocument.setCreateBy(u.getUsername());
        fileDocument.setCreateAt(UtilityClass.getDateRedable());
        fileDocument.setImage(new Binary(bytes));
        fileDocument.setName(multipartFile.getOriginalFilename());


      return   fileDocumentService.insert(fileDocument);
    }

    @PostMapping("/insertfilelead")
    public FileDocument insertFileLead(@RequestParam("file") MultipartFile multipartFile, @RequestParam("leadId")long leadId,Authentication authentication) throws Exception {
        byte[] bytes=  multipartFile.getBytes();
        UserMasterDao userMasterDao= (UserMasterDao) authentication.getPrincipal();
        int maxSize= Integer.parseInt(UtilityClass.propertyService.findProperty("Fileoperation","maxFileSizeInByte"));

        if(bytes.length>maxSize)
        {
            throw new Exception("Maximum Size Exception!!Only Allowed Limit is "+(maxSize/1000000)+" MB");
        }
        FileDocument fileDocument=new FileDocument();
        fileDocument.setCreateBy(UtilityClass.ApplicationPrefix+leadId);
        fileDocument.setCreateAt(UtilityClass.getDateRedable());
        fileDocument.setImage(new Binary(bytes));
        fileDocument.setName(multipartFile.getOriginalFilename());
        if (UtilityClass.propertyService.findProperty("Lead", "UpdateLeadOnAttachmentCreation").equalsIgnoreCase("1")) {
            leadMasterService.updateLastUpdateLead(null, userMasterDao.getUsername(), UtilityClass.dateFilterDate(), fileDocument.getCreateAt(), leadId);

        }
        return   fileDocumentService.insert(fileDocument);
    }

    @DeleteMapping("/delete")
    public void deleteFile(@RequestParam("id")String id)
    {
        fileDocumentService.delete(id);
    }


    @GetMapping("/downloadfile")
    public ResponseEntity<InputStreamResource> insertFile(@RequestParam("id")String fileid, HttpServletRequest request) throws Exception {
        FileDocument fileDocument=fileDocumentService.getFile(fileid);
        if(fileDocument==null)
        {
            throw new Exception("Requested File Cannot Found!!");
        }

        HttpHeaders respHeaders = new HttpHeaders();
        respHeaders.setContentType(MediaType.valueOf("application/pdf"));
        respHeaders.setContentLength(fileDocument.getImage().getData().length);
        respHeaders.setContentDispositionFormData("attachment", fileDocument.getName());
        InputStreamResource isr = new InputStreamResource(new ByteArrayInputStream(fileDocument.getImage().getData()));
        return new ResponseEntity<InputStreamResource>(isr, respHeaders, HttpStatus.OK);
    }


    @GetMapping("/getfilesbycreatebylead")
    List<FileDocument> getAllByCreateByLead(@RequestParam("createby")String createBy)
    {
        return fileDocumentService.getByCreateBy(createBy);
    }


    @GetMapping("/getfilebycreateby")
    List<FileDocument> getAllByCreateBy( Authentication authentication)
    {
        UserMasterDao u = (UserMasterDao) authentication.getPrincipal();
       return fileDocumentService.getByCreateBy(u.getUsername());
    }

}
