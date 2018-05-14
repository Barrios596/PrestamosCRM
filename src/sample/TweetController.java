package sample;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
import org.bson.Document;
import org.bson.json.*;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;


public class TweetController {

    public static boolean IngresarTweet (String username) throws TwitterException{

        //Tomado de https://www.youtube.com/watch?v=Il4wJxRzvaU
        ConfigurationBuilder cf= new ConfigurationBuilder();
        cf.setDebugEnabled(true)
                .setOAuthConsumerKey("yTLFbKcUT3VYlx4yUW9PYvfdM")
                .setOAuthConsumerSecret("yozKtg4nPIcqlQ4NlRbAZ8D0oeIwgojh1rSqqTXhMFPS0AAKWv")
                .setOAuthAccessToken("131305750-bllgSbbbGVZhtYgaStorPXXojq2Ioo0f2Hgi9oHk")
                .setOAuthAccessTokenSecret("aFzX82Q17akKZESFNdu2WLJGFXZtfVyJR93fSfF8XV7fK")
        ;

        TwitterFactory tf = new TwitterFactory(cf.build());
        twitter4j.Twitter twitter = tf.getInstance();

        //Tomado de https://www.youtube.com/watch?v=yi9KFF7oMjU
        //Conexion con servidor
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        System.out.println("Conexion a servidor exitoso");

        //Conexion con base de datos
        MongoDatabase dbs = mongoClient.getDatabase("twitter");
        System.out.println("Conexion a base de datos exitosa");

        //Conexion con la coleccion
        MongoCollection<Document> coll = dbs.getCollection("tweets");
        //MongoCollection col=dbs.getCollection("tweets");
        System.out.println("Conexion a la coleccion exitosa");


        //Obteniendo TimeLine de un usuario
        try{
            List<Status> status = twitter.getUserTimeline(username);
            for (Status st : status){
                //Insertando en la coleccion los twwets
                //tomado de http://mongodb.github.io/mongo-java-driver/3.4/driver/getting-started/quick-start/
                Document doc = new Document("usuario", st.getUser().getScreenName())
                        .append("texto", st.getText())
                        .append("fecha", st.getCreatedAt().toString())
                        .append("url", "https://twitter.com/" + st.getUser().getScreenName() + "/status/" + st.getId())
                        ;
                System.out.println(doc);
                System.out.println("empieza el ingreso de documentos");
                coll.insertOne(doc);
                System.out.println("finaliza el ingreso de documentos");
                //System.out.println(st.getUser().getName()+"---------------"+ st.getText());
            }
        }
        catch (Exception e){
            System.out.println("Ocurrio un error al ingresar el usuario");
            return false;
        }
        return true;
    }

    public void IngresarVariosTweets (ArrayList<String> usuarios){
        for (int i=0; i< usuarios.size();i++){
            try {
                IngresarTweet(usuarios.get(i));
            } catch (TwitterException e) {
                System.out.println("No se pudieron ingresar los tweets de " + usuarios.get(i));
            }
        }
    }

    public static void reiniciarColeccion (){

        MongoClient client = new MongoClient("localhost", 27017);
        MongoDatabase db = client.getDatabase("twitter");
        db.getCollection("tweets").deleteMany(new Document());

        BasicDBObject document = new BasicDBObject();

    }


    public static Tweet buscarUltimoTweet (String username){
        //Tomado de https://www.youtube.com/watch?v=Il4wJxRzvaU
        ConfigurationBuilder cf= new ConfigurationBuilder();
        cf.setDebugEnabled(true)
                .setOAuthConsumerKey("yTLFbKcUT3VYlx4yUW9PYvfdM")
                .setOAuthConsumerSecret("yozKtg4nPIcqlQ4NlRbAZ8D0oeIwgojh1rSqqTXhMFPS0AAKWv")
                .setOAuthAccessToken("131305750-bllgSbbbGVZhtYgaStorPXXojq2Ioo0f2Hgi9oHk")
                .setOAuthAccessTokenSecret("aFzX82Q17akKZESFNdu2WLJGFXZtfVyJR93fSfF8XV7fK")
        ;

        TwitterFactory tf = new TwitterFactory(cf.build());
        twitter4j.Twitter twitter = tf.getInstance();

        //Tomado de https://www.youtube.com/watch?v=yi9KFF7oMjU
        //Conexion con servidor
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        System.out.println("Conexion a servidor exitoso");


        //Tomado de https://www.youtube.com/watch?v=yi9KFF7oMjU
        //Conexion con servidor
        MongoClient mongoClient1 = new MongoClient("localhost", 27017);
        System.out.println("Conexion a servidor exitoso");

        //Conexion con base de datos
        MongoDatabase dbs = mongoClient.getDatabase("twitter");
        System.out.println("Conexion a base de datos exitosa");

        //Conexion con la coleccion
        MongoCollection<Document> coll = dbs.getCollection("tweets");
        //MongoCollection col=dbs.getCollection("tweets");
        System.out.println("Conexion a la coleccion exitosa");

        try {
            //Tomado de http://mongodb.github.io/mongo-java-driver/3.4/driver/getting-started/quick-start/
            Document myDoc = coll.find(eq("usuario", username)).first();
            String usuario= myDoc.getString("usuario");
            String text = myDoc.getString("texto");
            String fecha= myDoc.getString("fecha");
            String url = myDoc.getString("url");
            Tweet nuevoTweet = new Tweet(usuario,text, fecha, url);
            return nuevoTweet;
        }
        catch (Exception e ){
            System.out.println("Error no se encontro tweets de"+username);
            return null;
        }

        //String json= myDoc.toJson();
        //System.out.println("lo convierte a json"+ json);

    }


/*
    public boolean IngresarTweetBD(String username) throws TwitterException{
        //Tomado de https://www.youtube.com/watch?v=Il4wJxRzvaU
        ConfigurationBuilder cf= new ConfigurationBuilder();
        cf.setDebugEnabled(true)
                .setOAuthConsumerKey("yTLFbKcUT3VYlx4yUW9PYvfdM")
                .setOAuthConsumerSecret("yozKtg4nPIcqlQ4NlRbAZ8D0oeIwgojh1rSqqTXhMFPS0AAKWv")
                .setOAuthAccessToken("131305750-bllgSbbbGVZhtYgaStorPXXojq2Ioo0f2Hgi9oHk")
                .setOAuthAccessTokenSecret("aFzX82Q17akKZESFNdu2WLJGFXZtfVyJR93fSfF8XV7fK")
        ;

        TwitterFactory tf = new TwitterFactory(cf.build());
        twitter4j.Twitter twitter = tf.getInstance();

        //Tomado de https://www.youtube.com/watch?v=yi9KFF7oMjU
        //Conexion con servidor
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        System.out.println("Conexion a servidor exitoso");

        //Conexion con base de datos
        MongoDatabase dbs = mongoClient.getDatabase("twitter");
        System.out.println("Conexion a base de datos exitosa");

        //Conexion con la coleccion
        MongoCollection<BasicDBObject> coll = dbs.getCollection("tweets", BasicDBObject.class);
        //MongoCollection col=dbs.getCollection("tweets");
        System.out.println("Conexion a la coleccion exitosa");


        //Obteniendo TimeLine de un usuario
        try{
            List<Status> status = twitter.getUserTimeline(username);
            for (Status st : status){
                //Insertando en la coleccion los twwets
                BasicDBObject document = new BasicDBObject();
                document.put("usuario", st.getUser().getName());
                document.put("texto", st.getText());
                document.put("fecha", st.getCreatedAt().toString());
                System.out.println(document);
                System.out.println("empieza el ingreso de documentos");
                coll.insertOne(document);
                System.out.println("finaliza el ingreso de documentos");
                //System.out.println(st.getUser().getName()+"---------------"+ st.getText());
            }
        }
        catch (Exception e){
            System.out.println("Ocurrio un error al ingresar el usuario");
            return false;
        }
        return true;
    }*/


    /*
    public static void buscarTweetdeUsuario (String username){
        //Tomado de https://www.youtube.com/watch?v=Il4wJxRzvaU
        ConfigurationBuilder cf= new ConfigurationBuilder();
        cf.setDebugEnabled(true)
                .setOAuthConsumerKey("yTLFbKcUT3VYlx4yUW9PYvfdM")
                .setOAuthConsumerSecret("yozKtg4nPIcqlQ4NlRbAZ8D0oeIwgojh1rSqqTXhMFPS0AAKWv")
                .setOAuthAccessToken("131305750-bllgSbbbGVZhtYgaStorPXXojq2Ioo0f2Hgi9oHk")
                .setOAuthAccessTokenSecret("aFzX82Q17akKZESFNdu2WLJGFXZtfVyJR93fSfF8XV7fK")
        ;

        TwitterFactory tf = new TwitterFactory(cf.build());
        twitter4j.Twitter twitter = tf.getInstance();

        //Tomado de https://www.youtube.com/watch?v=yi9KFF7oMjU
        //Conexion con servidor
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        System.out.println("Conexion a servidor exitoso");

        //Conexion con base de datos
        MongoDatabase dbs = mongoClient.getDatabase("twitter");
        System.out.println("Conexion a base de datos exitosa");

        //Conexion con la coleccion
        MongoCollection<BasicDBObject> coll = dbs.getCollection("tweets", BasicDBObject.class);
        //MongoCollection col=dbs.getCollection("tweets");
        System.out.println("Conexion a la coleccion exitosa");


        System.out.println(coll.find().toString());
        //System.out.println(myDoc.toJson());
    }*/

    public static void main(String[] args) throws TwitterException {
        //IngresarTweet("barrios596");
        //Tweet ver = buscarUltimoTweet("Barrios596");
        //System.out.println(ver.getUsuario()+"\n");
        //System.out.println(ver.getTexto()+"\n");
        //System.out.println(ver.getFecha()+"\n");
        //BUSCANDO POR PALABRA
       /* twitter4j.Query query = new twitter4j.Query("pautips");
        twitter4j.QueryResult result;
        do {
            result = twitter.search(query);
            List<Status> tweets = result.getTweets();
            for (Status tweet : tweets) {
                System.out.println("@" + tweet.getUser().getScreenName() +
                        " - " + tweet.getText());
            }
        } while ((query = result.nextQuery()) != null);*/

    }


}
