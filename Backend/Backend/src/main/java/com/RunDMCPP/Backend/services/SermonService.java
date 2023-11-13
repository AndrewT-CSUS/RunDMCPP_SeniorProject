package com.RunDMCPP.Backend.services;

import com.RunDMCPP.Backend.enums.ErrorEnum;
import com.RunDMCPP.Backend.models.Sermon;
import com.RunDMCPP.Backend.repositories.SermonRepository;
import com.RunDMCPP.Backend.utils.BackendErrorException;
import com.RunDMCPP.Backend.validation.SermonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class SermonService {

    @Autowired
    private SermonRepository sermonRepository;
    @Autowired
    private SermonValidator sermonValidator;

    public Iterable<Sermon> findAll() {
        return sermonRepository.findAll();
    }

    public Optional<Sermon> findById(String id) throws BackendErrorException {
        Optional<Sermon> dbEntity = sermonRepository.findById(id);
        if(dbEntity.isPresent()){
            return dbEntity;
        }
        throw new BackendErrorException(ErrorEnum.NOT_FOUND);
    }

    public Sermon createSermon(Sermon sermon) throws BackendErrorException {
        if (sermonValidator.createValidator(sermon)) {
            try {
                return sermonRepository.save(sermon);
            } catch (Exception e) {
                throw new BackendErrorException(ErrorEnum.TRANSACTION_FAIL);
            }
        }
        throw new BackendErrorException(ErrorEnum.INVALID_INPUT);
    }


    public Sermon editSermon(Sermon sermon) throws BackendErrorException {
        if (!sermonValidator.readValidator(sermon)) {
            throw new BackendErrorException(ErrorEnum.INVALID_INPUT);
        }
        Optional<Sermon> dbEntity = sermonRepository.findById(sermon.getId());

        if (dbEntity.isPresent()) {
            if (sermonValidator.updateValidator(sermon, dbEntity.get())) {
                if (sermon.getName() != null) {
                    dbEntity.get().setName(sermon.getName());
                }
                if (sermon.getDescription() != null) {
                    dbEntity.get().setDescription(sermon.getDescription());
                }
                if (sermon.getDateTime() != null) {
                    dbEntity.get().setDateTime(sermon.getDateTime());
                }

                if (sermon.getYoutubeLink() != null) {
                    dbEntity.get().setYoutubeLink(sermon.getYoutubeLink());
                }

                try {
                    return sermonRepository.save(dbEntity.get());
                } catch (Exception e) {
                    throw new BackendErrorException(ErrorEnum.TRANSACTION_FAIL);
                }
            }
            throw new BackendErrorException(ErrorEnum.DATA_MISMATCH);
        }
        throw new BackendErrorException(ErrorEnum.NOT_FOUND);
    }

    public void deleteSermon(Sermon sermon) throws BackendErrorException {
        if (!sermonValidator.readValidator(sermon)) {
            throw new BackendErrorException(ErrorEnum.INVALID_INPUT);
        }
        Optional<Sermon> dbEntity = sermonRepository.findById(sermon.getId());
        if (dbEntity.isPresent()) {
            if (sermonValidator.deleteValidator(sermon, dbEntity.get())) {
                try {
                    sermonRepository.deleteById(sermon.getId());
                } catch (Exception e) {
                    throw new BackendErrorException(ErrorEnum.TRANSACTION_FAIL);
                }
            } else {
                throw new BackendErrorException(ErrorEnum.DATA_MISMATCH);
            }
        } else {
            throw new BackendErrorException(ErrorEnum.NOT_FOUND);
        }
    }

    public List<Sermon> searchSermonsByTitle(String title) throws BackendErrorException {
        List<Sermon> results = sermonRepository.findByNameContaining(title);
        if(results.isEmpty()){
            throw new BackendErrorException(ErrorEnum.NOT_FOUND);
        }
        return results;
    }

    public List<Sermon> searchSermonsByDateRange(String startDate, String endDate) throws BackendErrorException {
        List<Sermon> results = sermonRepository.findByDateTimeBetween(startDate, endDate);
        if(results.isEmpty()){
            throw new BackendErrorException(ErrorEnum.NOT_FOUND);
        }
        return results;
    }
}
