package ru.gusev.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gusev.dao.DevDAO;

import java.sql.SQLException;

@Controller
@RequestMapping("/statistic")
public class DevControllers {

    private final DevDAO devDAO;

    @Autowired
    public DevControllers(DevDAO devDAO) {
        this.devDAO = devDAO;
    }

    @GetMapping()
    public String watch(Model model) throws SQLException {
        model.addAttribute("devDAO", devDAO.index());
        return "people/statistic";
    }
    @DeleteMapping()
    public String delete(){
        devDAO.delete();
        return "redirect:/people";
    }
}
