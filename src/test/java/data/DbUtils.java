package data;

import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtils {
    static String url = System.getProperty("db.url");
    static String user = System.getProperty("db.user");
    static String password = System.getProperty("db.password");

    public static void clearTables() {
        val deletePaymentEntity = "DELETE FROM payment_entity";
        val deleteCreditEntity = "DELETE FROM credit_request_entity";
        val deleteOrderEntity = "DELETE FROM order_entity";
        val runner = new QueryRunner();
        try (val conn = DriverManager.getConnection(
                url, user, password)
        ) {
            runner.update(conn, deletePaymentEntity);
            runner.update(conn, deleteCreditEntity);
            runner.update(conn, deleteOrderEntity);

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }


    public static String getPaymentStatus() throws SQLException {
        val statusSQL = "SELECT status FROM payment_entity;";
        return getStatus(statusSQL);
    }

    public static String getCreditStatus() throws SQLException {
        val statusSQL = "SELECT status FROM credit_request_entity;";
        return getStatus(statusSQL);
    }

    public static String countRecords() throws SQLException {
        val countSQL = "SELECT COUNT(*) FROM order_entity;";
        val runner = new QueryRunner();
        Long count = null;

        try (
                val conn = DriverManager.getConnection(
                        url, user, password
                );
        ) {
            count = runner.query(conn, countSQL, new ScalarHandler<>());
            System.out.println(count);
        }
        return Long.toString(count);
    }

    private static String getStatus(String query) throws SQLException {
        val runner = new QueryRunner();
        String data = "";

        try (
                val conn = DriverManager.getConnection(
                        url, user, password
                );
        ) {
            data = runner.query(conn, query, new ScalarHandler<>());
            System.out.println(data);
        }
        return data;
    }
}