package controllers;

import models.Member;
import play.Logger;
import play.mvc.Controller;

public class Accounts extends Controller
{
    public String firstname;
    public String lastname;
    public String email;
    public String password;
    public static void signup()
    {
        render("signup.html");
    }
    public static void edit()
    {
        Member member = Accounts.getLoggedInMember();
        render("editProfile.html", member);
    }

    public static void login()
    {
        render("login.html");
    }
    public static void logout()
    {
        session.clear();
        redirect ("/");
    }
    public static void register(String firstname, String lastname, String email, String password)
    {
        Logger.info("Registering new user " + email);
        Member member = new Member(firstname, lastname, email, password);
        member.save();
        redirect("/");
    }
    public static void editProfile (String newFirstname, String newLastname, String newPassword)
    {
        Member member = getLoggedInMember();

        //retrieve the selected details from member
        member.setFirstname(newFirstname);
        member.setLastname(newLastname);
        member.setPassword(newPassword);
        member.save();
        Logger.info("Editing profile of user " + newFirstname);
        redirect("/dashboard");
    }
    public static void authenticate(String email, String password)
    {
        Logger.info("Attempting to authenticate with " + email + ":" + password);

        Member member = Member.findByEmail(email);
        if ((member != null) && (member.checkPassword(password) == true)) {
            Logger.info("Authentication successful");
            session.put("logged_in_Memberid", member.id);
            redirect ("/dashboard");
        } else {
            Logger.info("Authentication failed");
            redirect("/login");
        }
    }
    public static Member getLoggedInMember()
    {
        Member member = null;
        if (session.contains("logged_in_Memberid")) {
            String memberId = session.get("logged_in_Memberid");
            member = Member.findById(Long.parseLong(memberId));
        } else {
            login();
        }
        return member;
    }
}
