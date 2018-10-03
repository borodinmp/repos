package home;

import home.domain.Message;
import home.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

    @Controller
    public class GreetingController {

        @Autowired
        private MessageRepo messageRepo;

        @GetMapping("/")
        public String greeting(
            @RequestParam(name="name", required=false, defaultValue="World") String name,
            Map<String,Object> model) {
            model.put("name", name);
            return "greeting.mustache";
            }

        @GetMapping("/main")
        public String main(Map<String,Object> model) {
            Iterable<Message> messages = messageRepo.findAll();
            model.put("messages", messages);
            return "main";
            }

        @PostMapping("/main")
        public String add(@RequestParam String nameOrg, @RequestParam String inn, @RequestParam String ogrnn,
                          @RequestParam String addressOrg, Map<String,Object> model) {
            Message message = new Message(nameOrg, inn, ogrnn, addressOrg);
            messageRepo.save(message);

            Iterable<Message> messages = messageRepo.findAll();
            model.put("messages", messages);
            return "main";
        }

        @PostMapping("filter")
        public String filter(@RequestParam String filter, Map<String,Object> model) {
            Iterable<Message> messages;

            if(filter != null && !filter.isEmpty()) {
                messages = messageRepo.
                        findByNameOrgLikeOrInnLikeOrOgrnnLikeOrAddressOrgLike(filter,filter,filter,filter);
            } else {
                messages = messageRepo.findAll();
            }
            model.put("messages", messages);
            return "main";
            }

        @DeleteMapping("delete")
        public String delete(@RequestParam Integer id, Map<String,Object> model) {

            Iterable<Message> messages;

            messages = messageRepo.deleteById(id);
            model.remove("messages", messages);
            return "main";
            }

    }

