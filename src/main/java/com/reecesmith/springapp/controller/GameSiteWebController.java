package com.reecesmith.springapp.controller;

import com.reecesmith.springapp.Search;
import com.reecesmith.springapp.domain.GameSiteEntry;
import com.reecesmith.springapp.service.GameSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class GameSiteWebController
{
    private static final String GAMESITE_TEMPLATE = "gamesite";
    private static final String ENTRIES_TEMPLATE_ID = "entries";
    private static final String HOMEPAGE_REDIRECT = "redirect:/";
    private static final String NEW_ENTRY_TEMPLATE_ID = "newEntry";
    private static final String GAMESITE_FORM_HEADER_ID = "formHeader";
    private static final String SEARCH_VALUE = "value";
    private static final String PAGE_HEADER = "pageHeader";
    private static final String SEARCH_TEMPLATE = "searchResult";

    @Autowired
    private GameSiteService gameSiteService;

    @GetMapping("/")
    public String displayGameSite (Model model, Search search) {
        model.addAttribute (GAMESITE_FORM_HEADER_ID, "Add a New Score");
        model.addAttribute (ENTRIES_TEMPLATE_ID, this.gameSiteService.findAllEntries ());
        model.addAttribute (NEW_ENTRY_TEMPLATE_ID, new GameSiteEntry());
        model.addAttribute(SEARCH_VALUE,search);
        model.addAttribute(PAGE_HEADER, "Welcome home.");
        return GAMESITE_TEMPLATE;
    }

    @GetMapping ("/delete/{id}")
    public String deleteComment (@PathVariable Integer id) {
        this.gameSiteService.deleteGameSiteEntryById (id);
        return HOMEPAGE_REDIRECT;
    }

    @PostMapping("/")
    public String addComment (Model model, @Valid @ModelAttribute(NEW_ENTRY_TEMPLATE_ID)GameSiteEntry newEntry, BindingResult bindingResult, Search search) {
        if (!bindingResult.hasErrors ()) {
            this.gameSiteService.save (newEntry);
            return HOMEPAGE_REDIRECT;
        }
        else {
            model.addAttribute (GAMESITE_FORM_HEADER_ID, "Add a New Score");
            model.addAttribute (ENTRIES_TEMPLATE_ID, this.gameSiteService.findAllEntries ());
            model.addAttribute(SEARCH_VALUE,search);
            return GAMESITE_TEMPLATE;
        }
    }

    @GetMapping ("update/{id}")
    public String editComment (Model model, @PathVariable Integer id, Search search) {
        model.addAttribute (ENTRIES_TEMPLATE_ID, this.gameSiteService.findAllEntries ());
        model.addAttribute (GAMESITE_FORM_HEADER_ID, "Please Change the Entry");
        model.addAttribute (NEW_ENTRY_TEMPLATE_ID, this.gameSiteService.findOne (id));
        model.addAttribute(SEARCH_VALUE,search);
        return GAMESITE_TEMPLATE;
    }


    @PostMapping ("update/{id}")
    public String saveComment (Model model,
                               @PathVariable Integer id,
                               @Valid @ModelAttribute (NEW_ENTRY_TEMPLATE_ID) GameSiteEntry newEntry,
                               BindingResult bindingResult, Search search ) {
        if (!bindingResult.hasErrors ()) {
            GameSiteEntry current = this.gameSiteService.findOne (id);
            current.setUser (newEntry.getUser ());
            current.setComment (newEntry.getComment ());
            current.setGameTitle(newEntry.getGameTitle());
            current.setScore(newEntry.getScore());

            this.gameSiteService.save (current);
            return HOMEPAGE_REDIRECT;
        }
        else {
            model.addAttribute (GAMESITE_FORM_HEADER_ID, "Please Correct the Score entry");
            model.addAttribute (ENTRIES_TEMPLATE_ID, this.gameSiteService.findAllEntries ());
            model.addAttribute(SEARCH_VALUE,search);
            return GAMESITE_TEMPLATE;
        }
    }

    @GetMapping (value = "search",params = {"searchValue"})
    public String searchComments(Model model, @RequestParam("searchValue") String value, Search search)
    {




        model.addAttribute (ENTRIES_TEMPLATE_ID, this.gameSiteService.search (value));
        model.addAttribute (NEW_ENTRY_TEMPLATE_ID, new GameSiteEntry());
        model.addAttribute(PAGE_HEADER, "Search Result For: "+value);
        model.addAttribute(SEARCH_VALUE, search);


        return SEARCH_TEMPLATE;
    }
}
