import entity.Entity;
import entity.EntitySpawner;
import fitness.Fitness;
import selection.TournamentSelection;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<Entity> entities = EntitySpawner.spawnEntities(100);
        ArrayList<Integer> solution = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            solution.add(i);
        }
        entities.forEach(entity -> Fitness.calculateFitness(entity, solution));
        System.out.println(TournamentSelection.performTournamentSelection(entities).get(0).getChromosome());
    }

    public static void testChromosomesForUniqueness(int cycles){
        Set<ArrayList<Integer>> chromosomes = new HashSet<>();
        for(int i = 0; i < cycles; i++){
            ArrayList<Integer> chromosome = EntitySpawner.generateRandomChromosome();
            System.out.println("Chromosome generated: " + chromosome);
            chromosomes.add(chromosome);
        }
        System.out.println("Number of chromosomes generated: " + cycles);
        System.out.println("Number of unique chromosomes: " + chromosomes.size());
        System.out.println("Percentage of unique chromosomes: " + (chromosomes.size() * 1.0d / cycles) * 100 + "%");
    }
}