package pentaho.controller;

import org.apache.http.Header;
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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URISyntaxException;

@Controller
@RequestMapping("/pentaho")
public class PentahoController {
    private final Logger logger = LogManager.getLogger(PentahoController.class);

//    @Autowired
//    RestTemplate restTemplate;

    @RequestMapping(method = RequestMethod.GET)
    public String spittles(ModelAndView mav) {


        return "pentaho";
    }

    //http://localhost:8080/pentaho/report
    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public String showSpittle(HttpServletRequest request) {
        logger.info(request.getAttribute("startTime"));

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


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(Model model) {
        return "test";
    }

    HttpHeaders createHeaders(String auth, String host, String referer, String connection) {
        return new HttpHeaders() {{
//            String auth = username + ":" + password;
//            byte[] encodedAuth = Base64.encodeBase64(
//                    auth.getBytes(Charset.forName("US-ASCII")));
//            String authHeader = "Basic " + new String(encodedAuth);
            set("Authorization", auth);
            set("Host", host);
            set("Referer", referer);
            set("Connection", connection);
        }};
    }

    HttpHeaders createHeaders(String auth, String referer) {
        return new HttpHeaders() {{
            set("Authorization", auth);
            set("Referer", referer);
        }};
    }

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


    //http://localhost:8080/pentaho/post
    @RequestMapping(value = "/**", method = RequestMethod.GET)
    public void doProxy(Model model,
                        HttpServletRequest request,
                        HttpServletResponse response) throws URISyntaxException, IOException {

        String uri = request.getRequestURI();
        HttpHeaders headers = createHeaders(BASIC_AUTH, REFERER, CONNECTION);

        HttpEntity<?> httpRequest = new HttpEntity<>(headers);
//        ResponseEntity<String> httpResponce = restTemplate.exchange(PENTAHO_URL + url, HttpMethod.GET, httpRequest, String.class);

        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", 8081));
        requestFactory.setProxy(proxy);

        RestTemplate restTemplate = new RestTemplate(requestFactory);
        restTemplate.setRequestFactory(requestFactory);

        ResponseEntity<Resource> responseEntity = restTemplate.exchange("http://www.google.it", HttpMethod.GET, httpRequest, Resource.class);

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

//        response.setHeader(contentTypeName, contentTypevalue);
        response.setHeader("Content-Type", "text/html;charset=UTF-8");

        try {

            response.getOutputStream().print(result.toString());
            response.getOutputStream().close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        return null;
    }

}
