package dao.impl;

import util.ConnectionBuilder;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.stream.Collectors;

public class DbInit {
    public static void startUp() throws Exception {

        URL urlInitDb = UserDaoimplTest.class.getClassLoader().getResource("scripts/Init.sql");
        URL urlInsertDb = UserDaoimplTest.class.getClassLoader().getResource("scripts/Insert.sql");
        List<String> strInitDb = Files.readAllLines(Paths.get(urlInitDb.toURI()));
        String sqlInitDb = strInitDb.stream().collect(Collectors.joining());

        List<String> strInsertDb = Files.readAllLines(Paths.get(urlInsertDb.toURI()));
        String sqlInsertDb = strInsertDb.stream().collect(Collectors.joining());


        try (Connection connection = ConnectionBuilder.getConnection();
             Statement statement = connection.createStatement()){

            statement.executeUpdate(sqlInitDb);
            statement.executeUpdate(sqlInsertDb);
        }
    }
}
