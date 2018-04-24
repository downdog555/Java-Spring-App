package com.reecesmith.springapp.domain;


import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface GameSiteEntryRepository extends CrudRepository<GameSiteEntry, Integer>
{
    @Override
    List<GameSiteEntry> findAll();

    GameSiteEntry findGameSiteEntryById (Integer id);

    List<GameSiteEntry> findGameSiteEntryByUser(String user);

    List<GameSiteEntry> findGameSiteEntryByGameTitle(String gameTitle);
}
