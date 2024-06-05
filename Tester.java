import java.sql.*;

public class Tester {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection(
                 "jdbc:mysql://127.0.0.1:3306/student_schema",
                "root",
                "Kohinoormysql@01"

        );
        Statement statement = connection.createStatement();
        ResultSet resultset = statement.executeQuery("select * from new_student");

        //while(resultset.next()){
          //  System.out.println(resultset.getString("first_name"));
         //System.out.println(resultset.getString("last_name"));
       //}
        // Get ResultSetMetaData to get column information
        ResultSetMetaData metaData = resultset.getMetaData();
        int columnCount = metaData.getColumnCount();

        // Print column names
        for (int i = 1; i <= columnCount; i++) {
            System.out.print(metaData.getColumnName(i) + "\t");
        }
        System.out.println();

        // Print rows
        while (resultset.next()) {
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(resultset.getString(i) + "\t");
            }
            System.out.println();
        }
    }
}
