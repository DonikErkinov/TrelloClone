package uz.jl.service.auth;/*
  @author "Doniyor Erkinov"
  @since 6/22/2022 4:49 PM (Wednesday)
  trello_VU/IntelliJ IDEA
*/


import uz.jl.BaseUtils;
import uz.jl.Colors;

public class AuthTeacherService {
    private static AuthTeacherService authTeacherService = new AuthTeacherService();

    public static void testCrud(String choice){
        switch (choice){
            case "1" -> authTeacherService.showTest();
            case "2" -> authTeacherService.createTest();
            case "3" -> authTeacherService.updateTest();
            case "4" -> authTeacherService.deleteTest();
            case "0" -> authTeacherService.logout();
            case "q" -> {
                BaseUtils.println("Bye", Colors.CYAN);
                System.exit(0);
            }


        }

    }

    private void logout() {

    }

    private void deleteTest() {

    }

    private void updateTest() {

    }

    private void createTest() {
    }

    private void showTest() {
    }

}
