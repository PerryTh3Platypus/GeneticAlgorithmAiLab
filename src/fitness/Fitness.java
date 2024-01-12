package fitness;

import entity.Entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Fitness {
    public static Comparator<Entity> entityComparator = Comparator.comparing(Entity::getFitness).reversed();
    public static void calculateFitness(Entity entity, ArrayList<Integer> solution){
        // 1 = worst fitness, 10 = perfect fitness
        int fitness = 1;
        for (int i = 0; i < 9; i++){
            if (entity.getChromosome().get(i) == solution.get(i))
                ++fitness;
        }
        entity.setFitness(fitness);
    }

    public static void sortByFitness(ArrayList<Entity> entities){
        entities.sort(entityComparator);
    }
}
