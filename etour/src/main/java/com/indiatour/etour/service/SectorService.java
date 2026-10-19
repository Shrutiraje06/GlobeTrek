package com.indiatour.etour.service;

import java.util.List;

import com.indiatour.etour.entity.Sector;

public interface SectorService {

    Sector saveSector(Sector sector);

    List<Sector> getAllSectors();

    Sector getSectorById(Long id);

    Sector updateSector(Long id, Sector sector);

    void deleteSector(Long id);
}