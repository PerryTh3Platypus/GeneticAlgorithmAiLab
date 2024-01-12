package entity;

import rng.RandomNumberGenerator;

import java.util.ArrayList;

public class Entity {
    private ArrayList<Integer> chromosome;
    private int mutationRate;
    private int fitness;

    public Entity(int mutationRate){
        this.mutationRate = mutationRate;
    }

    public void mutate(){
        if (RandomNumberGenerator.generateNumber(0, 100) <= mutationRate){
            int index1 = RandomNumberGenerator.generateNumber(0, 9);
            int index2 = RandomNumberGenerator.generateNumber(0, 9);
            int temp = chromosome.get(index1);
            chromosome.add(index1, chromosome.get(index2));
            chromosome.add(index2, temp);
        }
    }

    public void printChromosome(){
        System.out.println("**********************************************");
        System.out.println("Entity 0x" + Integer.toHexString(this.hashCode()).toUpperCase() + " has the following chromosome:");

        for (int i = 0; i < 9; i+=3){
            System.out.println("[ " + chromosome.get(i) + " " + chromosome.get(i + 1) + " " + chromosome.get(i + 2) + " ]");
        }
        System.out.println("**********************************************");
    }

    public ArrayList<Integer> getChromosome() {
        return chromosome;
    }

    public void setChromosome(ArrayList<Integer> chromosome) {
        this.chromosome = chromosome;
    }

    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }
}
