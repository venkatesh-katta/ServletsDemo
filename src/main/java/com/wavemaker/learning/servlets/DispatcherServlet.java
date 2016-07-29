package com.wavemaker.learning.servlets;

import com.wavemaker.learning.models.Products;
import com.wavemaker.learning.response.ProductDetailsResponse;
import com.wavemaker.learning.response.ProductsResponse;
import com.wavemaker.learning.response.RedirectResponse;
import com.wavemaker.learning.service.LogoutService;
import com.wavemaker.learning.service.ProductDetailsService;
import com.wavemaker.learning.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by venkateswarluk on 21/7/16.
 */
public class DispatcherServlet extends HttpServlet {

    private static final Map<MappingDetails, String> patternsToService = new HashMap<>();
    public static final String REDIRECT = "redirect";
    private final ServiceDelegate serviceDelegate = new ServiceDelegate();

    @Override
    public void init() throws ServletException {
        // should be in context loader
        patternsToService.put(new MappingDetails("service/index.jsp"), REDIRECT);
        patternsToService.put(new MappingDetails("service/logout"), "logoutService");
        patternsToService.put(new MappingDetails("service/productslist"), "productService");
        patternsToService.put(new MappingDetails("service/productDetails", new String[]{"id"}), "productDetails");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServiceObject serviceObject = matchRequest(req);
        if (serviceObject != null) {
            serviceDelegate.delegateService(serviceObject, req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    private ServiceObject matchRequest(HttpServletRequest request) {
        for (Map.Entry<MappingDetails, String> patternService : patternsToService.entrySet()) {
            MappingDetails mappingDetails = patternService.getKey();
            if (request.getRequestURI().contains(mappingDetails.getUrlPattern())) {
                ServiceObject serviceObject = new ServiceObject();
                serviceObject.setServiceName(patternService.getValue());
                List<String> inputs = new ArrayList<>();
                List<String> params = mappingDetails.getParams();
                if (params != null) {
                    for (String param : params) {
                        String parameterValue = request.getParameter(param);
                        inputs.add(parameterValue);
                    }
                }
                serviceObject.setInputs(inputs);
                return serviceObject;
            }
        }
        return null;
    }

    private static class MappingDetails {
        private String urlPattern;
        private List<String> params;

        public MappingDetails(String urlPattern) {
            this.urlPattern = urlPattern;
        }

        public MappingDetails(String urlPattern, String[] params) {
            this.urlPattern = urlPattern;
            this.params = Arrays.asList(params);
        }

        public String getUrlPattern() {
            return urlPattern;
        }

        public void setUrlPattern(String urlPattern) {
            this.urlPattern = urlPattern;
        }

        public List<String> getParams() {
            return params;
        }

        public void setParams(List<String> params) {
            this.params = params;
        }
    }

    private static class ServiceObject {
        private String serviceName;
        private List<String> inputs;

        public String getServiceName() {
            return serviceName;
        }

        public void setServiceName(String serviceName) {
            this.serviceName = serviceName;
        }

        public List<String> getInputs() {
            return inputs;
        }

        public void setInputs(List<String> inputs) {
            this.inputs = inputs;
        }
    }

    private class ServiceDelegate {

        public void delegateService(ServiceObject serviceObj, HttpServletRequest req, HttpServletResponse resp) {
            try {
                if ("logoutService".equals(serviceObj.getServiceName())) {
                    new LogoutService().doAction(serviceObj.getInputs(), req, resp);
                } else if ("productService".equals(serviceObj.getServiceName())) {
                    List<Products> productsList = new ProductService().doAction(serviceObj.getInputs(), req, resp);
                    new ProductsResponse().viewRender(req,resp, productsList);
                } else if ("productDetails".equals(serviceObj.getServiceName())) {
                    List<Products> productsList = new ProductDetailsService().doAction(serviceObj.getInputs(), req, resp);
                    new ProductDetailsResponse().viewRender(req,resp, productsList);
                } else if (REDIRECT.equals(serviceObj.getServiceName())) {
                    new RedirectResponse().viewRender(req,resp, "index.jsp");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ServletException e) {
                e.printStackTrace();
            }

        }
    }
}
