package selection;

import constants.Constants;
import entity.Entity;
import fitness.Fitness;
import rng.RandomNumberGenerator;

import java.util.ArrayList;
import java.util.Collections;

public class TournamentSelection {
    public static final int TOURNAMENT_SIZE = Constants.TOURNAMENT_SIZE;

    public static ArrayList<Entity> performTournamentSelection(ArrayList<Entity> entities){
        // does tournament selection and returns 2 parents
        ArrayList<Entity> potentialParents = new ArrayList<>();
        for (int i = 0; i < TOURNAMENT_SIZE; i++){
            potentialParents.add(randomlySelect(entities));
        }

        //potentialParents.forEach(parent -> entities.remove(parent));
        entities.removeAll(potentialParents);

        potentialParents.removeAll(Collections.singleton(null)); // remove nulls if there are any
        Fitness.sortByFitness(potentialParents);

        ArrayList<Entity> parents = new ArrayList<>();
        if (potentialParents.size() >= 2){
            parents.add(potentialParents.get(0));
            parents.add(potentialParents.get(1));
            potentialParents.remove(0);
            potentialParents.remove(0);
            // entities that lost the tournament are put back. maybe shouldn't do this
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
