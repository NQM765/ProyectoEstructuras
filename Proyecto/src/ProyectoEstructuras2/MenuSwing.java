/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProyectoEstructuras2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuSwing extends JFrame {

    public MenuSwing() {
        setTitle("Menu de Tarjetas");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Centrar la ventana en la pantalla
        setLayout(new BorderLayout());  // Usar BorderLayout para organizar componentes

        // Crear el label con la palabra "MENU" y colocarlo en la parte superior
        JLabel lblMenu = new JLabel("MENU", SwingConstants.CENTER);
        lblMenu.setFont(new Font("Arial", Font.BOLD, 24));
        add(lblMenu, BorderLayout.NORTH);

        // Crear un panel para los botones con GridLayout (4 filas, 2 columnas)
        JPanel panelBotones = new JPanel(new GridLayout(4, 2, 20, 20));  // 20px de espacio entre filas y columnas

        // Añadir márgenes al panel para crear más espacio en blanco alrededor
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); 

        // Crear botones para cada opción del menú con tamaños más pequeños
        Dimension buttonSize = new Dimension(120, 30);  // Tamaño más pequeño para los botones

        JButton btnCrearTarjeta = new JButton("1. Crear tarjeta");
        btnCrearTarjeta.setPreferredSize(buttonSize);
        JButton btnBuscarTarjeta = new JButton("2. Buscar tarjeta");
        btnBuscarTarjeta.setPreferredSize(buttonSize);
        JButton btnVerTarjetas = new JButton("3. Ver tarjetas");
        btnVerTarjetas.setPreferredSize(buttonSize);
        JButton btnVerGuardadas = new JButton("4. Ver tarjetas guardadas");
        btnVerGuardadas.setPreferredSize(buttonSize);
        JButton btnAgruparTarjetas = new JButton("5. Agrupar tarjetas por etiqueta");
        btnAgruparTarjetas.setPreferredSize(buttonSize);
        JButton btnMejorCalificadas = new JButton("6. Ver tarjetas mejor calificadas");
        btnMejorCalificadas.setPreferredSize(buttonSize);
        JButton btnMasRecientes = new JButton("7. Ver tarjetas más recientes");
        btnMasRecientes.setPreferredSize(buttonSize);
        JButton btnSalir = new JButton("8. Salir");
        btnSalir.setPreferredSize(buttonSize);

        // Añadir los botones al panel
        panelBotones.add(btnCrearTarjeta);
        panelBotones.add(btnBuscarTarjeta);
        panelBotones.add(btnVerTarjetas);
        panelBotones.add(btnVerGuardadas);
        panelBotones.add(btnAgruparTarjetas);
        panelBotones.add(btnMejorCalificadas);
        panelBotones.add(btnMasRecientes);
        panelBotones.add(btnSalir);

        // Añadir el panel de botones a la parte central de la ventana
        add(panelBotones, BorderLayout.CENTER);

        // Agregar ActionListeners a los botones
        btnCrearTarjeta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                crearTarjeta();
            }
        });

        btnBuscarTarjeta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarTarjeta();
            }
        });

        btnVerTarjetas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                verTarjetas();
            }
        });

        btnVerGuardadas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                verTarjetasGuardadas();
            }
        });

        btnAgruparTarjetas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agruparTarjetas();
            }
        });

        btnMejorCalificadas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                verMejorCalificadas();
            }
        });

        btnMasRecientes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                verMasRecientes();
            }
        });

        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);  // Cierra la aplicación
            }
        });

        setVisible(true);
        
        
    }

    private void crearTarjeta() {
        JOptionPane.showMessageDialog(this, "Funcionalidad de creación de tarjeta no implementada.");
    }

    private void buscarTarjeta() {
        JOptionPane.showMessageDialog(this, "Funcionalidad de búsqueda de tarjeta no implementada.");
    }

    private void verTarjetas() {
        JOptionPane.showMessageDialog(this, "Funcionalidad de ver tarjetas no implementada.");
    }

    private void verTarjetasGuardadas() {
        JOptionPane.showMessageDialog(this, "Funcionalidad de ver tarjetas guardadas no implementada.");
    }

    private void agruparTarjetas() {
        JOptionPane.showMessageDialog(this, "Funcionalidad de agrupar tarjetas no implementada.");
    }

    private void verMejorCalificadas() {
        JOptionPane.showMessageDialog(this, "Funcionalidad de ver tarjetas mejor calificadas no implementada.");
    }

    private void verMasRecientes() {
        JOptionPane.showMessageDialog(this, "Funcionalidad de ver tarjetas más recientes no implementada.");
    }

    public static void main(String[] args) {
        new MenuSwing();
    }
}
