package home.controller;

import home.domain.Message;
import home.domain.User;
import home.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

    @Controller
    public class MainController {

        @Autowired
        private MessageRepo messageRepo;

        @GetMapping("/")
        public String greeting(Map<String,Object> model) {
                       return "greeting";
            }

        @GetMapping("/main")
        public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
            Iterable<Message> messages = messageRepo.findAll();

            if(filter != null && !filter.isEmpty()) {

                messages = messageRepo.
                        findByNameOrgLikeOrInnLikeOrOgrnnLikeOrAddressOrgLike(filter,filter,filter,filter);
            } else {
                messages = messageRepo.findAll();
            }

            model.addAttribute("messages", messages);
            model.addAttribute("filter", filter);
            return "main";
            }

        @PostMapping("text")
        public String add(
                @AuthenticationPrincipal User user,
                @RequestParam String nameOrg,
                @RequestParam String inn,
                @RequestParam String ogrnn,
                @RequestParam String addressOrg, Map <String,Object> model) {
            Message message = new Message(nameOrg, inn, ogrnn, addressOrg, user);
            messageRepo.save(message);

            Iterable<Message> messages = messageRepo.findAll();

            model.put("messages", messages);

            return "main";
        }


        @PostMapping("delete")
        @Transactional
        public String delete(@RequestParam Integer id, Map<String,Object> model) {

            Iterable<Message> messages;

            messageRepo.deleteById(id);
            messages = messageRepo.findAll();
            model.put("messages", messages);
            return "main";
            }

    }

