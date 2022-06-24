package uz.jl.dao.auth;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import uz.jl.dao.GenericDAO;
import uz.jl.domains.auth.AuthUser;
import uz.jl.enums.AuthRole;

import javax.swing.text.html.Option;
import java.util.Objects;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthUserDAO extends GenericDAO<AuthUser, Long> {
    private static AuthUserDAO instance;

    public static AuthUserDAO getInstance() {
        if (Objects.isNull(instance)) {
            instance = new AuthUserDAO();
        }
        return instance;
    }



    public Optional<AuthUser> findByUserName(String username) {
        Session session = getSession();
        session.beginTransaction();
        Query<AuthUser> query = session
                .createQuery("select t from AuthUser t where lower(t.username) = lower(:username) ",
                        AuthUser.class);
        query.setParameter("username", username);
        Optional<AuthUser> result = Optional.ofNullable(query.getSingleResultOrNull());
        session.close();
        return result;
    }

    public void update(Long user_id, AuthRole role) {

        Session session = getSession();
        session.beginTransaction();
        AuthUser authuser = session.load(AuthUser.class, user_id);
        authuser.setRole(role);
        session.update(authuser);
        session.getTransaction().commit();
        session.close();
    }
}
