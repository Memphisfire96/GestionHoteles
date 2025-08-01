import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.sql.SQLException;

public class Interfaz {
    private JTextField idField, nombreField, ubicacionField, habitacionesField, tarifaField, telefonoField;
    private JTextArea resultadoArea;
    private OperacionesHotel operaciones;

    public Interfaz() {
        operaciones = new OperacionesHotel();
        crearVentana();
    }

    private void crearVentana() {
        JFrame frame = new JFrame("Gestión de Hoteles");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);

        JPanel panel = new JPanel(new GridLayout(9, 2));
        idField = new JTextField();
        nombreField = new JTextField();
        ubicacionField = new JTextField();
        habitacionesField = new JTextField();
        tarifaField = new JTextField();
        telefonoField = new JTextField();

        panel.add(new JLabel("ID:")); panel.add(idField);
        panel.add(new JLabel("Nombre:")); panel.add(nombreField);
        panel.add(new JLabel("Ubicación:")); panel.add(ubicacionField);
        panel.add(new JLabel("Habitaciones:")); panel.add(habitacionesField);
        panel.add(new JLabel("Tarifa:")); panel.add(tarifaField);
        panel.add(new JLabel("Teléfono:")); panel.add(telefonoField);

        JButton insertarBtn = new JButton("Insertar");
        JButton actualizarBtn = new JButton("Actualizar");
        JButton borrarBtn = new JButton("Borrar");
        JButton consultarBtn = new JButton("Consultar");

        panel.add(insertarBtn); panel.add(actualizarBtn);
        panel.add(borrarBtn); panel.add(consultarBtn);

        resultadoArea = new JTextArea();
        resultadoArea.setEditable(false);

        // Acción de botones
        insertarBtn.addActionListener(this::insertarHotel);
        actualizarBtn.addActionListener(this::actualizarHotel);
        borrarBtn.addActionListener(this::borrarHotel);
        consultarBtn.addActionListener(this::consultarHoteles);

        frame.getContentPane().add(BorderLayout.WEST, panel);
        frame.getContentPane().add(BorderLayout.CENTER, new JScrollPane(resultadoArea));
        frame.setVisible(true);
    }

    private void insertarHotel(ActionEvent e) {
        try {
            operaciones.insertarHotel(
                    Integer.parseInt(idField.getText()),
                    nombreField.getText(),
                    ubicacionField.getText(),
                    Integer.parseInt(habitacionesField.getText()),
                    new BigDecimal(tarifaField.getText()),
                    telefonoField.getText()
            );
            mostrarMensaje("Hotel insertado correctamente.");
            limpiarCampos();
        } catch (Exception ex) {
            mostrarMensaje("Error al insertar: " + ex.getMessage());
        }
    }

    private void actualizarHotel(ActionEvent e) {
        try {
            operaciones.actualizarHotel(
                    Integer.parseInt(idField.getText()),
                    nombreField.getText(),
                    ubicacionField.getText(),
                    Integer.parseInt(habitacionesField.getText()),
                    new BigDecimal(tarifaField.getText()),
                    telefonoField.getText()
            );
            mostrarMensaje("Hotel actualizado correctamente.");
            limpiarCampos();
        } catch (Exception ex) {
            mostrarMensaje("Error al actualizar: " + ex.getMessage());
        }
    }

    private void borrarHotel(ActionEvent e) {
        try {
            operaciones.borrarHotel(Integer.parseInt(idField.getText()));
            mostrarMensaje("Hotel eliminado correctamente.");
            limpiarCampos();
        } catch (Exception ex) {
            mostrarMensaje("Error al eliminar: " + ex.getMessage());
        }
    }

    private void consultarHoteles(ActionEvent e) {
        try {
            String resultado = operaciones.consultarHoteles();
            resultadoArea.setText(resultado);
        } catch (SQLException ex) {
            mostrarMensaje("Error al consultar: " + ex.getMessage());
        }
    }

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

    private void limpiarCampos() {
        idField.setText("");
        nombreField.setText("");
        ubicacionField.setText("");
        habitacionesField.setText("");
        tarifaField.setText("");
        telefonoField.setText("");
    }
}
