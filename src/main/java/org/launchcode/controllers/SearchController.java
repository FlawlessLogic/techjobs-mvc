package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.launchcode.controllers.ListController.columnChoices;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";

    }

    @RequestMapping(value = "results", method = RequestMethod.GET)
    public String results(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        int number = 0;
        if (searchType.equals("all")) {
            ArrayList<HashMap<String, String>> jobs = JobData.findByValue(searchTerm);
            model.addAttribute("title", "All Jobs");
            model.addAttribute("jobs", jobs);
            model.addAttribute("columns", columnChoices);
            for (HashMap<String, String> job : jobs) {
                number++;
            }
            String results = Integer.toString(number) + " Result(s)";
            model.addAttribute("results", results);
            return "search";
        } else {
            ArrayList<HashMap<String, String>> jobs = JobData.findByColumnAndValue(searchType, searchTerm);
            model.addAttribute("title", "All Jobs");
            model.addAttribute("jobs", jobs);
            model.addAttribute("columns", columnChoices);
            for (HashMap<String, String> job : jobs) {
                number++;
            }
            String results = Integer.toString(number) + " Result(s)";
            model.addAttribute("results", results);
            return "search";
        }

        // TODO #1 - Create handler to process search request and display results

    }
}