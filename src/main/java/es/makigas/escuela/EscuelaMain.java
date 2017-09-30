package es.makigas.escuela;

import es.makigas.escuela.dao.mysql.MySQLDaoManager;
import es.makigas.escuela.frames.LoginDialog;
import es.makigas.escuela.frames.MainMenu;
import java.sql.SQLException;

public class EscuelaMain {
    
    public static void main(String[] args) throws SQLException {
        LoginDialog dialog = new LoginDialog();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        if (dialog.isAceptado()) {
            String username = dialog.getUsuario();
            String password = dialog.getPassword();
            MySQLDaoManager manager = new MySQLDaoManager("localhost", username, password, "escuela");
            MainMenu menu = new MainMenu(manager);
            menu.setLocationRelativeTo(null);
            menu.setVisible(true);
        }
    }
    
}
