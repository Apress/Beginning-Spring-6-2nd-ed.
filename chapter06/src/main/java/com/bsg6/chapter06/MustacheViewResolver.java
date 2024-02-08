package com.bsg6.chapter06;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Mustache.Compiler;
import org.springframework.web.servlet.view.AbstractTemplateViewResolver;
import org.springframework.web.servlet.view.AbstractUrlBasedView;

public class MustacheViewResolver extends AbstractTemplateViewResolver {

    private final Compiler compiler;

    private String charset;

    public MustacheViewResolver() {
        this.compiler = Mustache.compiler();
        setViewClass(requiredViewClass());
    }

    public MustacheViewResolver(Compiler compiler) {
        this.compiler = compiler;
        setViewClass(requiredViewClass());
    }

    @Override
    protected Class<?> requiredViewClass() {
        return MustacheView.class;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    @Override
    protected AbstractUrlBasedView buildView(
            String viewName
    ) throws Exception {
        var view = (MustacheView) super.buildView(viewName);
        view.setCompiler(this.compiler);
        view.setCharset(this.charset);

        return view;
    }

    @Override
    protected AbstractUrlBasedView instantiateView() {
        if((getViewClass() == MustacheView.class)) {
            return new MustacheView();
        } else {
            return super.instantiateView();
        }
    }
}
