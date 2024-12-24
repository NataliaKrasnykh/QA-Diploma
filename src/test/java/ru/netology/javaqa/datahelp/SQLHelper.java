package ru.netology.javaqa.datahelp;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class SQLHelper {
    private static QueryRunner QUERY_RUNNER = new QueryRunner();

    public SQLHelper() {
    }

    private static String url = System.getProperty("db.url");
    private static String user = System.getProperty("db.user");
    private static String password = System.getProperty("db.password");


    @SneakyThrows
    private static Connection getConn() throws SQLException {
        return DriverManager.getConnection(url, user, password);

    }

    @SneakyThrows
    public static void cleanBase() {
        var conn = getConn();
        QUERY_RUNNER.execute(conn, "DELETE FROM credit_request_entity");
        QUERY_RUNNER.execute(conn, "DELETE FROM order_entity");
        QUERY_RUNNER.execute(conn, "DELETE FROM payment_entity");

    }

    @SneakyThrows
    public static String getCreditInfo() {
        var codeSQL = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1;";
        var conn = getConn();
        var countStatement = conn.createStatement();
        var resultSet = countStatement.executeQuery(codeSQL);
        if (resultSet.next()) {
            return resultSet.getString("status");
        }
        return null;
    }

    @SneakyThrows
    public static String getDebitInfo() {
        var codeSQL = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1;";
        var conn = getConn();
        var countStatement = conn.createStatement();
        var resultSet = countStatement.executeQuery(codeSQL);
        if (resultSet.next()) {
            return resultSet.getString("status");
        }
        return null;
    }
}