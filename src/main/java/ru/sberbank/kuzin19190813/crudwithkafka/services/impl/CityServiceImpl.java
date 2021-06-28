package ru.sberbank.kuzin19190813.crudwithkafka.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sberbank.kuzin19190813.crudwithkafka.dto.CityDTO;
import ru.sberbank.kuzin19190813.crudwithkafka.entities.City;
import ru.sberbank.kuzin19190813.crudwithkafka.repositories.CityRepository;
import ru.sberbank.kuzin19190813.crudwithkafka.services.CreatorService;

@Service
public class CityServiceImpl extends CreatorService<CityDTO, City, CityRepository> {
    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        super(cityRepository);
    }

    @Override
    protected City getNew(String name) {
        return new City(name);
    }
}
