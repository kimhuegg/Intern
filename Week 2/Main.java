import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws SQLException {

        //ket noi database

        Connection connection = jdbcConector.getJBDC();

        if(connection !=null){
            System.out.println("Database connected !");
        }else {
            System.out.println("Fail !");
        }

        // lay data

        Statement stmt1 = connection.createStatement();

        String sql1 ="select * from country;";
        ResultSet resultSet1 =  stmt1.executeQuery(sql1);

        List<Country> listCountry = new ArrayList<>();
        Stream<Country> streamCountry = listCountry.stream();

        Statement stmt2 = connection.createStatement();

        String sql2 ="select * from city;";
        ResultSet resultSet2=  stmt2.executeQuery(sql2);

        List<City> listCity = new ArrayList<>();
        Stream<City> streamCity = listCity.stream();

        //nhap data vào collection

        while(resultSet1.next()){
            Country country = new Country(resultSet1.getString(1),resultSet1.getString(2),
                    resultSet1.getString(3),resultSet1.getInt(4),resultSet1.getInt(5),
                    resultSet1.getInt(6),resultSet1.getInt(7));
            listCountry.add(country);
        }

        while(resultSet2.next()){
            City city = new City(resultSet2.getInt(1),resultSet2.getString(2),
                    resultSet2.getInt(3),resultSet2.getString(4));
            listCity.add(city);
        }

        //chon thanh pho dong dan nhat
        City max = getMaxPopulationCity(streamCity);
        System.out.println("Thanh pho dong dan nhat: " + max.getPopulation());

        //sắp xếp quốc gia có dân số giảm dần
        sortWithPopulation(streamCountry);

    }
    public static City getMaxPopulationCity(Stream<City> stream) {
        City max = stream.max((x1,x2) -> x1.getPopulation() - x2.getPopulation()).get();
        return max;
    }
    public static void sortWithPopulation(Stream<Country> stream){
        System.out.println("Sắp xếp quốc gia theo dân số...");
        stream.sorted((o1, o2) -> o1.getPopulation() - o2.getPopulation())
                .forEach(s -> System.out.println(s.getName() + " " + s.getPopulation()));
    }
}
