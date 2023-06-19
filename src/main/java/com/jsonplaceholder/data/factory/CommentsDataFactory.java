package com.jsonplaceholder.data.factory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.jsonplaceholder.data.model.Comments;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.jsonplaceholder.file.FileUtils.getObjectsFromJsonFile;

public class CommentsDataFactory {

    private static List<Comments> getCommentsFromJsonFile() {
        String fileName = "data/valid_comments.json";
        TypeReference<List<Comments>> typeReference = new TypeReference<>() {};
        return getObjectsFromJsonFile(fileName, typeReference);
    }

    public static Comments getValidComment() {
        Random random = new Random();
        int randomIndex = random.nextInt(getCommentsFromJsonFile().size());
        return getCommentsFromJsonFile().get(randomIndex);
    }

    public static Comments createRandomComment() {
        Faker faker = new Faker();

        int postId = faker.number().numberBetween(1, 100);
        int id = getNextValidId();
        String name = String.join(" ", faker.lorem().words(4));
        String email = faker.internet().emailAddress();
        String body = faker.lorem().paragraph();

        return new Comments(postId, id, name, email, body);
    }

    public static Comments createNewComment(int postId, String name, String email, String body) {
        return new Comments(postId, getNextValidId(), name, email, body);
    }

    private static int getNextValidId(){
        return 501; // todo: method to consume the /comments endpoint and get the last id + 1
    }

}
