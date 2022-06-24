package uz.jl.ui;/*
  @author "Doniyor Erkinov"
  @since 6/23/2022 6:28 PM (Thursday)
  trello_VU/IntelliJ IDEA
*/

import uz.jl.BaseUtils;
import uz.jl.service.auth.AuthUserService;
import uz.jl.vo.auth.Session;

import java.util.Objects;

public class AdminUI {

    static AuthUserService authUserService = new AuthUserService();

    public static void main(String[] args) {
        if (Objects.nonNull(Session.sessionUser)){
            System.out.println("==============Admin Page===========");
            BaseUtils.println("Shows     -> 1");
            BaseUtils.println("CRUDs     -> 2");
            BaseUtils.println("Settings  -> 3");
            BaseUtils.println("Log out   -> 4");
            BaseUtils.println("Quit      -> q");

            String option = BaseUtils.readText("Choose option:  ");
            switch (option){
                case "1" -> shows();
                case "2" -> crud();
                case "3" -> settings();
                case "4" -> Session.setSessionUser(null);
                case "q" -> System.exit(0);
                default -> BaseUtils.println("Wrong choice dolbayob");
            }


        }
    }


    private static void settings() {
        BaseUtils.println("Change YOUR username -> 1");
        BaseUtils.println("Change YOUR password -> 2");
        BaseUtils.println("Go back -> 3");

        String option = BaseUtils.readText("choose option bro: ");
        switch (option){
            case "1" -> changeUsername();
            case "2" -> changePassword();
            case "3" -> {}
            default -> BaseUtils.println("Wrong option blat");
        }
    }

    public static void changePassword() {
        BaseUtils.readText("Insert ");

    }

    public static void changeUsername() {
        String newUsername = BaseUtils.readText("Insert new username: ");

        authUserService.changeUsername(newUsername);

    }

    private static void crud() {

    }

    private static void shows() {

    }
}
