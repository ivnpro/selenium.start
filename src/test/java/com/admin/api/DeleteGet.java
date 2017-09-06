package com.admin.api;

import com.support.helper.Linker;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.ArrayList;
import java.util.List;

public class DeleteGet {
    private HttpClient client = HttpClientBuilder.create().build();
    private final String USER_AGENT = "Mozilla/5.0";
    private CookiesObj cake = new CookiesObj();

    public static void main(String[] args) throws Exception {

        String url = Linker.adminLink+"login/";
        String admin = Linker.adminLink;

        // make sure cookies is turn on
        CookieHandler.setDefault(new CookieManager());

        DeleteGet http = new DeleteGet();

        http.sendPost(url);

        String strID = "1, 2, 4, 5, 6, 3, 29";


        http.sendGetDeleted(admin, "1");

        System.out.println("Done");
    }

    private void sendPost(String url)
            throws Exception {

        HttpPost post = new HttpPost(url);

        // add header
        post.setHeader("Host", url.substring(7, url.length()-7));
        post.setHeader("User-Agent", USER_AGENT);
        post.setHeader("Accept",
                "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        post.setHeader("Accept-Language", "ru-RU,ru;q=0.8,en-US;q=0.6,en;q=0.4");
        post.setHeader("Cookie", cake.getCook());
        post.setHeader("Connection", "keep-alive");
        post.setHeader("Referer", url);
        post.setHeader("Content-Type", "application/x-www-form-urlencoded");

        List<NameValuePair> postParams = new ArrayList<NameValuePair>();
        postParams.add(new BasicNameValuePair("login", "adm"));
        postParams.add(new BasicNameValuePair("password", "123"));

        post.setEntity(new UrlEncodedFormEntity(postParams));

        HttpResponse response = client.execute(post);

        int responseCode = response.getStatusLine().getStatusCode();
        cake.setCook(response.getFirstHeader("Set-Cookie") == null ? "" :
                response.getFirstHeader("Set-Cookie").toString());

        response.getEntity().getContent().close();

        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + postParams);
        System.out.println("Response Code : " + responseCode);

    }

    private void sendGetDeleted(String url, String id)
            throws Exception {

        HttpGet get = new HttpGet(url+"profile/delete/"+id);

        // add header
        get.setHeader("Host", "admin.2.ts.wiggum.tf");
        get.setHeader("User-Agent", USER_AGENT);
        get.setHeader("Accept",
                "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        get.setHeader("Accept-Language", "ru-RU,ru;q=0.8,en-US;q=0.6,en;q=0.4");
        get.setHeader("Cookie", cake.getCook());
        get.setHeader("Connection", "keep-alive");
        get.setHeader("Referer", url+"profile/"+id);
        get.setHeader("Content-Type", "application/x-www-form-urlencoded");
        get.setHeader("Upgrade-Insecure-Requests", "1");


        HttpResponse response = client.execute(get);

        int responseCode = response.getStatusLine().getStatusCode();

        cake.setCook(response.getFirstHeader("Set-Cookie") == null ? "" :
                response.getFirstHeader("Set-Cookie").toString());

        response.getEntity().getContent().close();

        System.out.println("\nSending 'GET' request to URL : " + url + "profile/delete/" + id);
        System.out.println("Response Code : " + responseCode);


    }


    class CookiesObj {
        String php, admin;

        CookiesObj(){
            php = "";
            admin = "";
        }

        public void setCook (String parse) {
            try {
                php = parse.substring(parse.indexOf("PHPSESSID="), parse.indexOf(";", parse.indexOf("PHPSESSID=")));
            } catch (IndexOutOfBoundsException e){}
            try {
                admin = parse.substring(parse.indexOf("admin="), parse.indexOf(";", parse.indexOf("admin=")));
            } catch (IndexOutOfBoundsException e){}
        }

        public String getCook(){
            String cook;
            if (admin.equals("")) {
                cook = php;
            } else {
                cook = php + "; " + admin;
            }
            return cook;
        }
    }

}

