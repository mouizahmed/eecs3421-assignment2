import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The purpose of this class is to create the MiddleTier and provide database
 * interaction functions/actions.
 * 
 * @author Patrick Koziarski & Mouiz Ahmed
 */
public class A02MiddleTier {
	// This class will contain your code for interacting with Database, acquire the
	// query result and display it in the GUI text area.
	private static Connection conn;
	private static Statement stmt;
	private static ResultSet rs;
	private static ResultSet rs1;
	private static String sql;
	private static int ID;

	/**
	 * The purpose of this function is to connect to the database.
	 */
	public A02MiddleTier() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/a02schema", "root", "password");
			if (conn != null) {
				initialize();
				System.out.println("Connected to the database");
			} else {
				System.out.println("Failed to make connection!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Generate a unique ID for the new event.
	 */
	public void initialize() {
		try {
			stmt = conn.createStatement();
			sql = "SELECT ID FROM Event WHERE ID=(SELECT MAX(ID) FROM Event)";
			rs1 = stmt.executeQuery(sql);
			while (rs1.next()) {
				ID += rs1.getInt("ID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function will insert a new record in the Event table and EventConference
	 * table.
	 * 
	 * @param evNameT
	 * @param evDateT
	 */
	public static void insertEventConference(String evNameT, String evDateT) {
		try {
			ID += 1;
			stmt = conn.createStatement();
			sql = "INSERT INTO Event (Name, ID) VALUES ('" + evNameT + "', '" + ID + "')"; // insert name and id
			stmt.executeUpdate(sql);
			sql = "INSERT INTO EventConference (evDate, eventID) VALUES ('" + evDateT + "', '" + ID + "')"; // insert date and id
			stmt.executeUpdate(sql);
			System.out.println("Inserted records into Event, and EventConference table successfully.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function will insert a new record in EventJournal table.
	 * 
	 * @param evNameT
	 * @param evDateT
	 * @param jourNameT
	 */
	public static void insertEventJournal(String evNameT, String jourNameT) {
		try {
			ID += 1;
			stmt = conn.createStatement();
			sql = "INSERT INTO Event (Name, ID) VALUES ('" + evNameT + "', '" + ID + "')"; // insert name and id
			stmt.executeUpdate(sql);
			sql = "INSERT INTO EventJournal (eventID, JournalName) VALUES ('" + ID + "', '" + jourNameT + "')"; // insert id and journal name
			stmt.executeUpdate(sql);
			System.out.println("Inserted records into Event and EventJournal successfully.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function will insert a new record in EventBook table.
	 * 
	 * @param evNameT
	 * @param string
	 * @param evDateT
	 */
	public static void insertEventBook(String evNameT) {
		try {
			ID += 1;
			stmt = conn.createStatement();
			sql = "INSERT INTO Event (Name, ID) VALUES ('" + evNameT + "', '" + ID + "')"; // insert name and id
			stmt.executeUpdate(sql);
			sql = "INSERT INTO EventBook (eventID) VALUES ('" + ID + "')"; // insert id
			stmt.executeUpdate(sql);
			System.out.println("Inserted records into Event and EventBook table successfully.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function will query the database and return the result set to the caller
	 * 
	 * @return result
	 */
	public static String queryEventConference() {
		String result = "";
		try {
			// Query all results from Event table
			sql = "SELECT * FROM Event";
			rs = stmt.executeQuery(sql);
			result += "EVENT TABLE \n";
			while (rs.next()) {
				result += "ID: " + rs.getString("ID") + ", Name: " + rs.getString("Name") + "\n";
			}
			result += "\n";

			// Query all results from EventConference table
			stmt = conn.createStatement();
			sql = "SELECT * FROM EventConference";
			rs = stmt.executeQuery(sql);
			result += "EVENTCONFERENCE TABLE \n";
			while (rs.next()) {
				result += "EventID: " + rs.getString("eventID") + ", EvDate: " + rs.getString("evDate") + "\n";
			}
			result += "\n";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * This function will query the database and return the result set to the caller
	 * 
	 * @return result
	 */
	public static String queryEventJournal() {
		String result = "";
		try {
			// Query all results from Event table
			sql = "SELECT * FROM Event";
			rs = stmt.executeQuery(sql);
			result += "EVENT TABLE \n";
			while (rs.next()) {
				result += "ID: " + rs.getString("ID") + ", Name: " + rs.getString("Name") + "\n";
			}
			result += "\n";

			// Query all results from EventJournal table
			stmt = conn.createStatement();
			sql = "SELECT * FROM EventJournal";
			rs = stmt.executeQuery(sql);
			result += "EVENTJOURNAL TABLE \n";
			while (rs.next()) {
				result += "EventID: " + rs.getString("eventID") + ", JournalName: " + rs.getString("JournalName") + "\n";
			}
			result += "\n";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * This function will query the database and return the result set to the caller
	 * 
	 * @return result
	 */
	public static String queryEventBook() {
		String result = "";
		try {
			// Query all results from Event table
			sql = "SELECT * FROM Event";
			rs = stmt.executeQuery(sql);
			result += "EVENT TABLE \n";
			while (rs.next()) {
				result += "ID: " + rs.getString("ID") + ", Name: " + rs.getString("Name") + "\n";
			}
			result += "\n";

			// Query all results from EventBook table
			stmt = conn.createStatement();
			sql = "SELECT * FROM EventBook";
			rs = stmt.executeQuery(sql);
			result += "EVENTBOOK TABLE \n";
			while (rs.next()) {
				result += "EventID: " + rs.getString("eventID") + "\n";
			}
			result += "\n";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}