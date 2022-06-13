package com.arnur.email_spammer.controller;

import com.arnur.email_spammer.dto.ClientDto;
import com.arnur.email_spammer.dto.MessageDto;
import com.arnur.email_spammer.service.ClientService;
import com.arnur.email_spammer.service.GroupService;
import com.arnur.email_spammer.service.MessageService;
import com.arnur.email_spammer.dto.GroupDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MyController {
    @Autowired
    private ClientService clientService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private MessageService messageService;

    @RequestMapping("/")
    public String showAllClients(Model model) {

        List<ClientDto> allClientsDto = clientService.getAllClients();
        model.addAttribute("allClns", allClientsDto);
        return "all-clients";
    }

    @RequestMapping("/addNewClient")
    public String addNewClient(Model model) {
        ClientDto clientDto = new ClientDto();
        model.addAttribute("client", clientDto);

        return "add-client";
    }

    @RequestMapping("/saveClient")
    public String saveClient(@ModelAttribute("client") ClientDto clientDto, Model model) {
        boolean noException = clientService.saveClient(clientDto);

        if (!noException) {
            String exception = "Client email already exists";
            model.addAttribute("exception", exception);
            return "add-client";
        }

        return "redirect:/";
    }

    @RequestMapping("/persistClient")
    public String persistClient(@ModelAttribute("client") ClientDto clientDto, Model model) {

        boolean noException = clientService.persistClient(clientDto);

        if (!noException) {
            String exception = "Client email already exists";
            model.addAttribute("exception", exception);
            return "add-client";
        }

        return "redirect:/";
    }

    @RequestMapping("/updateInfo/{id}")
    public String updateClient(@PathVariable("id") int id, Model model) {

        ClientDto clientDto = clientService.getClient(id);
        System.out.println(clientDto.getGroups());
        model.addAttribute("client", clientDto);

        return "update-client";
    }

    @RequestMapping("/deleteClient/{id}")
    public String deleteClient(@PathVariable("id") int id, Model model) {

        clientService.deleteClient(id);

        return "redirect:/";
    }

    @RequestMapping("/searchClient")
    public String searchClients(@RequestParam("name") String name, Model model) {
        List<ClientDto> allClientsDto = clientService.searchClient(name);
        model.addAttribute("allClns", allClientsDto);
        return "all-clients";
    }

    @RequestMapping("/groups")
    public String showAllGroups(Model model) {
        List<GroupDto> allGroupsDto = groupService.getAllGroups();
        model.addAttribute("allGrs", allGroupsDto);
        for (GroupDto group : allGroupsDto) {
            System.out.println(group.getMessages());
        }
        return "all-groups";
    }

    @RequestMapping("/addNewGroup")
    public String addNewGroup(Model model) {
        GroupDto groupDto = new GroupDto();
        model.addAttribute("group", groupDto);

        return "add-group";
    }

    @RequestMapping("/saveGroup")
    public String saveGroup(@ModelAttribute("group") GroupDto groupDto, Model model) {

        boolean noException = groupService.saveGroup(groupDto);

        if (!noException) {
            String exception = "Group name already exists";
            model.addAttribute("exception", exception);
            return "group-info";
        }

        return "redirect:/groups";
    }

    @RequestMapping("/updateGroup/{id}")
    public String addClientToGroup(@PathVariable("id") int id, Model model) {

        GroupDto groupDto = groupService.getGroup(id);
        List<ClientDto> clientsDto = groupService.getAllClients(id);

        model.addAttribute("group", groupDto);
        model.addAttribute("clients", clientsDto);

        return "group-info";
    }

    @RequestMapping("/searchGroup")
    public String searchGroups(@RequestParam("name") String name, Model model) {

        List<GroupDto> allGroupsDto = groupService.searchGroup(name);
        model.addAttribute("allGrs", allGroupsDto);
        return "all-groups";
    }

    @RequestMapping("/deleteClientFromGroup/{id1}/{id2}")
    public String deleteClient(@PathVariable("id1") int id,@PathVariable("id2") int cid, Model model) {

        ClientDto clientDto = clientService.getClient(cid);
        groupService.removeClient(id, clientDto);
        return "redirect:/updateGroup/" + id;
    }

    @RequestMapping("/addClientToGroup/{id}")
    public String ClientToGroup(@PathVariable("id") int id, Model model) {

        GroupDto groupDto = groupService.getGroup(id);
        List<ClientDto> clientsDto = groupService.getAllClients(id);
        List<ClientDto> finalClientsDto = clientService.exceptThem(clientsDto);
        if (finalClientsDto.size() == 0) {
            return "redirect:/updateGroup/" + id;
        }
        model.addAttribute("group", groupDto);
        model.addAttribute("clients", finalClientsDto);

        return "add-clients-to-group";
    }

    @RequestMapping("/addClientToGroup/{id}/{cid}")
    public String addClientToGroup(@PathVariable("id") int id,@PathVariable("cid") int cid, Model model) {

        ClientDto clientDto = clientService.getClient(cid);

        groupService.addClient(id,clientDto);

        return "redirect:/addClientToGroup/"+id;
    }


    @RequestMapping("/deleteGroup/{id}")
    public String deleteGroup(@PathVariable("id") int id, Model model) {

        groupService.deleteGroup(id);

        return "redirect:/groups";
    }

    @RequestMapping("/spam")
    public String spam(Model model) {
        List<GroupDto> groupsDto = groupService.getAllGroupsWithClients();
        MessageDto messageDto = new MessageDto();

        model.addAttribute("groups", groupsDto);
        model.addAttribute("message", messageDto);

        return "send-message";
    }

    @RequestMapping("/sendMessage")
    public String sendMessage(@ModelAttribute("message") MessageDto messageDto, Model model) {

        boolean NoException = groupService.sendEmail(messageDto.getId(), messageDto.getSubject(), messageDto.getSubject());

        if(!NoException) {
            String exception = "Failed to connect";
            model.addAttribute("exception", exception);
            return "send-message";
        }
        messageService.saveMessage(groupService.getGroup(messageDto.getId()), messageDto.getSubject(), messageDto.getSubject());
        return "redirect:/messages";
    }

    @RequestMapping("/messages")
    public String showAllMessages(Model model) {

        List<MessageDto> messagesDto = messageService.getAllMessages();
        model.addAttribute("allMsgs", messagesDto);
        return "all-messages";
    }

    @RequestMapping("/messages/{id}")
    public String showMessageText(@PathVariable("id") int id, Model model) {

        MessageDto messageDto = messageService.getMessage(id);
        model.addAttribute("text",messageDto.getText());
        return "text";
    }

    @RequestMapping("/deleteMessage/{id}")
    public String deleteMessage(@PathVariable("id") int id, Model model) {

        messageService.deleteMessage(id);

        return "redirect:/messages";
    }

    @RequestMapping("/resendMessage/{id}")
    public String resendMessage(@PathVariable("id") int id, Model model) {

        List<GroupDto> groupsDto = groupService.getAllGroupsWithClients();
        MessageDto messageDto = messageService.getMessage(id);

        model.addAttribute("groups", groupsDto);
        model.addAttribute("message", messageDto);

        return "send-message";
    }
}
