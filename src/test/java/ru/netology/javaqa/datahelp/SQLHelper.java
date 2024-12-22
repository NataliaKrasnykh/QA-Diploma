package ru.netology.javaqa.datahelp;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;



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
    public static String getDebitInfo() {
        var conn = getConn();
        var codeSQL = "SELECT * FROM payment_entity ORDER BY created DESC LIMIT 1;";
        return QUERY_RUNNER.query(conn, codeSQL, new ScalarHandler<>());
    }

    @SneakyThrows
    public static String getCreditInfo() {
        var conn = getConn();
        var codeSQL = "SELECT * FROM credit_request_entity ORDER BY created DESC LIMIT 1;";
        return QUERY_RUNNER.query(conn, codeSQL, new ScalarHandler<>());
    }
}