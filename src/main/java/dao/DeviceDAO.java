package dao;

import model.Device;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeviceDAO {

    public void addDevice(Device device) throws SQLException {
        String sql = "INSERT INTO devices(model, brand, type) VALUES(?,?,?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, device.getModel());
            stmt.setString(2, device.getBrand());
            stmt.setString(3, device.getType());
            stmt.executeUpdate();
        }
    }

    public List<Device> getAllDevices() throws SQLException {
        List<Device> devices = new ArrayList<>();
        String sql = "SELECT * FROM devices";
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                devices.add(Device.builder()
                        .id(rs.getInt("id"))
                        .model(rs.getString("model"))
                        .brand(rs.getString("brand"))
                        .type(rs.getString("type"))
                        .build());
            }
        }
        return devices;
    }
}
