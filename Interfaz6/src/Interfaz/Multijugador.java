package Interfaz;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Multijugador extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JButton[][] playerGrid;
    private JButton[][] opponentGrid;
    private int currentPlayer = 1;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Multijugador frame = new Multijugador();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Multijugador() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 300, 150); // Tamaño reducido
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(1, 2));

        playerGrid = createGrid(5, 5);
        opponentGrid = createGrid(5, 5);

        contentPane.add(createGridPanel(playerGrid, "Tu Tablero"));
        contentPane.add(createGridPanel(opponentGrid, "Tablero del Oponente"));

        initializeGame();
    }

    private JButton[][] createGrid(int rows, int cols) {
        JButton[][] grid = new JButton[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new JButton();
                final int row = i;
                final int col = j;
                grid[i][j].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        gridButtonClicked(row, col);
                    }
                });
            }
        }
        return grid;
    }

    private JPanel createGridPanel(JButton[][] grid, String title) {
        JPanel panel = new JPanel(new GridLayout(grid.length, grid[0].length));
        panel.setBorder(new EmptyBorder(5, 5, 5, 5)); // Espaciado reducido

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                panel.add(grid[i][j]);
            }
        }

        return panel;
    }

    private void initializeGame() {
        // Lógica para colocar barcos y demás inicializaciones
        // Puedes implementar esta parte según tus necesidades
        // ...

        // Ejemplo: Coloca un barco en las coordenadas (0, 0) y (0, 1)
        playerGrid[0][0].setText("B");
        playerGrid[0][1].setText("B");
    }

    private void gridButtonClicked(int row, int col) {
        // Lógica para manejar el clic en el tablero
        // Puedes implementar esta parte según tus necesidades
        // ...

        // Ejemplo: Verifica si el jugador ha hundido un barco
        if (opponentGrid[row][col].getText().equals("B")) {
            opponentGrid[row][col].setText("X"); // Marcamos como hundido
            JOptionPane.showMessageDialog(this, "¡Hundiste un barco!");
        } else {
            opponentGrid[row][col].setText("O"); // Marcamos como agua
            JOptionPane.showMessageDialog(this, "Agua. Sigue intentando.");
        }

        // Lógica para cambiar al siguiente jugador
        currentPlayer = (currentPlayer == 1) ? 2 : 1;
    }
}
