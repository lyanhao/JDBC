import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Select {
    public static void main(String[] args) {
        List<Book> book = new Select1().selectBook("%java%", "周%");
    }

    public List<Book> selectBook(String name, String author) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Book> Book = new ArrayList<>();
        try {
            //获取数据库连接
            connection = DBUtil.getConnection();
            //获取数据库操作对象
            String sql = "select * from book where name like ? and author like ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, author);
            rs = ps.executeQuery();
            while (rs.next()) {
                // 前面Java语言用驼峰式，后面数据库语言用下划线分隔
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setName(rs.getString("name"));
                book.setAuthor(rs.getString("author"));
                book.setPrice(rs.getDouble("price"));
                book.setCategoryId(rs.getInt("category_id"));
                Book.add(book);
                System.out.println(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // finally 始终会执行，如果放在 try 里面可能执行不到
            DBUtil.close(connection, ps, rs);
        }
        return Book;
    }
}
