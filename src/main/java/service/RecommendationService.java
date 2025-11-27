package service;

import dao.FaultDAO;
import model.Fault;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class RecommendationService {
    private final FaultDAO faultDAO = new FaultDAO();

    public List<Fault> getPossibleFaults(String symptom) throws SQLException {
        List<Fault> allFaults = faultDAO.getAllFaults();
        return allFaults.stream()
                .filter(f -> f.getSymptom().toLowerCase().contains(symptom.toLowerCase()))
                .collect(Collectors.toList());
    }
}
