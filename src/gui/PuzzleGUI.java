package gui;

import entity.Entity;
import entity.EntitySpawner;
import fitness.Fitness;
import selection.TournamentSelection;
import crossover.Crossover;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PuzzleGUI extends JFrame {

    private PuzzleBoard puzzleBoard;

    public PuzzleGUI() {
        initializeGUI();
    }

    private void initializeGUI() {
        setTitle("Puzzle Solver");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel puzzlePanel = new JPanel();
        puzzlePanel.setLayout(new GridLayout(3, 3));

        

        JButton evolveButton = new JButton("Evolve Puzzle");
        evolveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                evolvePuzzle();
            }
        });

        add(puzzlePanel, BorderLayout.CENTER);
        add(evolveButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void evolvePuzzle() {
        ArrayList<Entity> entities = null;
        EntitySpawner PuzzleInitializer;


        ArrayList<Entity> newGeneration = new ArrayList<>();
        while (!entities.isEmpty()) {
            ArrayList<Entity> parents = TournamentSelection.performTournamentSelection(entities);
            ArrayList<Entity> children = Crossover.makeTwoOffspring(parents);
            newGeneration.addAll(children);
        }

        updatePuzzleState(newGeneration);
    }

    private void updatePuzzleState(ArrayList<Entity> entities) {

        JPanel puzzlePanel = (JPanel) getContentPane().getComponent(0);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JLabel cellLabel = new JLabel(String.valueOf(puzzleBoard.getCellValue(i, j)));
                puzzlePanel.add(cellLabel);
            }
        }

        // După actualizare, reafișează panoul
        puzzlePanel.revalidate();
        puzzlePanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PuzzleGUI());
    }
}
