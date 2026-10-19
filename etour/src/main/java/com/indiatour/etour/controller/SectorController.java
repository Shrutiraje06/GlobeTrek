package com.indiatour.etour.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.indiatour.etour.entity.Sector;
import com.indiatour.etour.service.SectorService;

@RestController
@RequestMapping("/sectors")
public class SectorController {

    @Autowired
    private SectorService sectorService;

    @PostMapping
    public Sector saveSector(@RequestBody Sector sector) {
        return sectorService.saveSector(sector);
    }

    @GetMapping
    public List<Sector> getAllSectors() {
        return sectorService.getAllSectors();
    }

    @GetMapping("/{id}")
    public Sector getSectorById(@PathVariable Long id) {
        return sectorService.getSectorById(id);
    }

    @PutMapping("/{id}")
    public Sector updateSector(@PathVariable Long id,
                               @RequestBody Sector sector) {
        return sectorService.updateSector(id, sector);
    }

    @DeleteMapping("/{id}")
    public String deleteSector(@PathVariable Long id) {

        sectorService.deleteSector(id);

        return "Sector Deleted Successfully!";
    }

}