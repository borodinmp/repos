package home.service;

import home.domain.Message;
import home.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindService {

    private Iterable<Message> messages;

    @Autowired
    private MessageRepo messageRepo;

    public Iterable find (String filter, String selectFilter) {

        if(filter != null & !filter.isEmpty() & selectFilter.equals("1")) {
            messages = messageRepo.
                    findByNameOrgLikeOrInnLikeOrOgrnnLikeOrAddressOrgLike(filter, filter, filter, filter);

        } else if(filter != null & !filter.isEmpty() & selectFilter.equals("2")) {
            messages = messageRepo.
                    findByNameOrg(filter);

        } else if(filter != null & !filter.isEmpty() & selectFilter.equals("3")) {
            messages = messageRepo.
                    findByInn(filter);

        } else if(filter != null & !filter.isEmpty() & selectFilter.equals("4")) {
            messages = messageRepo.
                    findByOgrnn(filter);

        } else if(filter != null & !filter.isEmpty() & selectFilter.equals("5")) {
            messages = messageRepo.
                    findByAddressOrg(filter);

        } else messages = messageRepo.findAll();

        return messages;
    }

    public Iterable<Message> getMessages() {
        return messages;
    }

}
