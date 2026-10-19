package com.indiatour.etour.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indiatour.etour.entity.Sector;
import com.indiatour.etour.repository.SectorRepository;

@Service
public class SectorServiceImpl implements SectorService {

    @Autowired
    private SectorRepository sectorRepository;

    @Override
    public Sector saveSector(Sector sector) {
        return sectorRepository.save(sector);
    }

    @Override
    public List<Sector> getAllSectors() {
        return sectorRepository.findAll();
    }

    @Override
    public Sector getSectorById(Long id) {
        return sectorRepository.findById(id).orElse(null);
    }

    @Override
    public Sector updateSector(Long id, Sector sector) {

        Sector existingSector = sectorRepository.findById(id).orElse(null);

        if (existingSector != null) {

            existingSector.setSectorName(sector.getSectorName());
            existingSector.setDescription(sector.getDescription());

            return sectorRepository.save(existingSector);
        }

        return null;
    }

    @Override
    public void deleteSector(Long id) {
        sectorRepository.deleteById(id);
    }

}