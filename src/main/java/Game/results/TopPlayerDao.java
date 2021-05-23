package Game.results;

import util.jpa.GenericJpaDao;

import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

/**
 * DAO class for the {@link TopPlayer} entity.
 */
public class TopPlayerDao extends GenericJpaDao<TopPlayer> {

    private static TopPlayerDao instance;

    private TopPlayerDao() {
        super(TopPlayer.class);
    }

    public static TopPlayerDao getInstance() {
        if (instance == null) {
            instance = new TopPlayerDao();
            instance.setEntityManager(Persistence.createEntityManagerFactory("jpa-persistence-unit-1").createEntityManager());
        }
        return instance;
    }




    /**
     *
     * @param player is  the player name.
     */
    public TopPlayer update(String player) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            TopPlayer Uplayer = entityManager.find(TopPlayer.class, player);
            Uplayer.setNumOfGames(Uplayer.getNumOfGames()+1);
            return Uplayer;
        } catch (Exception ex) {
            return null;
        } finally {
            tx.commit();
        }
    }


    /**
     * Returns the list of {@code n} best results with respect to the number
     * of games won.
     *
     * @param n the maximum number of results to be returned
     * @return the list of {@code n} best results with respect to the number of
     * games won
     */
    public List<TopPlayer> findBest(int n) {

        return entityManager.createQuery("SELECT r FROM TopPlayer r ORDER BY r.numOfGames DESC",TopPlayer.class)
                .setMaxResults(n)
                .getResultList();

    }

}
