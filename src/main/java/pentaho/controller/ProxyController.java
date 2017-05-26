package pentaho.controller;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Controller
@RequestMapping("/proxy")
public class ProxyController {
    private final Logger logger = LogManager.getLogger(ProxyController.class);


    private static String PENTAHO_URL = "http://localhost:8080";
    private static String BASIC_AUTH = "Basic QWRtaW46cGFzc3dvcmQ=";

    @RequestMapping(value = "/**", method = RequestMethod.GET)
    public void doProxy(Model model,
                          HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();
        String pentahoRequest = uri.replace("/proxy", "");

        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(PENTAHO_URL + pentahoRequest);


//        httpGet.setHeader("Host","http://localhost:8080/pentaho");
        httpGet.setHeader("Connection", "keep-alive");
        httpGet.setHeader("Authorization", BASIC_AUTH);
        httpGet.setHeader("Referer", "http://localhost:8081/proxy");
        HttpResponse httpResponse = httpClient.execute(httpGet);

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
        response.setHeader("Content-Type", "text/html;charset=UTF-8");

//        response.setHeader(contentType.getName(), contentType.getValue());

        response.getOutputStream().print(result.toString());
        response.getOutputStream().close();
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(Model model) {
        return "test";
    }


/*

httpget.setHeader("Authorization", BASIC_AUTH);
        httpget.setHeader("User-Agent", "Mozilla/5.0");
        httpget.setHeader("Accept",
                "text/html,application/xhtml+xml,application/xml;q=0.9,);
            httpget.setHeader("Accept-Language", "en-US,en;q=0.5");

    HttpResponse httpResponse  = null;
        System.out.println("executing request " + httpget.getRequestLine());
    HttpGet rq = new HttpGet(pentahoRequest);
        try {
        httpResponse = client.execute(rq);
        InputStream inpppp = httpResponse.getEntity().getContent();
        response.getWriter().write(httpResponse.getEntity().getContent().toString());
    } catch (IOException e) {
        e.printStackTrace();
    }
        return null;

 */
}
