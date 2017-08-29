package ua.usstandart.avayastatformer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ua.usstandart.avayastatformer.service.StatsFormer;

import java.util.Date;


@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    StatsFormer statsFormer;

    @CrossOrigin
    @RequestMapping(method= RequestMethod.GET, value="/api/stat/1.0/get")
    public Object getStats (@RequestParam(value = "number")String number, @RequestParam(value = "heading")String heading, @RequestParam(value = "date") @DateTimeFormat(pattern="yyyy-MM-dd") Date date){
        return statsFormer.getStats(number,heading,date);
    }
}
