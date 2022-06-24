package uz.jl.service.auth;

import jakarta.transaction.Transactional;
import lombok.NonNull;
import uz.jl.configs.ApplicationContextHolder;
import uz.jl.dao.AbstractDAO;
import uz.jl.dao.auth.AuthUserDAO;
import uz.jl.domains.auth.AuthUser;
import uz.jl.enums.AuthRole;
import uz.jl.service.GenericCRUDService;
import uz.jl.utils.BaseUtils;
import uz.jl.vo.auth.AuthUserCreateVO;
import uz.jl.vo.auth.AuthUserUpdateVO;
import uz.jl.vo.auth.AuthUserVO;
import uz.jl.vo.auth.Session;
import uz.jl.vo.http.Response;

import java.util.List;
import java.util.Optional;

public class AuthUserService extends AbstractDAO<AuthUserDAO> implements GenericCRUDService<
        AuthUserVO,
        AuthUserCreateVO,
        AuthUserUpdateVO,
        Long> {

    private static AuthUserService instance;


//    private final AuthUserValidator validator;

    public AuthUserService() {
        super(
                ApplicationContextHolder.getBean(AuthUserDAO.class),
                ApplicationContextHolder.getBean(BaseUtils.class)
        );
    }

    @Override
    @Transactional
    public Response<Long> create(@NonNull AuthUserCreateVO vo) {
        // TODO: 6/21/2022 validate input
        Optional<AuthUser> optionalAuthUser = dao.findByUserName(vo.getUsername());
        if (optionalAuthUser.isPresent()) {
            throw new RuntimeException("Username already taken");
        }
        AuthUser authUser = AuthUser
                .childBuilder()
                .username(vo.getUsername())
                .password(utils.encode(vo.getPassword()))
                .email(vo.getEmail())
                .build();
        dao.save(authUser);
        return new Response<>(authUser.getId());
    }

    @Override
    public Response<Void> update(@NonNull AuthUserUpdateVO vo) {
        return null;
    }

    @Override
    public Response<Void> delete(@NonNull Long id) {
        return null;
    }

    @Override
    public Response<AuthUserVO> get(@NonNull Long id) {
        return null;
    }

    @Override
    public Response<List<AuthUserVO>> getAll() {
        return null;
    }

    public static AuthUserService getInstance() {
        if (instance == null) {
            instance = new AuthUserService();
        }
        return instance;
    }

    public Response<AuthUserVO> login(String username, String password) {
        Optional<AuthUser> response = dao.findByUserName(username);

        if (response.isEmpty()) {
            throw new RuntimeException("Username does not exist!");
        }

        AuthUser authUser = response.get();
        if (!utils.matchPassword(password, authUser.getPassword())) {
            throw new RuntimeException("Bad credentials");
        }
        AuthUserVO authUserVO = AuthUserVO.builder()
                .username(authUser.getUsername())
                .email(authUser.getEmail())
                .createdAt(authUser.getCreatedAt())
                .role(authUser.getRole())
                .build();
        Session.setSessionUser(authUserVO);
        return new Response<>(authUserVO);
    }


    public void register() {

    }


    public Response<List<AuthUserVO>> showSubjects() {
        List<AuthUser> allSubject = dao.findAll();
        if(allSubject.isEmpty()){
            throw new RuntimeException("Subject doesnt exist!");
        }


        return null;
    }


    public Response<AuthUserVO> showUserRole(String userId) {
        AuthUser authUser = dao.findById(Long.valueOf(userId));
        AuthUserVO authUserVO = new AuthUserVO(authUser.getUsername(), authUser.getEmail(),authUser.getRole());

        return new Response<>(authUserVO);
    }

    public void changeUserCurrentRole(String userName) {
        Optional<AuthUser> authUser = dao.findByUserName(userName);

    }

    public void setRole(Long user_id, AuthRole role) {

        Optional<AuthUser> findById = Optional.ofNullable(dao.findById((user_id)));
        if (findById.isEmpty()){
            throw new RuntimeException("user not found");
        }
        AuthUser authUser = findById.get();
        authUser.setRole(role);
        dao.update(authUser);

    }

    public void changeUsername(String newUsername) {
        AuthUser authUser = dao.findById(Session.sessionUser.getId());
        authUser.setUsername(newUsername);
        dao.update(authUser);




    }
}
