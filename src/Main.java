import constants.Constants;
import crossover.Crossover;
import entity.Entity;
import entity.EntitySpawner;
import fitness.Fitness;
import rng.RandomNumberGenerator;
import selection.TournamentSelection;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> solution = new ArrayList<>();
        for (int i = 0; i < 9; i++){
            solution.add(i);
        }
        Constants.solution = solution;



        ArrayList<Entity> entities = EntitySpawner.spawnEntities(Constants.POPULATION_SIZE);
        entities.forEach(entity -> Fitness.calculateFitness(entity, Constants.solution));
        while(true){
            for (Entity entity : entities){
                if (entity.getFitness() == Constants.FITNESS_TARGET){
                    entity.printChromosome();
                    System.exit(0);
                }
            }
            System.out.println("size of entities: " + entities.size());

            ArrayList<Entity> newGeneration = new ArrayList<>();
            while (!entities.isEmpty()){
                System.out.println("size of entities: " + entities.size());
                ArrayList<Entity> parents = TournamentSelection.performTournamentSelection(entities);
                ArrayList<Entity> children = Crossover.makeTwoOffspring(parents);
                System.out.println("size of entities: " + entities.size());
                for (Entity child : children){
                    newGeneration.add(child);
                }
            }
            entities = newGeneration;
            System.out.println("size of new generation: " + entities.size());
        }
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

    public static void biasedMutationDemo(){
        ArrayList<Integer> solution = new ArrayList<>();
        for (int i = 0; i < 9; i++){
            solution.add(i);
        }
        Constants.solution = solution;
        ArrayList<Entity> entities = EntitySpawner.spawnEntities(1);
        Entity entity = entities.get(0);
        Fitness.calculateFitness(entity, Constants.solution);
        int startingFitness = entity.getFitness();
        for(int i = 0; i < 1000000; i++){
            entity.printChromosome();
            entity.mutate();
        }
        Fitness.calculateFitness(entity, Constants.solution);
        int fitness = entity.getFitness();
        System.out.println("Starting fitness: " + startingFitness * 10 + "%");
        System.out.println("Fitness after 1000000 mutations: " + fitness * 10 + "%");
    }
}