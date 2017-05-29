package pentaho.interceptor;

import org.apache.http.HttpResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created by norman on 26/05/17.
 */
public class PentahoLoginInterceptor extends HandlerInterceptorAdapter {

    private static String PENTAHO_URL = "http://localhost:8080";
    private static String BASIC_AUTH = "Basic QWRtaW46cGFzc3dvcmQ=";
    private static String HOST = "http://localhost:8080/pentaho";
    private static String REFERER = "http://localhost:8081/pentaho";
    private static String CONNECTION = "keep-alive";

    private static final String SET_COOKIE = "Set-Cookie";
    private static final String COOKIE_VALUE_DELIMITER = ";";
    private static final char NAME_VALUE_SEPARATOR = '=';

    private HttpHeaders createHeaders(String auth, String referer, String connection) {
        return new HttpHeaders() {{
            set("Authorization", auth);
            set("Referer", referer);
            set("Connection", connection);
        }};
    }

    //before the actual handler will be executed
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler)
            throws Exception {

        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);


        String uri = request.getRequestURI();
        String pentaho_request_url = uri.replace("/report/restBaseAuth", "/pentaho");
        String rep = "/api/repos/%3Apublic%3ASteel%20Wheels%3AMySalesDashboard.xdash/generatedContent";

        HttpHeaders headers = createHeaders(BASIC_AUTH, REFERER, CONNECTION);
        HttpEntity<?> httpRequest = new HttpEntity<>(headers);

        String resultUrl = java.net.URLDecoder.decode(PENTAHO_URL + pentaho_request_url + rep, "UTF-8");

        RestTemplate restTemplate = new RestTemplate();

        Map hashMapCookie = new HashMap();
        try {
            ResponseEntity<Resource> responseEntity = restTemplate.exchange
                    (resultUrl, HttpMethod.GET, httpRequest, Resource.class);

            List<String> headerCookies = responseEntity.getHeaders().get(SET_COOKIE);


            if (headerCookies.size() > 0) {
                String headerName = headerCookies.get(0);

                StringTokenizer st = new StringTokenizer(headerName, COOKIE_VALUE_DELIMITER);
                if (st.hasMoreTokens()) {
                    String token = st.nextToken();
                    String name = token.substring(0, token.indexOf(NAME_VALUE_SEPARATOR));
                    String value_name = token.substring(token.indexOf(NAME_VALUE_SEPARATOR) + 1, token.length());

                    hashMapCookie.put(name, value_name);
                    token = st.nextToken();
                    hashMapCookie.put(token.substring(0, token.indexOf(NAME_VALUE_SEPARATOR)).toLowerCase().replaceAll("\\s+", ""),
                            token.substring(token.indexOf(NAME_VALUE_SEPARATOR) + 1, token.length()));
                }
            }

        } catch (HttpClientErrorException e) {
            // 401 Unauthorized
        }

        if (hashMapCookie.get("JSESSIONID") != null &&
                hashMapCookie.get("path") != null) {
            Cookie cookie = new Cookie("JSESSIONID", hashMapCookie.get("JSESSIONID").toString());
            cookie.setPath(hashMapCookie.get("path").toString());
            cookie.setDomain("localhost");
            cookie.setHttpOnly(true);

            response.addCookie(cookie);
        }


        return true;
    }

    public void postHandle(
            HttpServletRequest request, HttpServletResponse response,
            Object handler, ModelAndView modelAndView)
            throws Exception {

        long startTime = (Long) request.getAttribute("startTime");

        long endTime = System.currentTimeMillis();

        long executeTime = endTime - startTime;

        //modified the exisitng modelAndView
        modelAndView.addObject("executeTime", executeTime);

    }
}
