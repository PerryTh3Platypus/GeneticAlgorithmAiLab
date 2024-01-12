package selection;

import entity.Entity;
import fitness.Fitness;
import rng.RandomNumberGenerator;

import java.util.ArrayList;
import java.util.Collections;

public class TournamentSelection {
    public static final int TOURNAMENT_SIZE = 4;

    public static ArrayList<Entity> performTournamentSelection(ArrayList<Entity> entities){
        ArrayList<Entity> potentialParents = new ArrayList<>();
        for (int i = 0; i < TOURNAMENT_SIZE; i++){
            potentialParents.add(randomlySelect(entities));
        }

        potentialParents.removeAll(Collections.singleton(null)); // remove nulls if there are any
        Fitness.sortByFitness(potentialParents);

        ArrayList<Entity> parents = new ArrayList<>();
        if (potentialParents.size() >= 2){
            parents.add(entities.get(0));
            parents.add(entities.get(1));
            potentialParents.remove(0);
            potentialParents.remove(0);

            potentialParents.forEach(entity -> entities.add(entity));
        }
        else if (potentialParents.size() == 1){
            parents.add(potentialParents.get(0));
        }
        else{
            return null;
        }
        return parents;
    }

    private static Entity randomlySelect(ArrayList<Entity> entities){
        if (!entities.isEmpty()){
            int index = RandomNumberGenerator.generateNumber(0, entities.size() - 1);
            Entity entity = entities.get(index);
            entities.remove(index);
            return entity;
        }
        return null;
    }
}
