package dao;

import model.RepairGuide;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepairGuideDAO {

    public void addGuide(RepairGuide guide) throws SQLException {
        String sql = "INSERT INTO repair_guides(fault_id, media_type, path) VALUES(?,?,?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, guide.getFaultId());
            stmt.setString(2, guide.getMediaType());
            stmt.setString(3, guide.getPath());
            stmt.executeUpdate();
        }
    }

    public List<RepairGuide> getAllGuides() throws SQLException {
        List<RepairGuide> guides = new ArrayList<>();
        String sql = "SELECT * FROM repair_guides";
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                guides.add(RepairGuide.builder()
                        .id(rs.getInt("id"))
                        .faultId(rs.getInt("fault_id"))
                        .mediaType(rs.getString("media_type"))
                        .path(rs.getString("path"))
                        .build());
            }
        }
        return guides;
    }
}
