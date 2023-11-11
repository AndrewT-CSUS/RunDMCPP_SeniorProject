package com.RunDMCPP.Backend.services;
import com.RunDMCPP.Backend.enums.SermonEnum;
import com.RunDMCPP.Backend.models.Sermon;
import com.RunDMCPP.Backend.repositories.SermonRepository;
import com.RunDMCPP.Backend.controllers.SermonController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SermonService {

    @Autowired
    private SermonRepository sermonRepository;

    public Optional<Sermon> findById(String id) {
        return sermonRepository.findById(id);
    }

    public Sermon createSermon(Sermon s) {
        //Maybe do some validation here on the input sermon
        Sermon result;
        try {
            result = sermonRepository.save(s);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, SermonEnum.TRANSACTION_FAIL_E.getValue());
        }
        return result;
    }

    public List<Sermon> findAll() {
        Iterable<Sermon> sermons = sermonRepository.findAll();
        List<Sermon> sermonList = new ArrayList<>();
        sermons.forEach(sermonList::add);
        return sermonList;
    }

    public Sermon editSermon(Sermon s) {
        //Validate the inputs later on
        Optional<Sermon> dbEntity = sermonRepository.findById(s.getId());

        if (dbEntity.isPresent()) {
            try {
                if (s.getName() != null) {
                    dbEntity.get().setName(s.getName());
                }
                if (s.getDescription() != null) {
                    dbEntity.get().setDescription(s.getDescription());
                }
                if (s.getDateTime() != null) {
                    dbEntity.get().setDateTime(s.getDateTime());
                }

                if (s.getYoutubeLink() != null) {
                    dbEntity.get().setYoutubeLink(s.getYoutubeLink());
                }

                return sermonRepository.save(dbEntity.get());
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, SermonEnum.TRANSACTION_FAIL_E.getValue());
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, SermonEnum.NOT_FOUND_E.getValue());
        }
    }

    public void deleteSermon(Sermon s) {
        Optional<Sermon> dbEntity = sermonRepository.findById(s.getId());
        if (dbEntity.isPresent()) {
            if (dbEntity.get().equals(s)) {

                sermonRepository.deleteById(s.getId());
            } else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, SermonEnum.DATA_MISMATCH.getValue());
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, SermonEnum.NOT_FOUND_E.getValue());
        }
    }
    
    public List<Sermon> searchSermonsByTitle(String title) {
        return sermonRepository.findByNameContaining(title);
    }

    public List<Sermon> searchSermonsByDateRange(String startDate, String endDate) {
        return sermonRepository.findByDateTimeBetween(startDate, endDate);
    }
}
