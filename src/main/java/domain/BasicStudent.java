package domain;

import json.*;

/**
 * Created by Andrii_Rodionov on 1/5/2017.
 */
public class BasicStudent implements Jsonable {

    protected String name;
    protected String surname;
    protected Integer year;

    public BasicStudent() {
    }

    public BasicStudent(String name, String surname, Integer year) {
        this.name = name;
        this.surname = surname;
        this.year = year;
    }

    @Override
    public JsonObject toJsonObject() {
        // ToDo
        JsonString nameString = new JsonString(name);
        JsonString surnameString = new JsonString(surname);
        JsonNumber yearNumber = new JsonNumber(year);
        JsonPair namePair = new JsonPair("name", nameString);
        JsonPair surnamePair = new JsonPair("surname", surnameString);
        JsonPair yearPair = new JsonPair("year", yearNumber);
        return new JsonObject(namePair, surnamePair, yearPair);
    }
}
