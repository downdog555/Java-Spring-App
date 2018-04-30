package com.reecesmith.springapp.controller;

import com.reecesmith.springapp.domain.GameSiteEntry;
import com.reecesmith.springapp.service.GameSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Void> deleteGuestBookEntryById(@PathVariable("id") Integer id)
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
    public void addComment(@RequestBody GameSiteEntry guestBookEntry)
    {
        this.gameSiteService.save(guestBookEntry);
    }
    @GetMapping("/entry/{id}")
    public GameSiteEntry findGuestBookEntryById (@PathVariable ("id") Integer id)
    {
        return this.gameSiteService.findGameSiteEntryById(id);
    }

    @GetMapping("/user/{user}")
    public List<GameSiteEntry> findGuestBookEntryByUser(@PathVariable ("user") String user)
    {
        return this.gameSiteService.findGameSiteEntryByUser(user);
    }

    @PostMapping("/update")
    public void updateComment(@RequestBody GameSiteEntry guestBookEntry)
    {
        this.gameSiteService.save(guestBookEntry);

    }
}
