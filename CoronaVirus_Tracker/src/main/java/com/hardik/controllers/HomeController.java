package com.hardik.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hardik.models.LocationStats;
import com.hardik.service.CoronaVirusDataService;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/")
    public String home(Model model) {
    	
        List<LocationStats> allStats = coronaVirusDataService.getAllStats();
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalNewCases", totalNewCases);

//        return "home";
        return "Dashboard";
    }
    @GetMapping("/areaChart")
    public String areaChart(Model model) {
    	return "areaChart";
    }
    
    @GetMapping("/barChart")
    public String barChart(Model model) {
    	return "barChart";
    }
    
    @GetMapping("/pieChart")
    public String pieChart(Model model) {
    	return "pieChart";
    }
    
    @GetMapping("/timeLine")
    public String timeLine(Model model) {
    	return "timeLine";
    }
}
