package home.controller;


import home.domain.Message;
import home.domain.User;
import home.repos.MessageRepo;
import org.hibernate.sql.SelectValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
    public class MainController {


        @Autowired
        private MessageRepo messageRepo;

        @Value("${upload.path}")
        private String uploadPath;

        @GetMapping("/")
        public String greeting(Map<String,Object> model) {
                       return "greeting";
            }

        @GetMapping("/main")
        public String main(@RequestParam(required = false, defaultValue = "") String filter,
                           /*@RequestParam(required = false, defaultValue = "") SelectValues orgFilter,*/
                           Model model) {
            Iterable<Message> messages = messageRepo.findAll();


            if(filter != null & !filter.isEmpty()/* & orgFilter.equals("orgName")*/) {

                messages = messageRepo.
                        findByNameOrg(filter);
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
                @RequestParam("file") MultipartFile file,
                @Valid Message message,
                BindingResult bindingResult,
                Model model) throws IOException {
            message.setAuthor(user);

            if(bindingResult.hasErrors()) {
                Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
                model.mergeAttributes(errorsMap);
                model.addAttribute("message", message);
            } else {

                if (file != null && !file.getOriginalFilename().isEmpty()) {
                    File uploadDir = new File(uploadPath);
                    if (!uploadDir.exists()) {
                        uploadDir.mkdir();
                    }
                    String uuidFile = UUID.randomUUID().toString();
                    String resultFilename = uuidFile + "." + file.getOriginalFilename();
                    file.transferTo(new File(uploadPath + "/" + resultFilename));
                    message.setFilename(resultFilename);
                }

                model.addAttribute("message" , null);

                messageRepo.save(message);
            }

            Iterable<Message> messages = messageRepo.findAll();

            model.addAttribute("messages", messages);

            return "main";
        }



    @PostMapping("delete")
        @Transactional
        public String delete(@RequestParam Long id, Map<String,Object> model) {

            Iterable<Message> messages;

            messageRepo.deleteById(id);
            messages = messageRepo.findAll();
            model.put("messages", messages);
            return "main";
            }

    }
