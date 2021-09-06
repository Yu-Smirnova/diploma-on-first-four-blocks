package ru.netology.web.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;

public class SqlHelper {
    private static String url= "jdbc:mysql://localhost:3306/app";

    private SqlHelper(){}

    @SneakyThrows
    public static String getCreditId(){
        var creditIdSQL = "SELECT credit_id FROM order_entity WHERE created > NOW() -INTERVAL 15 SECOND";
        var runner = new QueryRunner();
        var conn = DriverManager.getConnection(
                url, "app", "pass");
        var creditId = runner.query(conn, creditIdSQL, new ScalarHandler<String>());
        return creditId;
    }

    @SneakyThrows
    public static String getPaymentId(){
        var creditIdSQL = "SELECT payment_id FROM order_entity WHERE created > NOW() -INTERVAL 15 SECOND;";
        var runner = new QueryRunner();
        var conn = DriverManager.getConnection(
                url, "app", "pass");
        var paymentId = runner.query(conn, creditIdSQL, new ScalarHandler<String>());
        return paymentId;
    }

    @SneakyThrows
    public static String getCreditBankId(){
        var creditBankIdSQL = "SELECT bank_id FROM credit_request_entity WHERE created > NOW() -INTERVAL 15 SECOND;";
        var runner = new QueryRunner();
        var conn = DriverManager.getConnection(
                url, "app", "pass");
        var creditBankId = runner.query(conn, creditBankIdSQL, new ScalarHandler<String>());
        return creditBankId;
    }

    @SneakyThrows
    public static String getTransactionId(){
        var transactionIdSQL = "SELECT transaction_id FROM payment_entity WHERE created > NOW() -INTERVAL 15 SECOND;";
        var runner = new QueryRunner();
        var conn = DriverManager.getConnection(
                url, "app", "pass");
        var transactionId = runner.query(conn, transactionIdSQL, new ScalarHandler<String>());
        return transactionId;
    }

    @SneakyThrows
    public static String getPaymentStatus(String paymentId){
        var paymentStatusSQL = "SELECT status FROM payment_entity WHERE transaction_id=?";
        var runner = new QueryRunner();
        var conn = DriverManager.getConnection(
                url, "app", "pass");
        var paymentStatus = runner.query(conn, paymentStatusSQL, new ScalarHandler<String>(), paymentId);
        return paymentStatus;
    }

    @SneakyThrows
    public static String geCreditStatus(String bankId){
        var creditStatusSQL = "SELECT status FROM credit_request_entity WHERE bank_id=?";
        var runner = new QueryRunner();
        var conn = DriverManager.getConnection(
                url, "app", "pass");
        var creditStatus = runner.query(conn, creditStatusSQL, new ScalarHandler<String>(), bankId);
        return creditStatus;
    }
}
