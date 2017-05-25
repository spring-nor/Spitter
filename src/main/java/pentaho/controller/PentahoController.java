package pentaho.controller;

import com.sun.xml.internal.org.jvnet.mimepull.Header;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/pentaho")
public class PentahoController {
    private final Logger logger = LogManager.getLogger(PentahoController.class);

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(method = RequestMethod.GET)
    public String spittles(Model model) {

        logger.debug("--------------PentahoController pentaho");

        return "pentaho";
    }

    //http://localhost:8080/pentaho/report
    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public String showSpittle(Model model) {
        return "report";
    }


//    @RequestMapping(value = "/proxy/*", method = RequestMethod.GET)
//    public String doProxy(Model model,
//                          HttpServletRequest request,
//                          HttpServletResponse response) {
//        request.getRequestURI();
//
//        return "report";
//    }


    HttpHeaders createHeaders(String auth, String host, String referer) {
        return new HttpHeaders() {{
//            String auth = username + ":" + password;
//            byte[] encodedAuth = Base64.encodeBase64(
//                    auth.getBytes(Charset.forName("US-ASCII")));
//            String authHeader = "Basic " + new String(encodedAuth);
            set("Authorization", auth);
            set("host", host);
            set("Referer", referer);
        }};
    }

    HttpHeaders createHeaders(String auth, String referer) {
        return new HttpHeaders() {{
            set("Authorization", auth);
            set("Referer", referer);
        }};
    }

    private static String PENTAHO_URL = "http://localhost:8080";
    private static String BASIC_AUTH = "Basic QWRtaW46cGFzc3dvcmQ=";
    private static String HOST = "http://localhost:8080/pentaho";
    private static String REFERER = "http://localhost:8080/pentaho";


    private String server = "10.20.101.230";
    private int port = 8080;


    //http://localhost:8080/pentaho/post
    @RequestMapping(value = "/**", method = RequestMethod.GET)
    public String doProxy(Model model,
                          HttpServletRequest request,
                          HttpServletResponse response) throws URISyntaxException {

//        RestTemplate restTemplate = new RestTemplate();

//        HttpHeaders headers = createHeaders(BASIC_AUTH, REFERER);
//        HttpEntity<?> httpRequest = new HttpEntity<>(headers);
//
//
//        URI uri = new URI("http", null, server, port, request.getRequestURI(), request.getQueryString(), null);
//        ResponseEntity<String> responseEntity =
//                restTemplate.exchange(uri, HttpMethod.GET, httpRequest, String.class);
//
//        return responseEntity.getBody();

        String uri = request.getRequestURI();
        String url = uri.replace("/proxy", "");
        HttpHeaders headers = createHeaders(BASIC_AUTH, REFERER);
////        headers.setContentType(MediaType.APPLICATION_JSON);
//
//
////        Map bodyMap = new HashMap();
////        bodyMap.put("j_username", "Suzy");
////        bodyMap.put("j_password", "password");
////        HttpEntity<?> httpRequest = new HttpEntity<>(bodyMap, headers);
//
//        HttpEntity<?> httpRequest = new HttpEntity<>(headers);
//        RestTemplate restTemplate = new RestTemplate();
////        ResponseEntity<String> httpResponce = restTemplate.exchange(PENTAHO_URL + url, HttpMethod.GET, httpRequest, String.class);
//
//        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
//        InetSocketAddress address = new InetSocketAddress("http://10.20.101.230", 8080);
//        Proxy proxy = new Proxy(Proxy.Type.HTTP, address);
//        factory.setProxy(proxy);
//        restTemplate.setRequestFactory(factory);
//
//
//        ResponseEntity<Resource> responseEntity = restTemplate.exchange(url, HttpMethod.GET,httpRequest, Resource.class);
//
//        InputStream responseInputStream;
//        try {
//
//            response.getOutputStream().print(responseEntity.getBody().getInputStream().toString());
//
////            responseInputStream = responseEntity.getBody().getInputStream();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }


//        HttpHeaders respHeaders = responce.getHeaders();
//        String location = respHeaders.getFirst("Location");
//        String[] locs = location.split(";");
//        String path = locs[0];


//        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
//        Map map = new HashMap<String, String>();
//        map.put("Content-Type", "application/x-www-form-urlencoded");
//
//        headers.setAll(map);
//
//        Map req_payload = new HashMap();
//        req_payload.put("j_username", "Admin");
//        req_payload.put("j_password", "password");
//
//        HttpEntity<?> request = new HttpEntity<>(req_payload, headers);
//        String url = "http://localhost:8080/pentaho/j_spring_security_check";
//
//        ResponseEntity<?> response = new RestTemplate().postForEntity(url, request, String.class);
//
//        HttpStatus statusCode = response.getStatusCode();
//        int statusCodeValue = response.getStatusCodeValue();
//        Object body = response.getBody();
//        HttpHeaders httpHeaders = response.getHeaders();

//        return "redirect:http://localhost:8080" + path;
        return null;
    }

}
