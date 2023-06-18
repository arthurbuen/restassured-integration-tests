package com.jsonplaceholder.data.factory;

import com.jsonplaceholder.data.model.Posts;
import java.util.List;
import java.util.Random;
import com.github.javafaker.Faker;

public class PostsDataFactory {

    private static final List<Posts> postData = List.of(
            new Posts(1, 1, "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
                    "quia et suscipit suscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"),
            new Posts(1, 2, "qui est esse",
                    "est rerum tempore vitae\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\nqui aperiam non debitis possimus qui neque nisi nulla"),
            new Posts(1, 3, "ea molestias quasi exercitationem repellat qui ipsa sit aut",
                    "et iusto sed quo iure\nvoluptatem occaecati omnis eligendi aut ad\nvoluptatem doloribus vel accusantium quis pariatur\nmolestiae porro eius odio et labore et velit aut"),
            new Posts(1, 4, "eum et est occaecati",
                    "ullam et saepe reiciendis voluptatem adipisci\nsit amet autem assumenda provident rerum culpa\nquis hic commodi nesciunt rem tenetur doloremque ipsam iure\nquis sunt voluptatem rerum illo velit")
    );

    public static Posts getRandomPost() {
        Random random = new Random();
        int randomIndex = random.nextInt(postData.size());
        return postData.get(randomIndex);
    }

    public static int getInvalidPost(){
        Random random = new Random();
        return random.nextInt(901) + 100;
    }

    public static Posts createRandomPost() {
        Faker faker = new Faker();

        Integer userId = faker.number().numberBetween(1, 100);
        Integer id = getNextValidId();
        String title = faker.lorem().sentence();
        String body = faker.lorem().paragraph();

        return new Posts(userId, id, title, body);
    }

    private static int getNextValidId(){
        return 101; // todo: method to consume the /posts endpoint and get the last id + 1
    }
}
