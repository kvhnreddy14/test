
//package com.hospital.repository.io;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.hospital.model.Day;
import com.


import java.io.File;
import java.io.IOException;
import java.util.*;


public class testJ {
}

 class IODayRepository {
    private static final String FILE_PATH = "src/main/resources/day.json";
    private final ObjectMapper mapper = new ObjectMapper();

    public List<Day> getAll() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) return new ArrayList<>();
            return mapper.readValue(file, new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException("Failed to read days", e);
        }
    }

    public void save(Day day) {
        List<Day> list = getAll();
        list.add(day);
        writeToFile(list);
    }

    public void update(UUID id, Day updated) {
        List<Day> list = getAll();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(id)) {
                list.set(i, updated);
                break;
            }
        }
        writeToFile(list);
    }

    public void delete(UUID id) {
        List<Day> list = getAll();
        list.removeIf(d -> d.getId().equals(id));
        writeToFile(list);
    }

    public Optional<Day> findById(UUID id) {
        return getAll().stream().filter(d -> d.getId().equals(id)).findFirst();
    }

    private void writeToFile(List<Day> days) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), days);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write days", e);
        }
    }
}

