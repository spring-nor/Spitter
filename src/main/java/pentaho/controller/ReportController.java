package pentaho.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FilenameUtils;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClients;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Created by norman on 26/05/17.
 */

@Controller
@RequestMapping("/report")
public class ReportController {

    private final Logger logger = LogManager.getLogger(ReportController.class);


    HttpHeaders createHeaders(String auth, String referer, String connection) {
        return new HttpHeaders() {{
            set("Authorization", auth);
            set("Referer", referer);
            set("Connection", connection);
        }};
    }

    private static String PENTAHO_URL = "http://localhost:8080";
    private static String BASIC_AUTH = "Basic QWRtaW46cGFzc3dvcmQ=";
    private static String HOST = "http://localhost:8080/pentaho";
    private static String REFERER = "http://localhost:8081/pentaho";
    private static String CONNECTION = "keep-alive";


    @RequestMapping(value = "/resttemplate/**", method = RequestMethod.GET)
    public String doProxyRestTemplate(Model model,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws IOException {

        String uri = request.getRequestURI();
        String pentaho_request_url = uri.replace("/report/resttemplate", "/pentaho");


        String pentaho_url = PENTAHO_URL;
        try {
            String url_str = PENTAHO_URL + uri;
            URL url = new URL(url_str);
            String urlBaseName = FilenameUtils.getBaseName(url.getPath());
            String urlExtension = FilenameUtils.getExtension(url.getPath());
            String urlName = FilenameUtils.getName(url.getPath());
            if (!urlExtension.equals("css") && !urlExtension.equals("")) {
                logger.info(request.getAttribute(urlName));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        HttpHeaders headers = createHeaders(BASIC_AUTH, REFERER, CONNECTION);
        HttpEntity<?> httpRequest = new HttpEntity<>(headers);


//        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
//        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", 8081));
//        requestFactory.setProxy(proxy);
//        RestTemplate restTemplate = new RestTemplate(requestFactory);


        String resultUrl = java.net.URLDecoder.decode(PENTAHO_URL + pentaho_request_url, "UTF-8");

        RestTemplate restTemplate = new RestTemplate();

//        ResponseEntity<Resource> responseEntity = restTemplate.exchange
//                (resultUrl, HttpMethod.GET, httpRequest, Resource.class);

        ResponseEntity<Resource> responseEntity = restTemplate.exchange
                (resultUrl, HttpMethod.GET, httpRequest, Resource.class);

        MediaType contentType = responseEntity.getHeaders().getContentType();
        String contentTypeName = contentType.getType();
        String contentTypevalue = contentType.getSubtype();


        BufferedReader rd = new BufferedReader(
                new InputStreamReader(responseEntity.getBody().getInputStream()));
        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }


        try {
            response.setHeader(contentTypeName, contentTypevalue);
            response.getOutputStream().print(result.toString());
            response.getOutputStream().close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @RequestMapping(value = "/anon/**", method = RequestMethod.GET)
    public String doProxyAnon(Model model,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException {

        String uri = request.getRequestURI();
        String pentaho_request_url = uri.replace("/report/anon", "/pentaho");

        String pentaho_url = PENTAHO_URL;
//        try {
//            String url_str = PENTAHO_URL + uri;
//            URL url = new URL(url_str);
//            String urlBaseName = FilenameUtils.getBaseName(url.getPath());
//            String urlExtension = FilenameUtils.getExtension(url.getPath());
//            String urlName = FilenameUtils.getName(url.getPath());
//            if (!urlExtension.equals("css") && !urlExtension.equals("")) {
//                logger.info(request.getAttribute(urlName));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        HttpHeaders headers = createHeaders(BASIC_AUTH, REFERER, CONNECTION);
        HttpEntity<?> httpRequest = new HttpEntity<>(headers);


        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", 8081));
        requestFactory.setProxy(proxy);

        String resultUrl = java.net.URLDecoder.decode(pentaho_url + pentaho_request_url, "UTF-8");

        RestTemplate restTemplate = new RestTemplate(requestFactory);

        ResponseEntity<Resource> responseEntity = restTemplate.exchange
                (resultUrl, HttpMethod.GET, httpRequest, Resource.class);

//        ResponseEntity<Resource> responseEntity = restTemplate.exchange
//                ("http://www.google.it", HttpMethod.GET, httpRequest, Resource.class);

        MediaType contentType = responseEntity.getHeaders().getContentType();
        String contentTypeName = contentType.getType();
        String contentTypevalue = contentType.getSubtype();


        BufferedReader rd = new BufferedReader(
                new InputStreamReader(responseEntity.getBody().getInputStream()));
        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

//        try {
//            response.setHeader(contentTypeName, contentTypevalue);
//            response.getOutputStream().print(result.toString());
//            response.getOutputStream().close();
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return null;

        return "redirect:" + PENTAHO_URL + pentaho_request_url;
    }

    private static String PENTAHO_AUTHORITY = "localhost:8080";


    @RequestMapping(value = "/auth/**", method = RequestMethod.GET)
    public String doProxyAuth(Model model,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();

        String pentaho_request_url = uri.replace("/report/auth", "/pentaho");

//        String pentaho_request_url = uri;
//        try {
//            URL requesUrl = new URL(uri);
//            if (!requesUrl.getAuthority().equals(PENTAHO_AUTHORITY)) {
//                pentaho_request_url = uri.replace("/report/auth", "/pentaho");
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        // Gets a ticket from Pentaho

        // Creates a CredentialProvider for Basic authentication
        CredentialsProvider provider = new BasicCredentialsProvider();
        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("Admin", "password");
        // sets AuthScope.ANY, since we don't really care about the realm in this example
        provider.setCredentials(AuthScope.ANY, credentials);

        // Creates a target host and an AuthCache instance
        HttpHost targetHost = new HttpHost("localhost", 8080, "http");
        AuthCache authCache = new BasicAuthCache();
        // Generates BASIC scheme object and adds it to the local auth cache
        BasicScheme basicAuth = new BasicScheme();
        authCache.put(targetHost, basicAuth);

        // Creates an HttpClientContext to hold authentication data and sets the credential provider
        HttpClientContext context = HttpClientContext.create();
        context.setCredentialsProvider(provider);
        context.setAuthCache(authCache);
        HttpClient client = HttpClients.custom().setDefaultCredentialsProvider(provider).build();

        HttpGet get = new HttpGet(pentaho_request_url);

        HttpResponse httpResponse = client.execute(targetHost, get, context);

        Header contentType = httpResponse.getEntity().getContentType();
        String contentTypeName = contentType.getName();
        String contentTypevalue = contentType.getValue();

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(httpResponse.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        try {
            String url_str = PENTAHO_URL + uri;
            URL url = new URL(url_str);
            String urlBaseNam = FilenameUtils.getBaseName(url.getPath());
            String urlExtension = FilenameUtils.getExtension(url.getPath());
            String urlName = FilenameUtils.getName(url.getPath());
            if (!urlExtension.equals("css") && !urlExtension.equals("")) {
                logger.info(request.getAttribute(urlName));

//                response.setHeader(contentType.getName(), "text/javascript");
            } else {
//                response.setHeader(contentType.getName(), contentType.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.setHeader(contentType.getName(), contentType.getValue());


        response.getOutputStream().print(result.toString());
        response.getOutputStream().close();

        return null;
    }


    /**
     * Redirects a user to Pentaho using PentahoTransparentAuthentication.
     *
     * @param targetUrl A URL that is assumed to be under the target Pentaho context
     * @return A RedirectView to redirect the user to Pentaho
     * @throws ClientProtocolException
     * @throws IOException
     */
    private View pentahoAutologin(String targetUrl) throws ClientProtocolException, IOException {
        // Gets a ticket from Pentaho

        // Creates a CredentialProvider for Basic authentication
        CredentialsProvider provider = new BasicCredentialsProvider();
        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("Admin", "password");
        // sets AuthScope.ANY, since we don't really care about the realm in this example
        provider.setCredentials(AuthScope.ANY, credentials);

        // Creates a target host and an AuthCache instance
        HttpHost targetHost = new HttpHost("localhost", 8080, "http");
        AuthCache authCache = new BasicAuthCache();
        // Generates BASIC scheme object and adds it to the local auth cache
        BasicScheme basicAuth = new BasicScheme();
        authCache.put(targetHost, basicAuth);

        // Creates an HttpClientContext to hold authentication data and sets the credential provider
        HttpClientContext context = HttpClientContext.create();
        context.setCredentialsProvider(provider);
        context.setAuthCache(authCache);
        HttpClient client = HttpClients.custom().setDefaultCredentialsProvider(provider).build();

        HttpGet get = new HttpGet("/pentaho/Login?generate-ticket=1&app=showcase&username=user0.2");
        HttpResponse response = client.execute(targetHost, get, context);
        InputStream responseStream = response.getEntity().getContent();

        BufferedReader reader = new BufferedReader(new InputStreamReader(responseStream));
        String firstLine = reader.readLine();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(firstLine);
        String ticketId = node.get("ticketId").asText();

        // Redirects the user of this application to the Pentaho target URL with
        // the ticket
        RedirectView redirectView = new RedirectView(targetUrl + "&autologin=true&ticket=" + ticketId);
        return redirectView;
    }

    @RequestMapping(value = "/login/**", method = RequestMethod.GET)
    public String obtainCookie(Model model,
                               HttpServletRequest request,
                               HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();

//        String pentaho_request_url = uri.replace("/report/login", "/pentaho/j_spring_security_check?j_username=Admin&j_password=password");


        String login = "/pentaho/j_spring_security_check?j_username=Admin&j_password=password";
        String resultUrl = java.net.URLDecoder.decode(PENTAHO_URL + login, "UTF-8");
        HttpHeaders headers = createHeaders(BASIC_AUTH, REFERER, CONNECTION);
        HttpEntity<?> httpRequest = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Resource> responseEntity = restTemplate.exchange
                (resultUrl, HttpMethod.POST, httpRequest, Resource.class);
        List<String> cookies = responseEntity.getHeaders().get("Set-Cookie");
        if (cookies.size() > 0) {
            String[] cookieResp = cookies.get(0).toString().split(";");

            String cookiesContenuto = null;
            for (String c : cookieResp) {
                if (c.contains("JSESSION")) {
                    String[] cook = c.split("=");
                    String cookiesName = cook[0];
                    cookiesContenuto = cook[1];
                }
                logger.info(c);

            }

            Cookie cookie = new Cookie("JSESSIONID", cookiesContenuto);
            cookie.setPath("/pentaho");
            cookie.setDomain("localhost");
            HttpSession session = request.getSession(true);
            response.addCookie(cookie);


        }


//        JSESSIONID
//
//        HttpSession session = request.getSession(true);


        return "report";
    }


}























