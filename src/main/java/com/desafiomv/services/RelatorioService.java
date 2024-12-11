package com.desafiomv.services;


import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class RelatorioService {

    @Value("${banco.username}")
    private String bancoUsername;

    @Value("${banco.senha}")
    private String bancoPassword;


    public RelatorioService() {
    }

    public void gerarRelatorio() {
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1524:xe";
        String username = "SYSTEM";
        String password = "OR4CL3@TesTE";
        String filePath = "relatorio.txt";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             CallableStatement callableStatement = connection.prepareCall("{call ClientReportPackage.GetClientReport()}");
             BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            try (Statement stmt = connection.createStatement()) {
                stmt.execute("BEGIN DBMS_OUTPUT.ENABLE(NULL); END;");
            }

            callableStatement.execute();

            try (CallableStatement outputStmt = connection.prepareCall(
                    "DECLARE " +
                            "    l_line VARCHAR2(32767); " +
                            "    l_done NUMBER; " +
                            "    l_buffer LONG; " +
                            "BEGIN " +
                            "    LOOP " +
                            "        EXIT WHEN LENGTH(l_buffer) + 255 > 32767 OR l_done = 1; " +
                            "        DBMS_OUTPUT.GET_LINE(l_line, l_done); " +
                            "        l_buffer := l_buffer || l_line || CHR(10); " +
                            "    END LOOP; " +
                            "    ? := l_buffer; " +
                            "END;")) {
                outputStmt.registerOutParameter(1, Types.VARCHAR);
                outputStmt.execute();
                String dbmsOutput = outputStmt.getString(1);

                writer.write(dbmsOutput);
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}