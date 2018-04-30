package com.reecesmith.springapp.service;

import com.reecesmith.springapp.domain.GameSiteEntry;
import com.reecesmith.springapp.domain.GameSiteEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class GameSiteService
{
    private String[] bannedWords = {"boi","lul"};
    private DateTimeFormatter formattter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");

    @Autowired
    private GameSiteEntryRepository gameSiteEntryRepository;

    public List<GameSiteEntry> findAllEntries()
    {
        return this.gameSiteEntryRepository.findAll();
    }

    public GameSiteEntry findGameSiteEntryById (Integer id)
    {
        return this.gameSiteEntryRepository.findGameSiteEntryById(id);
    }

    public void deleteGameSiteEntryById(Integer id)
    {
        this.gameSiteEntryRepository.deleteById(id);
    }

    public List<GameSiteEntry> findGameSiteEntryByUser(String user)
    {
        return this.gameSiteEntryRepository.findGameSiteEntryByUser(user);
    }

    public void save (GameSiteEntry newEntry)

    {

    //we can format the date time on save






        newEntry.setGameTitle(CleanString(newEntry.getGameTitle()));
        newEntry.setComment(CleanString(newEntry.getComment()));
        newEntry.setUser(CleanString(newEntry.getUser()));

        this.gameSiteEntryRepository.save(newEntry);
    }

    private String CleanString(String value)
    {

        char[] com = value.toCharArray();
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


        value = String.copyValueOf(com);

        //we then check for banned words/replace them

        for (String word: bannedWords)
        {
            value = value.replaceAll(word, "****");

        }



        return value;
    }


    public GameSiteEntry findOne (Integer id) {
        return this.gameSiteEntryRepository.findGameSiteEntryById(id);
    }

    public List<GameSiteEntry> search (String value, String sort, String order)
    {
        //list to return
        ArrayList<GameSiteEntry> foundValues = new ArrayList<GameSiteEntry>();

        order = order.toLowerCase();
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

//we need to sort here//

        switch (sort)
        {
            case "score":
                if(order.equals("asc"))
                {
                    foundValues.sort(GameSiteEntry.GscoreComparatorASC);
                }
                else
                    {
                        foundValues.sort(GameSiteEntry.GscoreComparatorDESC);
                    }

                break;
            case "comment":
                if(order.equals("asc"))
                {
                    foundValues.sort(GameSiteEntry.GcommentCompartorASC);
                }
                else
                {
                    foundValues.sort(GameSiteEntry.GcommentCompartorDESC);
                }
                break;
            case "username":
                if(order.equals("asc"))
                {
                    foundValues.sort(GameSiteEntry.GusernameComparatorASC);
                }
                else
                {
                    foundValues.sort(GameSiteEntry.GusernameComparatorDESC);
                }

                break;
            case "gametitle":
                if(order.equals("asc"))
                {
                    foundValues.sort(GameSiteEntry.GgamenameComparatorASC);
                }
                else
                {
                    foundValues.sort(GameSiteEntry.GgamenameComparatorDESC);
                }

                break;
            case "created":
                if(order.equals("asc"))
                {
                    foundValues.sort(GameSiteEntry.GcreatedComparatorASC);
                }
                else
                {
                    foundValues.sort(GameSiteEntry.GcreatedComparatorDesc);
                }

                break;
            case "modified":
                if(order.equals("asc"))
                {
                    foundValues.sort(GameSiteEntry.GupdatedComparatorASC);
                }
                else
                {
                    foundValues.sort(GameSiteEntry.GupdatedComparatorDesc);
                }

                break;
        }




        return foundValues;
    }
}
