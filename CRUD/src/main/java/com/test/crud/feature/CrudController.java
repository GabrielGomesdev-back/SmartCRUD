package com.test.crud.feature;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.test.crud.feature.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/crud")
public class CrudController {

    @Autowired CrudService service;
    @Autowired Utils utils;


    // CREATE REQUESTS

    @PostMapping("/create-incident")
    @ResponseStatus(HttpStatus.CREATED)
    public ObjectNode NewIncident(@RequestBody ObjectNode json) {
        try {
            return service.NewIncident(json);
        } catch (Exception e){
            return utils.ErrorMessage();
        }
    }

    @PostMapping("/create-full-incident")
    @ResponseStatus(HttpStatus.CREATED)
    public ObjectNode NewFullIncident(@RequestBody ObjectNode json) {
        try {
            return service.NewFullIncident(json);
        } catch (Exception e){
            return utils.ErrorMessage();
        }
    }

    // READ REQUESTS

    @GetMapping("/search-incident")
    @ResponseStatus(HttpStatus.OK)
    public ObjectNode SearchIncident(@RequestParam Long idIncident){
        try {
            return service.SearchIncident(idIncident);
        } catch (Exception e){
            return utils.ErrorMessage();
        }
    }

    @GetMapping("/list-all-incidents")
    @ResponseStatus(HttpStatus.OK)
    public List<ObjectNode> ListIncidents() throws IOException {
        try {
            return service.ListAllIncidents();
        } catch (Exception e){
            List<ObjectNode> returnList = new ArrayList<>();
            returnList.add(utils.ErrorMessage());
            return returnList;
        }
    }

    @GetMapping("/list-recent-incidents")
    @ResponseStatus(HttpStatus.OK)
    public List<ObjectNode> ListRecentIncidents(){
        try {
            return service.ListRecentIncidents();
        } catch (Exception e){
            List<ObjectNode> returnList = new ArrayList<>();
            returnList.add(utils.ErrorMessage());
            return returnList;
        }
    }

    // UPDATE REQUESTS

    @PutMapping("/update-incident")
    @ResponseStatus(HttpStatus.CREATED)
    public ObjectNode UpdateIncident(@RequestBody ObjectNode json) {
        try {
            return service.UpdateIncident(json);
        } catch (Exception e){
            return utils.ErrorMessage();
        }
    }

    @PutMapping("/update-full-incident")
    @ResponseStatus(HttpStatus.CREATED)
    public ObjectNode UpdateFullIncident(@RequestBody ObjectNode json) {
        try {
            return service.UpdateFullIncident(json);
        } catch (Exception e){
            return utils.ErrorMessage();
        }
    }

    // DELETE REQUESTS

    @PutMapping("/soft-delete-incident")
    @ResponseStatus(HttpStatus.OK)
    public ObjectNode SoftDeleteIncident(@RequestParam Long idIncident) {
        try {
            return service.SoftDeleteIncident(idIncident);
        } catch (Exception e){
            return utils.ErrorMessage();
        }
    }

    @DeleteMapping("/hard-delete-incident")
    @ResponseStatus(HttpStatus.OK)
    public ObjectNode HardDeleteIncident(@RequestParam Long idIncident) {
        try {
            return service.HardDeleteIncident(idIncident);
        } catch (Exception e){
            return utils.ErrorMessage();
        }
    }

}
