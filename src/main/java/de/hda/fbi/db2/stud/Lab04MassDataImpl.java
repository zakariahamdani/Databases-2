package de.hda.fbi.db2.stud;

import de.hda.fbi.db2.api.Lab04MassData;
import de.hda.fbi.db2.stud.entity.Category;
import de.hda.fbi.db2.stud.entity.Game;
import de.hda.fbi.db2.stud.entity.Player;
import de.hda.fbi.db2.stud.entity.Question;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lab04MassDataImpl extends Lab04MassData {
    private List<Player> players = new ArrayList<Player>();
    private Timestamp timestamp;

    @Override
    public void createMassData() {
        lab02EntityManager.getEntityManager().getTransaction().begin();
        int i = 0;
        do {
            i++;
            players.add((Player)lab03Game.getOrCreatePlayer(generateName()));
        }while(i<50);

        for(Player p: players){
            int j = 0;
            long delay = 0;
            do {
                j++;
                Game game = (Game) lab03Game.createGame(p, lab03Game.getQuestions(randomSubList(lab01Data.getCategories(), 10), 2));
                game.setStartedAt(System.currentTimeMillis() + delay);
                lab03Game.playGame(game);
                game.setEndedAt(System.currentTimeMillis() + delay);
                delay += 12000000;

                //lab02EntityManager.getEntityManager().getTransaction().begin();

                lab02EntityManager.getEntityManager().persist(game);
                //lab02EntityManager.getEntityManager().persist(game.getPlayer());
                //lab03Game.persistGame(game);

            }while (j < 10);
            lab03Game.getLab02EntityManager().getEntityManager().flush();
            lab03Game.getLab02EntityManager().getEntityManager().clear();
        }
    }

    public String generateName() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int min = 10;
        int max = 14;
        int targetStringLength = (int)Math.floor(Math.random()*(max-min+1)+min);
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        System.out.println(generatedString);
        return generatedString;
    }

    public static <T> Set<T>
    findDuplicateInStream(Stream<T> stream)
    {

        // Set to store the duplicate elements
        Set<T> items = new HashSet<>();

        // Return the set of duplicate elements
        return stream.filter(n -> !items.add(n)).collect(Collectors.toSet());
    }

    public List<Category> randomSubList(List<Category> list, int newSize) {
        list = new ArrayList<>(list);
        Collections.shuffle(list);
        return list.subList(0, newSize);
    }
}
