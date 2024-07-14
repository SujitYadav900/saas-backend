package com.example.maxcrm.MaxCrm.MBOPSRegistration.grades;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mbops/grades")
public class MBOPSGradesController {

    @Autowired
    private MBOPSGradesService gradesService;

    @GetMapping("/insert")
    MBOPSGradesDao insert(@RequestBody MBOPSGradesDao gradesDao){
        return gradesService.insert(gradesDao);
    }

    @GetMapping("/update")
    MBOPSGradesDao update(@RequestBody MBOPSGradesDao gradesDao){
        return gradesService.update(gradesDao);
    }

    @GetMapping("/delete")
    void delete(@RequestParam int id){
        gradesService.delete(id);
    }

    @GetMapping("/findall")
    List<MBOPSGradesDao> findAll(){
        return gradesService.getAll();
    }

    @GetMapping("/findbyname")
    MBOPSGradesDao findByName(@RequestParam String gradeName){
        return gradesService.findByName(gradeName);
    }
}
