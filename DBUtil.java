import java.sql.*;

public class DBUtil {
    /**
     * 1.加载驱动
     * 2.建立连接
     * 3.创建执行对象 Statement
     * 4.执行 sql
     * 5.处理结果集 ResultSet
     * 6.释放资源（connection | statement | resultSet）正向定义，反向释放
     *
     * @param args
     */

    private static final String URL = "jdbc:mysql://localhost:3306/ebook";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "123456";

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            // 加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 获取连接
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            System.out.println(connection);
            // 获取执行对象
            statement = connection.createStatement();
            // 执行sql
            String sql = "select * from book";
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String author = resultSet.getString("author");
                Double price = resultSet.getDouble("price");
                System.out.println(String.format("id = %s\t,name = %s\t, author = %s\t, price = %f\t", id, name, author, price));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // 正向定义，反向释放
            // 由于定义时为null,若赋值出现异常,则有可能为空,所以要判断
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
