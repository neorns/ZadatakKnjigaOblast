package zadaci;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import model.Knjiga;

import java.io.IOException;
import java.util.List;
import java.util.Random;

/**
 * Created by androiddevelopment on 16.1.17..
 */
public class KnjigaNit extends Thread {
    private String imeClana;
    private Knjiga knjiga;
    static Dao<Knjiga, Integer> knjigaDao;

    public String getImeClana() {
        return imeClana;
    }

    public static void main(String[] args) {
        ConnectionSource connectionSource = null;
        try {
           connectionSource = new JdbcConnectionSource("jdbc:sqlite:knjigaOblast.db");
            knjigaDao = DaoManager.createDao(connectionSource, Knjiga.class);


            List<Knjiga> knjige = knjigaDao.queryForAll();
            for (Knjiga k : knjige)
                System.out.println(k);

            //////////////////////////////////////////////////////////////

        }catch(
            Exception e)

    {
        e.printStackTrace();
    }finally

    {
        if (connectionSource != null) {
            try {
                connectionSource.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    }

    public void setImeClana(String imeClana) {
        this.imeClana = imeClana;
    }

    public Knjiga getKnjiga() {
        return knjiga;
    }

    public void setKnjiga(Knjiga knjiga) {
        this.knjiga = knjiga;
    }

    @Override
    public void run(){

        Random random = new Random();
        long vreme = Math.round(random.nextDouble() * 5000);
        try {
            knjiga.setPrisutna(false);

            this.sleep(vreme);
            knjiga.setPrisutna(true);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
