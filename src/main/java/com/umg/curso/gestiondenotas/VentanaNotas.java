package com.umg.curso.gestiondenotas;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Marvin Antonio
 */
public class VentanaNotas extends JFrame {

    private JTextField campoNombre, campoCalificaciones;
    private JTextArea areaResultado;

    public VentanaNotas() {

        //CONFIGURACIONES BÁSICAS DE LA VENTANA:
        setTitle("GESTIÓN DE NOTAS");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout()); // USAMOS GridBagLayout PARA MEJOR DISTRIBUCIÓN
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        //PANEL SUPERIOR (IMAGEN Y TÍTULO):
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel titulo = new JLabel("CONTROL DE NOTAS");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));

        //CARGA DE IMAGEN:
        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/imagenes/notas.png"));
        Image imagen = iconoOriginal.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH); // Ajusta el tamaño
        ImageIcon icono = new ImageIcon(imagen);
        JLabel etiquetaImagen = new JLabel(icono);

        panelSuperior.add(etiquetaImagen);
        panelSuperior.add(titulo);

        //AGREGAR PANEL SUPERIOR:
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(panelSuperior, gbc);

        //CAMPOS DE ENTRADA:
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;

        //NOMBRE:
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Nombre:"), gbc);

        gbc.gridx = 1;
        campoNombre = new JTextField(15);
        add(campoNombre, gbc);

        //CALIFICACIONES:
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Ingrese 5 notas:"), gbc);

        gbc.gridx = 1;
        campoCalificaciones = new JTextField(15);
        add(campoCalificaciones, gbc);

        //PANEL DE BOTONES:
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnAgregar = new JButton("Agregar");
        JButton btnMostrar = new JButton("Mostrar");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnMostrar);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(panelBotones, gbc);

        //ÁREA DE RESULTADOS:
        areaResultado = new JTextArea(8, 30);
        areaResultado.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaResultado);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        add(scrollPane, gbc);

        //EVENTOS PARA LOS BOTONES:
        btnAgregar.addActionListener(e -> agregarEstudiante());
        btnMostrar.addActionListener(e -> mostrarResultados());

        setVisible(true);
    }

    //MÉTODO PARA AGREGAR UN ESTUDIANTE
    private void agregarEstudiante() {
        try {
            String nombre = campoNombre.getText().trim();
            String[] califStr = campoCalificaciones.getText().trim().split(",");

            if (califStr.length != 5) {
                JOptionPane.showMessageDialog(this, "Debe ingresar 5 calificaciones.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double[] calificaciones = new double[5];
            for (int i = 0; i < 5; i++) {
                calificaciones[i] = Double.parseDouble(califStr[i].trim());
            }

            //CREAR Y AGREGAR ESTUDIANTE A LA LISTA:
            Estudiante nuevoEstudiante = new Estudiante(nombre, calificaciones);
            Estudiante.agregarEstudiante(nuevoEstudiante);

            JOptionPane.showMessageDialog(this, "Estudiante agregado con éxito");

            //LIMPIAR CAMPOS:
            campoNombre.setText("");
            campoCalificaciones.setText("");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ingrese valores válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //MÉTODO PARA MOSTRAR LOS RESULTADOS:
    private void mostrarResultados() {
        if (Estudiante.obtenerLista().isEmpty()) {
            areaResultado.setText("No hay estudiantes registrados.");
            return;
        }

        StringBuilder resultado = new StringBuilder("Lista de Estudiantes y Promedios:\n");
        for (Estudiante e : Estudiante.obtenerLista()) {
            resultado.append(e.obtenerInfo()).append("\n");
        }

        resultado.append("\nEstadísticas: \n");
        resultado.append("Promedio general: ").append(String.format("%.2f", Estudiante.calcularPromedioGeneral())).append("\n");
        resultado.append(Estudiante.obtenerNotaMasAlta()).append("\n");
        resultado.append(Estudiante.obtenerNotaMasBaja()).append("\n");

        areaResultado.setText(resultado.toString());
    }
}
