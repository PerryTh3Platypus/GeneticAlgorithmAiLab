package entity;

import constants.Constants;
import rng.RandomNumberGenerator;

import java.util.ArrayList;

public class Entity {
    private ArrayList<Integer> chromosome;
    private int mutationRate;
    private int fitness;

    private enum SlideDirections{
        UP, LEFT, RIGHT, DOWN
    }
    private int generation = 1;

    public Entity(){
        this.mutationRate = Constants.MUTATION_RATE;
    }

    public void mutate(){
        if (RandomNumberGenerator.generateNumber(0, 100) <= mutationRate){
            slideTile();
        }
        else{
            // recursion?
            return;
        }
    }

    private ArrayList<SlideDirections> findPossibleMoves(int slidingTileIndex){
        ArrayList<SlideDirections> possibleMoves = new ArrayList<>();
        switch (slidingTileIndex){
            case 0 :
                possibleMoves.add(SlideDirections.RIGHT);
                possibleMoves.add(SlideDirections.DOWN);
                break;
            case 1:
                possibleMoves.add(SlideDirections.LEFT);
                possibleMoves.add(SlideDirections.RIGHT);
                possibleMoves.add(SlideDirections.DOWN);
                break;
            case 2:
                possibleMoves.add(SlideDirections.DOWN);
                possibleMoves.add(SlideDirections.LEFT);
                break;
            case 3:
                possibleMoves.add(SlideDirections.UP);
                possibleMoves.add(SlideDirections.DOWN);
                possibleMoves.add(SlideDirections.RIGHT);
                break;
            case 4:
                possibleMoves.add(SlideDirections.RIGHT);
                possibleMoves.add(SlideDirections.UP);
                possibleMoves.add(SlideDirections.LEFT);
                possibleMoves.add(SlideDirections.DOWN);
                break;
            case 5:
                possibleMoves.add(SlideDirections.UP);
                possibleMoves.add(SlideDirections.LEFT);
                possibleMoves.add(SlideDirections.DOWN);
                break;
            case 6:
                possibleMoves.add(SlideDirections.UP);
                possibleMoves.add(SlideDirections.RIGHT);
                break;
            case 7:
                possibleMoves.add(SlideDirections.LEFT);
                possibleMoves.add(SlideDirections.UP);
                possibleMoves.add(SlideDirections.RIGHT);
                break;
            case 8:
                possibleMoves.add(SlideDirections.UP);
                possibleMoves.add(SlideDirections.LEFT);
                break;
        }
        return possibleMoves;
    }

    private boolean attemptNiceSlide(int slidingTileIndex , SlideDirections direction){
        System.out.println("Attempting nice slide with direction: " + direction.toString());
        System.out.println("Nice slide slidingTileIndex: " + slidingTileIndex);
        int indexDiff;
        switch (direction){
            case DOWN -> indexDiff = 3;
            case UP -> indexDiff = -3;
            case LEFT -> indexDiff = -1;
            case RIGHT -> indexDiff = 1;
            default -> indexDiff = 0;
        }

        int toMoveIndex = slidingTileIndex + indexDiff;
        int toMove = chromosome.get(toMoveIndex);
        if (toMove == Constants.solution.get(toMoveIndex))
            return false;
        else{
            int temp = chromosome.get(toMoveIndex);
            chromosome.set(toMoveIndex, 0);
            chromosome.set(slidingTileIndex, temp);
            return true;
        }
    }
    private void forceSlide(int slidingTileIndex){
        System.out.println("Nice slide failed. Force sliding.");
        ArrayList<SlideDirections> possibleDirections = findPossibleMoves(slidingTileIndex);

        SlideDirections direction = possibleDirections.get(RandomNumberGenerator.generateNumber(0, possibleDirections.size() - 1));
        int indexDiff;
        switch (direction){
            case DOWN -> indexDiff = 3;
            case UP -> indexDiff = -3;
            case LEFT -> indexDiff = -1;
            case RIGHT -> indexDiff = 1;
            default -> indexDiff = 0;
        }

        int toMoveIndex = slidingTileIndex + indexDiff;
        int toMove = chromosome.get(toMoveIndex);
        int temp = chromosome.get(toMoveIndex);
        chromosome.set(toMoveIndex, 0);
        chromosome.set(slidingTileIndex, temp);
    }

    private void slideTile(){
        boolean success = false; // if you had success sliding a tile without sliding a correctly placed tile out of place
        int slidingTileIndex = chromosome.indexOf(0);
        ArrayList<SlideDirections> possibleMoves = findPossibleMoves(slidingTileIndex);

        /* because mutating/sliding is biased towards becoming more fit, this function won't slide a correctly placed
        *  tile from its position unless it has no other choice.*/

        while (possibleMoves.size() > 0){
            SlideDirections slideDirection = possibleMoves.get(RandomNumberGenerator.generateNumber(0, possibleMoves.size() - 1));
            switch (slideDirection){
                case UP:
                    // take the possible move out of the pool of possible moves
                    possibleMoves.remove(slideDirection);
                    // try sliding, sliding will happen if an incorrectly placed tile is there
                    if (attemptNiceSlide(slidingTileIndex, SlideDirections.UP)){
                        possibleMoves.clear();
                        success = true;
                    }
                    break;
                case DOWN:
                    possibleMoves.remove(slideDirection);
                    if (attemptNiceSlide(slidingTileIndex, SlideDirections.DOWN)){
                        possibleMoves.clear();
                        success = true;
                    }
                    break;
                case RIGHT:
                    possibleMoves.remove(slideDirection);
                    if (attemptNiceSlide(slidingTileIndex, SlideDirections.RIGHT)){
                        possibleMoves.clear();
                        success = true;
                    }
                    break;
                case LEFT:
                    possibleMoves.remove(slideDirection);
                    if (attemptNiceSlide(slidingTileIndex, SlideDirections.LEFT)){
                        possibleMoves.clear();
                        success = true;
                    }
                    break;
            }
        }
        if (!success){
            forceSlide(slidingTileIndex);
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

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }
}
