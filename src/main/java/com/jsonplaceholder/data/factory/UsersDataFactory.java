package com.jsonplaceholder.data.factory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.jsonplaceholder.data.model.Users;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

import static com.jsonplaceholder.file.FileUtils.getObjectsFromJsonFile;


public class UsersDataFactory {

    private static List<Users> getUsersFromJsonFile() {
        String fileName = "data/valid_users.json";
        TypeReference<List<Users>> typeReference = new TypeReference<>() {};
        return getObjectsFromJsonFile(fileName, typeReference);
    }

    public static Users getValidUser() {
        Random random = new Random();
        int randomIndex = random.nextInt(getUsersFromJsonFile().size());
        return getUsersFromJsonFile().get(randomIndex);
    }

    public static int getInvalidUser(){
        Random random = new Random();
        return random.nextInt(90) + 11;
    }

    private static int getNextValidId(){
        return 11; // todo: method to consume the /users endpoint and get the last id + 1
    }

    public static Users createRandomUser() {
        Faker faker = new Faker();

        int id = getNextValidId();
        String name = faker.name().fullName();
        String username = faker.name().username();
        String email = faker.internet().emailAddress();

        Users.Address address = new Users.Address(
                faker.address().streetName(),
                faker.address().secondaryAddress(),
                faker.address().city(),
                faker.address().zipCode(),
                new Users.Address.Geo(
                        faker.address().latitude(),
                        faker.address().longitude()
                )
        );

        String phone = faker.phoneNumber().phoneNumber();
        String website = faker.internet().url();

        Users.Company company = new Users.Company(
                faker.company().name(),
                faker.company().catchPhrase(),
                faker.company().bs()
        );

        return new Users(id, name, username, email, address, phone, website, company);
    }

}
