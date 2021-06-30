package ru.sberbank.kuzin19190813.crudwithkafka.util.test;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class TestMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static Long mapToLong(Object o) {
        return modelMapper.map(o, Long.class);
    }

    public static <D> D map(Object o, Class<D> clazz) {
        return modelMapper.map(o, clazz);
    }

    public static <D> List<D> mapToDtoList(Object o, Class<D> clazz) {
        return ((List<Object>) TestMapper.map(o, List.class))
                .stream()
                .map(i -> modelMapper.map(i, clazz))
                .collect(Collectors.toList());
    }
}
