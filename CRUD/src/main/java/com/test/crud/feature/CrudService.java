package com.test.crud.feature;

import com.fasterxml.jackson.databind.node.ObjectNode;
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
import java.util.List;

@Service
@Transactional
@Validated
public class CrudService {

    @Autowired Utils utils;

    @Autowired
    IncidentRepository incidentRepository;

    // CREATE METHODS

    public ObjectNode  NewIncident (ObjectNode json){
        IncidentEntity incident = new IncidentEntity();
        incident.setName(json.get("name").asText());
        incident.setDescription(json.get("description").asText());
        utils.fillInDates(incident);
        incidentRepository.save(incident);
        return utils.SuccesMessage();
    }

    public ObjectNode  NewFullIncident (ObjectNode json) throws ParseException {
        IncidentEntity incident = new IncidentEntity();
        incident.setName(json.get("name").asText());
        incident.setDescription(json.get("description").asText());
        incident.setCreatedAt(utils.StringToCalendar(json.get("createdAt").asText()));
        incident.setUpdatedAt(utils.StringToCalendar(json.get("updatedAt").asText()));
        incident.setClosedAt(utils.StringToCalendar(json.get("closedAt").asText()));
        incidentRepository.save(incident);
        return utils.SuccesMessage();
    }

    // UPDATE METHODS

    public ObjectNode UpdateIncident (ObjectNode json){
        IncidentEntity incident = incidentRepository.findByIdIncident(Long.parseLong(json.get("idIncident").asText()));
        incident.setName(json.get("name").asText());
        incident.setDescription(json.get("description").asText());
        utils.fillInDates(incident);
        incidentRepository.save(incident);
        return utils.SuccesMessage();
    }

    public ObjectNode UpdateFullIncident (ObjectNode json) throws ParseException {
        IncidentEntity incident = incidentRepository.findByIdIncident(Long.parseLong(json.get("idIncident").asText()));
        incident.setName(json.get("name").asText());
        incident.setDescription(json.get("description").asText());
        incident.setCreatedAt(utils.StringToCalendar(json.get("createdAt").asText()));
        incident.setUpdatedAt(utils.StringToCalendar(json.get("updatedAt").asText()));
        incident.setClosedAt(utils.StringToCalendar(json.get("closedAt").asText()));
        incidentRepository.save(incident);
        return utils.SuccesMessage();
    }

    // DELETE METHODS
    public ObjectNode SoftDeleteIncident (Long idIncident){
        IncidentEntity incident = incidentRepository.findByIdIncident(idIncident);
        incident.setClosedAt(utils.getCurrentDate());
        incidentRepository.save(incident);
        return utils.SuccesMessage();
    }

    public ObjectNode HardDeleteIncident (Long idIncident){
        IncidentEntity incident = incidentRepository.findByIdIncident(idIncident);
        incidentRepository.delete(incident);
        return utils.SuccesMessage();
    }

    // READ METHODS
    public ObjectNode SearchIncident (Long idIncident) throws IOException {
        IncidentEntity incident = incidentRepository.findByIdIncident(idIncident);
        ObjectNode jsonIncident = utils.ObjectToMap(incident);
        return utils.FormatDate(incident, jsonIncident);
    }

    public List<ObjectNode> ListAllIncidents () throws IOException {
        List<IncidentEntity> incident = incidentRepository.findAllByIdIncidentIsNotNullAndClosedAtIsNull();
        List<ObjectNode> incidentsList = new ArrayList<>();
        for (IncidentEntity incidentEntity : incident) {
            ObjectNode jsonIncident = utils.ObjectToMap(incidentEntity);
            incidentsList.add(utils.FormatDate(incidentEntity, jsonIncident));
        }
        return incidentsList;
    }

    public List<ObjectNode> ListRecentIncidents () throws IOException {
        List<IncidentEntity> incident = incidentRepository.findAllByIdIncidentIsNotNullAndClosedAtIsNullOrderByIdIncidentDesc().stream().limit(20).toList();
        List<ObjectNode> incidentsList = new ArrayList<>();
        for (IncidentEntity incidentEntity : incident) {
            ObjectNode jsonIncident = utils.ObjectToMap(incidentEntity);
            incidentsList.add(utils.FormatDate(incidentEntity, jsonIncident));
        }
        return incidentsList;
    }

}
