package zadaci;

import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;
import model.Oblast;

import java.io.IOException;
import java.util.List;

import static model.Oblast.POLJE_NAZIV;
import static zadaci.Zadatak2DodavanjeVrednosti.oblastDao;

/**
 * Created by androiddevelopment on 16.1.17..
 */
public class Zadatak3IzmenaVrednosti {
    public static void main(String[] args) {
        ConnectionSource connectionSource = null;
        try {
            // create our data-source for the database
            connectionSource = new JdbcConnectionSource("jdbc:sqlite:knjigaOblast.db");
            oblastDao = DaoManager.createDao(connectionSource, Oblast.class);

            List<Oblast> oblasti = oblastDao.queryForAll();
            for (Oblast o : oblasti)
                System.out.println(o);

            QueryBuilder<Oblast,Integer> oblastQuery=oblastDao.queryBuilder();
            Where<Oblast,Integer> where = oblastQuery.where();
            where.eq(POLJE_NAZIV, "Activity klasa");
            PreparedQuery<Oblast> oblastPripremljen = oblastQuery.prepare();
            Oblast oblastZaIzmenu = oblastDao.queryForFirst(oblastPripremljen);

            if (oblastZaIzmenu != null) {
                oblastZaIzmenu.setPocetnaStrana(35);
                oblastDao.update(oblastZaIzmenu);
            }

            oblasti = oblastDao.queryForAll();
            for (Oblast o : oblasti)
                System.out.println(o);


        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (connectionSource != null) {
                try {
                    connectionSource.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
