package fitness;

import entity.Entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Fitness {
    public static Comparator<Entity> entityComparator = Comparator.comparing(Entity::getFitness).reversed();
    public static void calculateFitness(Entity entity, ArrayList<Integer> solution){
        /* Calculates fitness. Function will look at the puzzle's tiles and will give
        *  a higher fitness the more tiles are in their correct place.
        *  1 = worst fitness (no tiles are where they should be),
        *  10 = perfect fitness (all tiles are where they should be) */
        int fitness = 1;
        for (int i = 0; i < 9; i++){
            if (entity.getChromosome().get(i) == solution.get(i))
                ++fitness;
        }
        entity.setFitness(fitness);
    }

    public static void sortByFitness(ArrayList<Entity> entities){
        // sorts entities in a list. The entities with higher fitness come first, kinda like having priority in a queue.
        entities.sort(entityComparator.reversed());
    }
}
