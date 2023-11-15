package com.test.crud.feature;

import com.test.crud.feature.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1/crud")
public class CrudController {

    @Autowired CrudService service;
    @Autowired Utils utils;


    // CREATE REQUESTS

    @PostMapping("/create-incident")
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String, Object> NewIncident(@RequestBody HashMap<String, String> json) {
        try {
            return service.NewIncident(json);
        } catch (Exception e){
            return utils.ErrorMessage();
        }
    }

    @PostMapping("/create-full-incident")
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String, Object> NewFullIncident(@RequestBody HashMap<String, String> json) {
        try {
            return service.NewFullIncident(json);
        } catch (Exception e){
            return utils.ErrorMessage();
        }
    }

    // READ REQUESTS

    @GetMapping("/search-incident")
    @ResponseStatus(HttpStatus.OK)
    public HashMap<String, Object> SearchIncident(@RequestParam Long idIncident){
        try {
            return service.SearchIncident(idIncident);
        } catch (Exception e){
            return utils.ErrorMessage();
        }
    }

    @GetMapping("/list-all-incidents")
    @ResponseStatus(HttpStatus.OK)
    public List<HashMap<String, Object>> ListIncidents() throws IOException {
        try {
            return service.ListAllIncidents();
        } catch (Exception e){
            List<HashMap<String, Object>> returnList = new ArrayList<>();
            returnList.add(utils.ErrorMessage());
            return returnList;
        }
    }

    @GetMapping("/list-recent-incidents")
    @ResponseStatus(HttpStatus.OK)
    public List<HashMap<String, Object>> ListRecentIncidents(){
        try {
            return service.ListRecentIncidents();
        } catch (Exception e){
            List<HashMap<String, Object>> returnList = new ArrayList<>();
            returnList.add(utils.ErrorMessage());
            return returnList;
        }
    }

    // UPDATE REQUESTS

    @PutMapping("/update-incident")
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String, Object> UpdateIncident(@RequestBody HashMap<String, String> json) {
        try {
            return service.UpdateIncident(json);
        } catch (Exception e){
            return utils.ErrorMessage();
        }
    }

    @PutMapping("/update-full-incident")
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String, Object> UpdateFullIncident(@RequestBody HashMap<String, String> json) {
        try {
            return service.UpdateFullIncident(json);
        } catch (Exception e){
            return utils.ErrorMessage();
        }
    }

    // DELETE REQUESTS

    @PutMapping("/soft-delete-incident")
    @ResponseStatus(HttpStatus.OK)
    public HashMap<String, Object> SoftDeleteIncident(@RequestParam Long idIncident) {
        try {
            return service.SoftDeleteIncident(idIncident);
        } catch (Exception e){
            return utils.ErrorMessage();
        }
    }

    @DeleteMapping("/hard-delete-incident")
    @ResponseStatus(HttpStatus.OK)
    public HashMap<String, Object> HardDeleteIncident(@RequestParam Long idIncident) {
        try {
            return service.HardDeleteIncident(idIncident);
        } catch (Exception e){
            return utils.ErrorMessage();
        }
    }

}
