package com.javamog.potapov.controller;

import com.javamog.potapov.domain.Category;
import com.javamog.potapov.domain.enums.Urgency;
import com.javamog.potapov.json.JsonRestUtils;
import com.javamog.potapov.service.MaintenanceEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/enums", produces = "application/json")
public class EnumsController {

    @Autowired
    private MaintenanceEntityService entityService;

    @RequestMapping("/categories")
    public ResponseEntity getCategories(){

        List<Category> categories = entityService.getCategories();

        return JsonRestUtils.toJsonResponse(categories);
    }

    @RequestMapping("/urgency")
    public ResponseEntity getUrgency(){
        List<Urgency> urgencies = new ArrayList<>();
        urgencies.add(Urgency.CRITICAL);
        urgencies.add(Urgency.HIGH);
        urgencies.add(Urgency.MEDIUM);
        urgencies.add(Urgency.LOW);
        return JsonRestUtils.toJsonResponse(urgencies);
    }

}
