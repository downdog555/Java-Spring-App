package com.reecesmith.springapp.service;

import com.reecesmith.springapp.domain.GameSiteEntry;
import com.reecesmith.springapp.domain.GameSiteEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameSiteService
{
    private String[] bannedWords = {"boi","lul"};

    @Autowired
    private GameSiteEntryRepository gameSiteEntryRepository;

    public List<GameSiteEntry> findAllEntries()
    {
        return this.gameSiteEntryRepository.findAll();
    }

    public GameSiteEntry findGuestBookEntryById (Integer id)
    {
        return this.gameSiteEntryRepository.findGameSiteEntryById(id);
    }

    public void deleteGuestBookEntryById(Integer id)
    {
        this.gameSiteEntryRepository.deleteById(id);
    }

    public List<GameSiteEntry> findGameSiteEntryByUser(String user)
    {
        return this.gameSiteEntryRepository.findGameSiteEntryByUser(user);
    }

    public void save (GameSiteEntry newEntry)

    {
        String comment = newEntry.getComment();
        char[] com = comment.toCharArray();
        for (int i =0; i< com.length;i++)
        {
            switch (com[i])
            {
                case '{':
                    com[i] = ' ';
                    break;

                case '}':
                    com[i] = ' ';
                    break;
                case ';':
                    com[i] = ' ';
                    break;
                case '<':
                    com[i] = ' ';
                    break;
                case '>':
                    com[i] = ' ';
                    break;
            }
        }
        //comment = com.toString();


        comment = String.copyValueOf(com);

        //we then check for banned words/replace them

        for (String word: bannedWords)
        {
            comment = comment.replaceAll(word, "****");

        }






        newEntry.setGameTitle(comment);

        this.gameSiteEntryRepository.save(newEntry);
    }

    public GameSiteEntry findOne (Integer id) {
        return this.gameSiteEntryRepository.findGameSiteEntryById(id);
    }

    public List<GameSiteEntry> search (String value)
    {
        //list to return
        ArrayList<GameSiteEntry> foundValues = new ArrayList<GameSiteEntry>();
        //all entries
        List<GameSiteEntry> entries = this.gameSiteEntryRepository.findAll();

        for (GameSiteEntry entry: entries)
        {
            String comment = entry.getComment();
            if(comment.contains(value) || entry.getGameTitle().contains(value) || entry.getUser().contains(value))
            {
                foundValues.add(entry);
            }
        }



        return foundValues;
    }
}