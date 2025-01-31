package com.example.AppAziendale.services;

import com.example.AppAziendale.domains.Entities.ComunicazioneAziendaleScheduled;
import com.example.AppAziendale.domains.dto.requests.CreateComunicazioneAziendaleRequest;
import com.example.AppAziendale.domains.dto.requests.CreateComunicazioneAziendaleScheduledRequest;
import com.example.AppAziendale.domains.dto.requests.UpdateComunicazioneAziendaleRequest;
import com.example.AppAziendale.domains.dto.responses.ComunicazioneAziendaleScheduledResponse;
import com.example.AppAziendale.domains.dto.responses.EntityIdResponse;
import com.example.AppAziendale.domains.exceptions.MyEntityNotFoundException;
import com.example.AppAziendale.mappers.ComunicazioneAziendaleMapper;
import com.example.AppAziendale.repositories.ComunicazioneAziendaleScheduledRepository;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class ComunicazioneAziendaleScheduledService implements Job {

    @Autowired
    private ComunicazioneAziendaleScheduledRepository comunicazioneAziendaleScheduledRepository;
    @Autowired
    private ComunicazioneAziendaleService comunicazioneAziendaleService;

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private ComunicazioneAziendaleMapper comunicazioneAziendaleMapper;

    @Autowired
    private Scheduler scheduler;

    public ComunicazioneAziendaleScheduled getById(Long id) throws MyEntityNotFoundException {
        return comunicazioneAziendaleScheduledRepository.findById(id)
                .orElseThrow(() -> new MyEntityNotFoundException("ComunicazioneAziendale con id " + id + " non trovata"));
    }

    public ComunicazioneAziendaleScheduledResponse getByIdWithResponse(Long id) throws MyEntityNotFoundException {
        return comunicazioneAziendaleMapper.toComunicazioneAziendaleScheduledResponse(comunicazioneAziendaleScheduledRepository
                .findById(id)
                .orElseThrow(() -> new MyEntityNotFoundException("ComunicazioneAziendale con id " + id + " non esiste!")));
    }

    public List<ComunicazioneAziendaleScheduled> getAll() {
        return comunicazioneAziendaleScheduledRepository.findAll();
    }

    public List<ComunicazioneAziendaleScheduledResponse> getAllWithResponse() {
        return comunicazioneAziendaleScheduledRepository.findAll()
                .stream()
                .map(id -> {
                    try {
                        return comunicazioneAziendaleMapper.toComunicazioneAziendaleScheduledResponse(id);
                    } catch (MyEntityNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toList();
    }


    public EntityIdResponse createComunicazioneAziendaleScheduled(CreateComunicazioneAziendaleScheduledRequest request) throws MyEntityNotFoundException, SchedulerException {
        ComunicazioneAziendaleScheduled comunicazioneAziendaleScheduled = comunicazioneAziendaleMapper.fromCreateComunicazioneAziendaleScheduledRequestToComunicazioneAziendaleScheduled(request);
        comunicazioneAziendaleScheduledRepository.save(comunicazioneAziendaleScheduled);
        CreateComunicazioneAziendaleRequest createComunicazioneAziendaleRequest = comunicazioneAziendaleMapper.fromCreateComunicazioneAziendaleScheduledRequestToCreateComunicazioneAziendaleRequest(request);
        // crea il job per lo schedule della transazione
        JobDetail jobDetail = buildJobDetail(comunicazioneAziendaleScheduled, createComunicazioneAziendaleRequest);
        Trigger trigger = buildJobTrigger(jobDetail, Date.from(comunicazioneAziendaleScheduled.getPublishTime().atZone(ZoneId.systemDefault()).toInstant()));
        scheduler.scheduleJob(jobDetail, trigger);
        return EntityIdResponse.builder().id(comunicazioneAziendaleScheduled.getId()).build();
    }

    public EntityIdResponse updateComunicazioneAziendaleScheduled(Long id, UpdateComunicazioneAziendaleRequest request) throws MyEntityNotFoundException {
        ComunicazioneAziendaleScheduled news = getById(id);
        if (request.titolo() != null) news.setTitolo(request.titolo());
        if (request.contenuto() != null) news.setContenuto(request.contenuto());
        return new EntityIdResponse(comunicazioneAziendaleScheduledRepository.save(news).getId());
    }

    public void deleteById(Long id) throws MyEntityNotFoundException {
        if (!comunicazioneAziendaleScheduledRepository.existsById(id)) {
            throw new MyEntityNotFoundException("News con id " + id + " non trovata");
        }
        comunicazioneAziendaleScheduledRepository.deleteById(id);
    }

    private Trigger buildJobTrigger(JobDetail jobDetail, Date publishTime) {

        return TriggerBuilder
                .newTrigger()
                .forJob(jobDetail)
                .startAt(publishTime)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule())
                .build();

    }

    private JobDetail buildJobDetail(ComunicazioneAziendaleScheduled comunicazioneAziendaleScheduled,
                                     CreateComunicazioneAziendaleRequest comunicazioneAziendaleRequest) {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("entityData", comunicazioneAziendaleRequest); // ---> l'entità che passerò all'execute
        jobDataMap.put("id", comunicazioneAziendaleScheduled.getId()); // ---> l'id del job
        return JobBuilder
                .newJob(ComunicazioneAziendaleScheduledService.class)
                .withIdentity(String.valueOf(comunicazioneAziendaleScheduled.getId()), "comunicazioneAziendaleScheduled")
                .storeDurably()
                .setJobData(jobDataMap)
                .build();
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();
        CreateComunicazioneAziendaleRequest request = (CreateComunicazioneAziendaleRequest) jobDataMap.get("entityData");
        Long id_scheduled = jobDataMap.getLongValue("id");
        try {
            comunicazioneAziendaleService.createComunicazione(request);
        } catch (MyEntityNotFoundException e) {
            throw new RuntimeException(e);
        }
        comunicazioneAziendaleScheduledRepository.deleteById(id_scheduled);
    }
}