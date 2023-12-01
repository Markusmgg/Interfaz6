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

public class UnovsUno extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JButton[][] playerGrid;
    private JButton[][] opponentGrid;
    private int remainingShipsPlayer = 4; // El jugador tiene que encontrar 4 barcos

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UnovsUno frame = new UnovsUno();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public UnovsUno() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 300, 150); // Tamaño reducido
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(1, 2));

        playerGrid = createGrid(5, 5);
        opponentGrid = createGrid(5, 5);

        contentPane.add(createGridPanel(playerGrid, "Tu Tablero"));
        contentPane.add(createGridPanel(opponentGrid, "Tablero de la IA"));

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
        // Lógica para que la IA coloque barcos aleatoriamente
        placeRandomShips(opponentGrid);

        // Lógica para que el jugador encuentre los barcos
        JOptionPane.showMessageDialog(this, "Encuentra los barcos de la IA.");
    }

    private void placeRandomShips(JButton[][] grid) {
        // Lógica para que la IA coloque barcos aleatoriamente
        // Puedes personalizar esta lógica según tus necesidades
        // En este ejemplo, simplemente coloca 4 barcos de 1 casilla en posiciones aleatorias

        for (int i = 0; i < 4; i++) {
            int row = (int) (Math.random() * grid.length);
            int col = (int) (Math.random() * grid[0].length);

            // Verifica si la posición está ocupada
            if (grid[row][col].getText().isEmpty()) {
                grid[row][col].setText("B");
            } else {
                // Si la posición ya está ocupada, intenta nuevamente
                i--;
            }
        }
    }

    private void gridButtonClicked(int row, int col) {
        // Lógica para manejar el clic en el tablero durante el juego
        // Puedes implementar esta parte según tus necesidades
        // ...

        // Ejemplo: Verifica si el jugador ha encontrado un barco de la IA
        if (opponentGrid[row][col].getText().equals("B")) {
            opponentGrid[row][col].setText("X"); // Marca como encontrado
            remainingShipsPlayer--;

            if (remainingShipsPlayer == 0) {
                // El jugador ha encontrado todos los barcos
                JOptionPane.showMessageDialog(this, "¡Has encontrado todos los barcos de la IA! ¡Ganaste!");
                // Puedes implementar lógica adicional para reiniciar el juego, salir, etc.
                System.exit(0);
            } else {
                JOptionPane.showMessageDialog(this, "¡Encontraste un barco! Sigue buscando.");
            }
        } else {
            opponentGrid[row][col].setText("O"); // Marca como agua
            JOptionPane.showMessageDialog(this, "Agua. Sigue intentando.");
        }

        // Lógica para el turno de la IA
        opponentTurn();
    }

    private void opponentTurn() {
        // Lógica para el turno de la IA
        // En este ejemplo, la IA simplemente elige una posición aleatoria
        int row = (int) (Math.random() * playerGrid.length);
        int col = (int) (Math.random() * playerGrid[0].length);

        // Verifica si la posición ya ha sido seleccionada por la IA
        if (!playerGrid[row][col].isEnabled()) {
            // Si la posición ya ha sido seleccionada, intenta nuevamente
            opponentTurn();
        } else {
            // Realiza el disparo de la IA
            playerGrid[row][col].setEnabled(false);

            // Verifica si la IA ha encontrado un barco del jugador
            if (playerGrid[row][col].getText().equals("B")) {
                playerGrid[row][col].setText("X"); // Marca como encontrado
                JOptionPane.showMessageDialog(this, "La IA ha encontrado uno de tus barcos.");
            } else {
                playerGrid[row][col].setText("O"); // Marca como agua
            }
        }
    }
}
