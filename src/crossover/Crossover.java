package crossover;

import constants.Constants;
import entity.Entity;
import fitness.Fitness;
import rng.RandomNumberGenerator;

import java.util.ArrayList;

public class Crossover {
    /*  */
    public static ArrayList<Entity> makeTwoOffspring(ArrayList<Entity> parents){
        if (parents.size() == 1)
            return parents;

        ArrayList<Entity> children = new ArrayList<>();
        int generation = parents.get(0).getGeneration();
        ++generation;

        ArrayList<Integer> parentOneChromosome = parents.get(0).getChromosome();
        ArrayList<Integer> parentTwoChromosome = parents.get(1).getChromosome();
        ArrayList<Integer> childChromosome;

        Entity childOne = new Entity();
        childChromosome = combineGeneticMaterial(parentOneChromosome, parentTwoChromosome);
        childOne.setChromosome(childChromosome);
        childOne.mutate();
        Fitness.calculateFitness(childOne, Constants.solution);
        childOne.setGeneration(generation);

        Entity childTwo = new Entity();
        // calling combineGeneticMaterial() again for more diversity
        childChromosome = combineGeneticMaterial(parentOneChromosome, parentTwoChromosome);
        childTwo.setChromosome(childChromosome);
        childTwo.mutate();
        Fitness.calculateFitness(childTwo, Constants.solution);
        childTwo.setGeneration(generation);

        children.add(childOne);
        children.add(childTwo);


        return children;
    }

    public static ArrayList<Integer> combineGeneticMaterial(ArrayList<Integer> chromosomeOne, ArrayList<Integer> chromosomeTwo){
        /* This function is biased. It will look at both parents' chromosomes and if one chromosome has the right
        *  tile in the right place, it will pick that one for the child. */
        ArrayList<Integer> childChromosome = new ArrayList<>();
        for (int i = 0; i < chromosomeOne.size(); i++){ // doesn't matter which chromosome you pick for size. They should both be the same size
            // the desired genetic (tile in this context) material
            int desiredTile = Constants.solution.get(i);
            // genetic material from parent one
            int tileFromParentOne = chromosomeOne.get(i);
            // genetic material from parent two
            int tileFromParentTwo = chromosomeTwo.get(i);
            // if the genetic material from parent one is what we're looking for, then add it to the child
            if(tileFromParentOne == desiredTile)
                childChromosome.add(tileFromParentOne);
            // if not, try the other parent's genetic material
            else if (tileFromParentTwo == desiredTile)
                childChromosome.add(tileFromParentTwo);
            // else, just randomly pick genetic material from one of the parents and add it to the child
            else{
                boolean addedGeneticMaterialToChild = false;
                // generate a number, 0 or 1
                int randomlyChosenChromosome = RandomNumberGenerator.generateNumber(0, 1);
                while(!addedGeneticMaterialToChild){
                    // if its 0, take genetic material from parent one
                    if (randomlyChosenChromosome == 0){
                        if (!childChromosome.contains(chromosomeOne.get(i))){
                            childChromosome.add(chromosomeOne.get(i));
                            addedGeneticMaterialToChild = true;
                        }
                    }
                    // otherwise take it from parent two
                    else
                        childChromosome.add(chromosomeTwo.get(i));

                }

            }
        }

        return childChromosome;
    }
}
