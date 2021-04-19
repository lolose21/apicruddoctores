package repositories;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Doctor;

public class RepositoryDoctores {

    private Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new SQLServerDriver());
        String cadena
                = "jdbc:sqlserver://sqlserverjavaisma.database.windows.net:1433;databaseName=SQLAZURE";
        Connection cn = DriverManager.getConnection(cadena, "admiinsql", "Admin123");
        return cn;
    }

    public List<Doctor> getDoctor() throws SQLException {
        Connection cn = this.getConnection();
        String sql = "select * from doctor";
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        ArrayList<Doctor> doctores = new ArrayList<>();
        while (rs.next()) {
            int hospitalcod = rs.getInt("HOSPITAL_COD");
            int iddoctor = rs.getInt("DOCTOR_NO");
            String apellido = rs.getString("APELLIDO");
            String especialidad = rs.getString("ESPECIALIDAD");
            int salario = rs.getInt("salario");
            Doctor doctor = new Doctor(hospitalcod, iddoctor, apellido, especialidad, salario);
            doctores.add(doctor);
        }
        rs.close();
        cn.close();
        return doctores;
    }

    public Doctor findDoctorId(int id) throws SQLException {
        Connection cn = this.getConnection();
        String sql = "select * from doctor where doctor_no=?";
        PreparedStatement pst = cn.prepareStatement(sql);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            int hospitalcod = rs.getInt("HOSPITAL_COD");
            int iddoctor = rs.getInt("DOCTOR_NO");
            String apellido = rs.getString("APELLIDO");
            String especialidad = rs.getString("ESPECIALIDAD");
            int salario = rs.getInt("salario");
            Doctor doctor = new Doctor(hospitalcod, iddoctor, apellido, especialidad, salario);
            rs.close();
            cn.close();
            return doctor;
        } else {
            cn.close();
            rs.close();
            return null;
        }
    }

    public List<Doctor> findDoctorIdhospital(int id) throws SQLException {
        Connection cn = this.getConnection();
        String sql = "select * from doctor where hospital_cod=?";
        PreparedStatement pst = cn.prepareStatement(sql);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        ArrayList<Doctor> doctores = new ArrayList<>();
        while (rs.next()) {
            int hospitalcod = rs.getInt("HOSPITAL_COD");
            int iddoctor = rs.getInt("DOCTOR_NO");
            String apellido = rs.getString("APELLIDO");
            String especialidad = rs.getString("ESPECIALIDAD");
            int salario = rs.getInt("salario");
            Doctor doctor = new Doctor(hospitalcod, iddoctor, apellido, especialidad, salario);
            doctores.add(doctor);
            rs.close();
            cn.close();

        }
        if (doctores.isEmpty()) {

            return null;
        } else {
            return doctores;
        }
    }

    public void insertarDoctor(int hospitalcod, int doctorno, String apellido, String especialidad, int salario) throws SQLException {
        Connection cn = this.getConnection();
        String sql = "insert into doctor values(?,?,?,?,?)";
        PreparedStatement pst = cn.prepareStatement(sql);
        pst.setInt(1, hospitalcod);
        pst.setInt(2, doctorno);
        pst.setString(3, apellido);
        pst.setString(4, especialidad);
        pst.setInt(5, salario);
        pst.executeUpdate();
        cn.close();

    }

    public void modificarDoctor(int hospitalcod, int doctorno, String apellido, String especialidad, int salario) throws SQLException {
        Connection cn = this.getConnection();
        String sql = "update doctor set hospital_cod=? , apellido=? , especialidad=? , salario=? where doctor_no=?";
        PreparedStatement pst = cn.prepareStatement(sql);
        pst.setInt(1, hospitalcod);
        pst.setString(2, apellido);
        pst.setString(3, especialidad);
        pst.setInt(4, salario);
        pst.setInt(5, doctorno);
        pst.executeUpdate();
        cn.close();
    }

    public void eliminarDoctor(int doctorno) throws SQLException {
        Connection cn = this.getConnection();
        String sql = "delete from doctor where doctor_no=?";
        PreparedStatement pst = cn.prepareStatement(sql);
        pst.setInt(1, doctorno);
        pst.executeUpdate();
        cn.close();
    }
}
