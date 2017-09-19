
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @since 9/19/2017
 * @author Brenda Mendez y Oscar Romero
 */
public class Serverpc {

    /**
     * Puerto
     */
    private final static int PORT = 5000;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args[0].equals("-a") && args[2].equals("-d")) {
            String archivo = args[1];
            String directorio = args[3];
            
            try {
                ServerSocket serverSocket = new ServerSocket(PORT);
                System.out.println("Servidor> Servidor iniciado");
                System.out.println("Servidor> En espera de cliente...");
                Socket clientSocket;
                while (true) {
                    clientSocket = serverSocket.accept();
                    BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));            
                    PrintStream output = new PrintStream(clientSocket.getOutputStream());
                    ArrayList<String> palabras=leerArchivo(archivo);
                    String request="hola";
                    String mensaje="";
                    while(request.equals("")!=true){
                        request=input.readLine();
                        //GET /PrimerHTML HTTP/1.1
                        try{
                            if((request.substring(0, 3)).equals("GET")){
                                String[] informacion=request.split(" ");
                                String pagina=informacion[1];
                                pagina=pagina.substring(1,pagina.length());
                                boolean t=true;
                                for(String p:palabras){
                                    if(p.equalsIgnoreCase(pagina)){
                                        mensaje=Error(pagina);
                                        t=false;
                                        break;
                                    } 
                                }
                                if(t==true){
                                    if(!pagina.equals("favicon.ico")){
                                        mensaje=Bien(pagina,directorio);
                                    }
                                }
                            }
                            break;
                        }catch(Exception e){
                            System.out.println("Error: "+e);
                        }
                    }
                    output.flush();
                    output.println(mensaje);                
                    clientSocket.close();
                }
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }

    }

    private static ArrayList<String> leerArchivo(String archivo) throws FileNotFoundException {
       File archivito=new File(archivo);
       Scanner sc=new Scanner(archivito);
       ArrayList<String> palabras=new ArrayList<String>();
       while(sc.hasNextLine()){
           String linea=sc.nextLine();
           if(!linea.equals("Fin")){
               palabras.add(linea);
           }
       }
       return palabras;
    }

    private static String Error(String s) {
       return ("HTTP/1.1 200 OK\n\n"
                +"<!DOCTYPE html>\n"
                + "<html lang=\"es\" dir=\"ltr\">\n"
                + "<head>\n"
                + "<meta charset=\"UTF-8\">\n"
                + "</head>\n"
                + "<body>\n"
                + "<header> <h1>La página solicitada está prohibida por contener la palabra: " + s + ".</h1> </header>\n"
                + "Por: Brenda Mendez y Oscar Romero :3"
                + "</body>\n"
                + "</html>");
    }

    private static String Bien(String request, String directorio) {
        try {
            File file = new File(directorio + "/" + request + ".html");
            Scanner sc = new Scanner(file);
            StringBuffer page = new StringBuffer("");
            int f=0;
            while (sc.hasNextLine()) {
                if(f==0){
                    page.append("HTTP/1.1 200 OK\n\n");
                    f=1;
                    String a=sc.nextLine();
                    a=a.substring(3,a.length());
                    page.append(a).append("\n");
                }else{
                    page.append(sc.nextLine()).append("\n");
                }
            }
            return page.toString();
        } catch (FileNotFoundException ex) {
            return ("HTTP/1.1 200 OK\n\n"
                    +"<!DOCTYPE html>\n"
                    + "<html lang=\"es\" dir=\"ltr\">\n"
                    + "<head>\n"
                    + "<meta charset=\"UTF-8\">\n"
                    + "</head>\n"
                    + "<body>\n"
                    + "<header> <h1>404. Página no encontrada.</h1> </header>\n"
                    + "Por: Brenda Mendez y Oscar Romero :3"
                    + "</body>\n"
                    + "</html>");
        }

    }
}
