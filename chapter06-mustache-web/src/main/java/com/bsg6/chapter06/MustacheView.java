package com.bsg6.chapter06;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Locale;
import java.util.Map;

import com.samskivert.mustache.Mustache.Compiler;
import com.samskivert.mustache.Template;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.core.io.Resource;
import org.springframework.web.servlet.view.AbstractTemplateView;

public class MustacheView  extends AbstractTemplateView {

    private Compiler compiler;

    private String charset;

    /**
     * Set the Mustache compiler to be used by this view.
     * <p>
     * Typically this property is not set directly. Instead a single {@link Compiler} is
     * expected in the Spring application context which is used to compile Mustache
     * templates.
     * @param compiler the Mustache compiler
     */
    public void setCompiler(Compiler compiler) {
        this.compiler = compiler;
    }

    /**
     * Set the charset used for reading Mustache template files.
     * @param charset the charset to use for reading template files
     */
    public void setCharset(String charset) {
        this.charset = charset;
    }

    @Override
    public boolean checkResource(Locale locale) throws Exception {
        Resource resource = getApplicationContext().getResource(getUrl());
        return (resource != null && resource.exists());
    }

    @Override
    protected void renderMergedTemplateModel(Map<String, Object> model, HttpServletRequest request,
                                             HttpServletResponse response) throws Exception {
        Template template = createTemplate(getApplicationContext().getResource(getUrl()));
        if (template != null) {
            template.execute(model, response.getWriter());
        }
    }

    private Template createTemplate(Resource resource) throws IOException {
        try (Reader reader = getReader(resource)) {
            return this.compiler.compile(reader);
        }
    }

    private Reader getReader(Resource resource) throws IOException {
        if (this.charset != null) {
            return new InputStreamReader(resource.getInputStream(), this.charset);
        }
        return new InputStreamReader(resource.getInputStream());
    }

}
