package com.github.leanfe.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.leanfe.App;
import lombok.Getter;
import lombok.SneakyThrows;

import java.io.File;

public class UserParser {

    @Getter
    private final File data;

    private final ObjectReader reader;
    private final ObjectWriter writer;

    public UserParser(App app, UserManager manager) {
        data = new File(app.getDataFolder(), "data.json");

        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        reader = mapper.readerFor(UserData.class);
        writer = mapper.writerFor(UserData.class);
    }

    @SneakyThrows
    public UserData fromJsonFile() {
        return getObjectReader().readValue(data);
    }

    @SneakyThrows
    public void toJsonFile(UserData obj) {
        getObjectWriter().writeValue(data, obj);
    }

    private ObjectReader getObjectReader() {
        return reader;
    }

    private ObjectWriter getObjectWriter() {
        return writer;
    }
}