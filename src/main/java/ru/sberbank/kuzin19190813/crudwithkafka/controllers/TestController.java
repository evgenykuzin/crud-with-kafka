package ru.sberbank.kuzin19190813.crudwithkafka.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static ru.sberbank.kuzin19190813.crudwithkafka.util.test.MarketplaceCreator.*;

@RestController
@RequestMapping(value = "/test")
public class TestController {
    @GetMapping("/create-marketplace/{id}")
    public Object createMarketplace(@PathVariable Integer id) {
        switch (id) {
            case 1: {
                createMarketplace1();
                return "{'result': 'Marketplace 1 has successfully created!'}";
            } case 2: {
                return "{'result': 'No marketplaces with id: 2 can be created. Use id: 1'}";
            }
        }
        return "{'result': 'wrong id to create a marketplace. Use id: 1'}";
    }
}
