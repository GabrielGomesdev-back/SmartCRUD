package com.test.crud.feature;

import com.test.crud.data.entity.IncidentEntity;
import com.test.crud.data.repository.IncidentRepository;
import com.test.crud.feature.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
@Validated
public class CrudService {

    @Autowired Utils utils;

    @Autowired
    IncidentRepository incidentRepository;

    // CREATE METHODS

    public HashMap<String, Object>  NewIncident (HashMap<String, String> json){
        IncidentEntity incident = new IncidentEntity();
        incident.setName(json.get("name"));
        incident.setDescription(json.get("description"));
        utils.fillInDates(incident);
        incidentRepository.save(incident);
        return utils.SuccesMessage();
    }

    public HashMap<String, Object>  NewFullIncident (HashMap<String, String> json) throws ParseException {
        IncidentEntity incident = new IncidentEntity();
        incident.setName(json.get("name"));
        incident.setDescription(json.get("description"));
        incident.setCreatedAt(utils.StringToCalendar(json.get("createdAt")));
        incident.setUpdatedAt(utils.StringToCalendar(json.get("updatedAt")));
        incident.setUpdatedAt(utils.StringToCalendar(json.get("closedAt")));
        incidentRepository.save(incident);
        return utils.SuccesMessage();
    }

    // UPDATE METHODS

    public HashMap<String, Object> UpdateIncident (HashMap<String, String> json){
        IncidentEntity incident = incidentRepository.findByIdIncident(Long.parseLong(json.get("idIncident")));
        incident.setName(json.get("name"));
        incident.setDescription(json.get("description"));
        utils.fillInDates(incident);
        incidentRepository.save(incident);
        return utils.SuccesMessage();
    }

    public HashMap<String, Object> UpdateFullIncident (HashMap<String, String> json) throws ParseException {
        IncidentEntity incident = incidentRepository.findByIdIncident(Long.parseLong(json.get("idIncident")));
        incident.setName(json.get("name"));
        incident.setDescription(json.get("description"));
        incident.setCreatedAt(utils.StringToCalendar(json.get("createdAt")));
        incident.setUpdatedAt(utils.StringToCalendar(json.get("updatedAt")));
        incident.setUpdatedAt(utils.StringToCalendar(json.get("closedAt")));
        incidentRepository.save(incident);
        return utils.SuccesMessage();
    }

    // DELETE METHODS
    public HashMap<String, Object> SoftDeleteIncident (Long idIncident){
        IncidentEntity incident = incidentRepository.findByIdIncident(idIncident);
        incident.setClosedAt(utils.getCurrentDate());
        incidentRepository.save(incident);
        return utils.SuccesMessage();
    }

    public HashMap<String, Object> HardDeleteIncident (Long idIncident){
        IncidentEntity incident = incidentRepository.findByIdIncident(idIncident);
        incidentRepository.delete(incident);
        return utils.SuccesMessage();
    }

    // READ METHODS
    public HashMap<String, Object> SearchIncident (Long idIncident) throws IOException {
        IncidentEntity incident = incidentRepository.findByIdIncident(idIncident);
        HashMap<String, Object> jsonIncident = utils.ObjectToMap(incident);
        return utils.FormatDate(incident, jsonIncident);
    }

    public List<HashMap<String, Object>> ListAllIncidents () throws IOException {
        List<IncidentEntity> incident = incidentRepository.findAllByIdIncidentIsNotNullAndClosedAtIsNull();
        List<HashMap<String, Object>> incidentsList = new ArrayList<>();
        for (IncidentEntity incidentEntity : incident) {
            HashMap<String, Object> jsonIncident = utils.ObjectToMap(incidentEntity);
            incidentsList.add(utils.FormatDate(incidentEntity, jsonIncident));
        }
        return incidentsList;
    }

    public List<HashMap<String, Object>> ListRecentIncidents () throws IOException {
        List<IncidentEntity> incident = incidentRepository.findAllByIdIncidentIsNotNullAndClosedAtIsNullOrderByIdIncidentDesc().stream().limit(20).toList();
        List<HashMap<String, Object>> incidentsList = new ArrayList<>();
        for (IncidentEntity incidentEntity : incident) {
            HashMap<String, Object> jsonIncident = utils.ObjectToMap(incidentEntity);
            incidentsList.add(utils.FormatDate(incidentEntity, jsonIncident));
        }
        return incidentsList;
    }

}
