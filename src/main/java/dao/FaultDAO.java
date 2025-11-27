package dao;

import model.Fault;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FaultDAO {

    public void addFault(Fault fault) throws SQLException {
        String sql = "INSERT INTO faults(name, symptom, solution, device_id) VALUES(?,?,?,?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, fault.getName());
            stmt.setString(2, fault.getSymptom());
            stmt.setString(3, fault.getSolution());
            stmt.setInt(4, fault.getDeviceId());
            stmt.executeUpdate();
        }
    }

    public List<Fault> getAllFaults() throws SQLException {
        List<Fault> faults = new ArrayList<>();
        String sql = "SELECT * FROM faults";
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                faults.add(Fault.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .symptom(rs.getString("symptom"))
                        .solution(rs.getString("solution"))
                        .deviceId(rs.getInt("device_id"))
                        .build());
            }
        }
        return faults;
    }
}
