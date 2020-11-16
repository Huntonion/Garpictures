package controller;

import model.Utente;
import model.UtenteDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/Registrazione")
public class Registrazione extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nomeUtente = request.getParameter("nomeUtente");
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String telefono = request.getParameter("telefono");
        String citta = request.getParameter("citta");
        String provincia = request.getParameter("provincia");
        String via = request.getParameter("via");
        Utente utente = new Utente();
        String address;

        //controllo che il nome sia nel giusto formato
        Pattern pattern = Pattern.compile("^[A-Za-z]{3,30}$",Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(nome);
        if(!matcher.matches()){
            request.setAttribute("errore","errore nel formato del nome.");
            address = "registrazione.jsp";
        }else{
            utente.setNome(nome);
        }
        matcher = pattern.matcher(cognome);
        if(!matcher.matches()){
            request.setAttribute("errore", "errore nel formato del cognome.");
            address = "registrazione.jsp";
        }else{
            utente.setCognome(cognome);
        }
        pattern= Pattern.compile("^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$");
        matcher = pattern.matcher(email);
        if(!matcher.matches()){
            request.setAttribute("errore","errore nel formato dell'email.");
            address = "registrazione.jsp";
        }else{
            var filter = new UtenteDAO();
            boolean flag = false;
            ArrayList<Utente> utenti = filter.doRetriveAll();
            for(Utente x: utenti){
                if(email.equals(x.getEmail())) {
                    request.setAttribute("errore", "email già presente nel database.");
                    flag = true;
                }
            }
            if(flag == false)
                utente.setEmail(email);
        }
        pattern = Pattern.compile("^[A-Za-z0-9]{5,20}$");
        matcher = pattern.matcher(nomeUtente);
        if(!matcher.matches()){
            request.setAttribute("errore","errore nel formato del nome utente");
            address = "registrazione.jsp";
        }else{
            var filter = new UtenteDAO();
            boolean flag = false;
            ArrayList<Utente> utenti = filter.doRetriveAll();
            for(Utente x: utenti){
                if(x.getNomeUtente().equals(nomeUtente)) {
                    request.setAttribute("errore", "nome utente già presente nel database.");
                    flag = true;
                }
            }
            if(flag == false)
                utente.setNomeUtente(nomeUtente);
        }
        pattern = Pattern.compile("^[A-Za-z]{2,30}((\\s[A-z]{2,30})?){1,4}(\\s\\d{1,4})?$");
        matcher = pattern.matcher(via);
        System.out.println(matcher.matches());
        if(!matcher.matches()){
            request.setAttribute("errore","errore nel formato della via");
            address = "registrazione.jsp";
        }else{
            utente.setIndirizzo(via);
        }
        pattern = Pattern.compile("^[A-Z]{2}?$");
        matcher = pattern.matcher(provincia);
        if(!matcher.matches()){
            request.setAttribute("errore","errore nel formato della provincia");
            address = "registrazione.jsp";
        }else{
            utente.setProvincia(provincia);
        }
        pattern = Pattern.compile("^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*$");
        matcher = pattern.matcher(citta);
        if(!matcher.matches()){
            request.setAttribute("errore","errore nel formato della città");
            address = "registrazione.jsp";
        }else{
            utente.setCitta(citta);
        }
        pattern = Pattern.compile("^[0-9]{8,13}$");
        matcher = pattern.matcher(telefono);
        if(!matcher.matches()){
            request.setAttribute("errore","errore nel formato del numero di telefono");
            address = "registrazione.jsp";
        }else{
            utente.setTelefono(telefono);
        }
        pattern = Pattern.compile("^[A-Za-z0-9_.-]{5,20}$");
        matcher = pattern.matcher(password);
        if(!matcher.matches()){
            request.setAttribute("errore","errore nel formato della password");
            address = "registrazione.jsp";
        }else{
            utente.setPassword(password);
        }
        System.out.println(utente.getNomeUtente() + utente.getNome() + utente.getCognome() + utente.getCitta() + utente.getPassword() + utente.getIndirizzo()+ utente.getProvincia() + utente.getTelefono() + utente.getEmail());
        if(utente.getNomeUtente() != null && utente.getNome() !=null && utente.getCognome() != null && utente.getPassword() != null && utente.getEmail() != null && utente.getCitta() != null
        && utente.getIndirizzo() != null && utente.getProvincia() != null && utente.getTelefono() != null){
            utente.setVisitatore(false);
            utente.setAmministratore(false);
            HttpSession session = request.getSession();
            session.setAttribute("beanUtente",utente);
            var filter = new UtenteDAO();
            filter.doSave(utente);
            utente.setId(filter.retriveIdByUsername(utente.getNomeUtente()));
            address = "IndexServlet";
        }else address = "registrazione.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
              doPost(request,response);
    }
}
