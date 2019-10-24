import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.*;

/**
 * * 1.加载驱动
 * * 2.建立连接
 * * 3.创建执行对象 Statement
 * * 4.执行 sql
 * * 5.处理结果集 ResultSet
 * * 6.释放资源（connection | statement | resultSet）正向定义，反向释放
 */
public class DBUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/ebook";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "123456";

    // 单例设计模式
    private static MysqlDataSource DATASOURCE = new MysqlDataSource();

    static {
        DATASOURCE.setUrl(URL);
        DATASOURCE.setUser(USER_NAME);
        DATASOURCE.setPassword(PASSWORD);

    }

    /**
     * 获取数据库连接
     * @return
     */
    public static Connection getConnection() {
        try {
            return DATASOURCE.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("数据库获取连接失败");
        }
    }

    /**
     * 释放数据库资源
     *
     * @param connection 数据库连接
     * @param ps         数据库操作对象
     * @param rs         操作结果集
     */
    public static void close(Connection connection, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("释放数据库资源错误");
        }
    }
}
