package pl.edu.pjwstk.jaz.servelet;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@ApplicationScoped
public class ParamRetriever {
    @Inject
    private HttpServletRequest request;

    public boolean contains(String paramKey) {
        return request.getParameter(paramKey) != null;
    }

    public Long getLong(String paramKey) {
        var paramValue = request.getParameter(paramKey);
        return Long.parseLong(paramValue);
    }
}
