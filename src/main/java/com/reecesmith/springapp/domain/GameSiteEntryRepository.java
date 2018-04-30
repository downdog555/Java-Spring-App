package com.reecesmith.springapp.domain;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface GameSiteEntryRepository extends CrudRepository<GameSiteEntry, Integer>
{
    @Override
    List<GameSiteEntry> findAll();



    GameSiteEntry findGameSiteEntryById (Integer id);

    List<GameSiteEntry> findGameSiteEntryByUser(String user);

    List<GameSiteEntry> findGameSiteEntryByGameTitle(String gameTitle);
}
