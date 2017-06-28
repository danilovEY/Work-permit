package ru.kolaer.permit.spring.component;

import freemarker.ext.jsp.TaglibFactory;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

/**
 * Created by danilovey on 28.06.2017.
 */
public class DefaultFreeMarkerConfigurer extends FreeMarkerConfigurer {
    @Override
    public TaglibFactory getTaglibFactory() {
        TaglibFactory tagLibFactory = super.getTaglibFactory();
        if (tagLibFactory.getObjectWrapper()==null) {
            tagLibFactory.setObjectWrapper(super.getConfiguration().getObjectWrapper());
        }
        return tagLibFactory;
    }
}