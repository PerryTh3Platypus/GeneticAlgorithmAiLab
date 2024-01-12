package entity;

import constants.Constants;
import rng.RandomNumberGenerator;

import java.util.ArrayList;

public class EntitySpawner {
    public static ArrayList<Entity> spawnEntities(int numOfEntities){
        ArrayList<Entity> entities = new ArrayList<>();
        for (int i = 0; i < numOfEntities; i++){
            Entity entity = new Entity();
            entity.setChromosome(generateRandomChromosome());
            entities.add(entity);
        }
        return entities;
    }

    public static ArrayList<Integer> generateRandomChromosome(){
        boolean isDone = false;

        ArrayList<Integer> chromosome = new ArrayList<>();
        while (!isDone){
            int randomNumber = RandomNumberGenerator.generateNumber(0, 9);
            if (!chromosome.contains(randomNumber))
                chromosome.add(randomNumber);
            else
                continue;
            if (chromosome.size() == 9)
                isDone = true;
        }
        return chromosome;
    }
}
