package it.polito.tdp.borders.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import it.polito.tdp.borders.model.Border;
import it.polito.tdp.borders.model.Country;

public class BordersDAO {

	public List<Country> loadAllCountries() {

		String sql = "SELECT ccode, StateAbb, StateNme FROM country ORDER BY StateAbb";
		List<Country> result = new ArrayList<Country>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				result.add(new Country(rs.getInt("ccode"), rs.getString("StateAbb"), rs.getString("StateNme")));
			}
			
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

	public List<Border> getCountryPairs(int anno) {

		System.out.println("TODO -- BordersDAO -- getCountryPairs(int anno)");
		return new ArrayList<Border>();
	}
	
	public List<Confini> getCountrySpecifici(int num){
		String sql = "SELECT CCode, StateAbb,COUNT(*) AS confinanti "
				+ "FROM contiguity AS c, country AS cc "
				+ "WHERE c.state1no=cc.CCode "
				+ "AND YEAR <= ? "
				+ "AND conttype = 1 "
				+ "GROUP BY CCode";
		List<Confini> result = new LinkedList<Confini>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, num);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				result.add(new Confini(rs.getInt("CCode"), rs.getString("StateAbb"), rs.getInt("confinanti")));
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
		
	}
	
	public List<String> trovaStati(String stato){
		String sql = "SELECT State2ab "
				+ "FROM contiguity AS c, country AS cc "
				+ "WHERE c.state1no=cc.CCode "
				+ "AND YEAR <= 1950 "
				+ "AND state1ab = ? "
				+ "AND conttype = 1";
		List<String> result = new LinkedList<String>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, stato);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				result.add(rs.getString("State2ab"));
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}
}
