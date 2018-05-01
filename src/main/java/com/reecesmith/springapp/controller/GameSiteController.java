package com.reecesmith.springapp.controller;

import com.reecesmith.springapp.Search;
import com.reecesmith.springapp.domain.GameSiteEntry;
import com.reecesmith.springapp.service.GameSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
@RequestMapping ("/api")
@RestController
public class GameSiteController
{

    @Autowired
    private GameSiteService gameSiteService;


    @GetMapping("/entry")
    public List<GameSiteEntry> getAll()
    {
        return gameSiteService.findAllEntries();
    }

    @DeleteMapping("/entry/{id}")
    public ResponseEntity<Void> deleteGameEntryById(@PathVariable("id") Integer id)
    {
        try
        {
            this.gameSiteService.deleteGameSiteEntryById(id);
            return ResponseEntity.ok().build();
        }
        catch (ResourceAccessException e)
        {
            return ResponseEntity.notFound().build();
        }


    }

    @PostMapping("/add")
    public void addEntry(@RequestBody GameSiteEntry gameSiteEntry)
    {
        this.gameSiteService.save(gameSiteEntry);
    }
    @GetMapping("/entry/{id}")
    public GameSiteEntry findGuestBookEntryById (@PathVariable ("id") Integer id)
    {
        return this.gameSiteService.findGameSiteEntryById(id);
    }

    @GetMapping("/user/{user}")
    public List<GameSiteEntry> findGameSiteEntryByUser(@PathVariable ("user") String user)
    {
        return this.gameSiteService.findGameSiteEntryByUser(user);
    }

    @PostMapping("/update")
    public void updateEntry(@RequestBody GameSiteEntry gameSiteEntry)
    {
        this.gameSiteService.save(gameSiteEntry);

    }

    @GetMapping (value = "search",params = {"searchValue", "sort", "order"})
    public List<GameSiteEntry> searchEntriesSortOrder( @RequestParam("searchValue") String value, Search search, @RequestParam(name = "sort") String sort, @RequestParam("order") String order)
    {




        return this.gameSiteService.search (value, sort, order);
    }


    @GetMapping (value = "search",params = {"searchValue", "order"})
    public List<GameSiteEntry> searchEntriesOrder( @RequestParam("searchValue") String value,  @RequestParam("order") String order)
    {


        String sort = "score";

        return this.gameSiteService.search (value, sort, order);
    }

    @GetMapping (value = "search",params = {"searchValue", "sort" })
    public List<GameSiteEntry> searchEntriesSort( @RequestParam("searchValue") String value,  @RequestParam(name = "sort") String sort)
    {


        String order = "asc";

        return this.gameSiteService.search (value, sort, order);

    }

    @GetMapping (value = "search",params = {"searchValue"})
    public List<GameSiteEntry> searchEntries( @RequestParam("searchValue") String value)
    {
        String sort = "score";
        String order = "asc";
         return this.gameSiteService.search (value, sort, order);




    }
}
