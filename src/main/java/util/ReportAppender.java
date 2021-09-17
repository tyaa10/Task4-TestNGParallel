package util;

import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.apache.logging.log4j.core.layout.AbstractStringLayout;
import org.testng.Reporter;

import java.io.Serializable;

@Plugin(name="Reporter", category ="Core", elementType="appender", printObject=true)
public class ReportAppender extends AbstractAppender {

    private ReportAppender(final String name, final Layout layout) {
        super(name, null, layout, false, null);
    }

    @Override
    public void append(final LogEvent event) {
        final Layout<? extends Serializable> layout = getLayout();
        if (layout instanceof AbstractStringLayout) {
            Reporter.log(((AbstractStringLayout) layout).toSerializable(event));
        } else {
            Reporter.log(event.getMessage().getFormattedMessage());
        }
    }

    @PluginFactory
    public static ReportAppender createAppender(
    @PluginAttribute("name") @Required(message = "A name for the Appender must be specified") final String name,
    @PluginElement("Layout") Layout<? extends Serializable> layout) {
        return new ReportAppender(name, layout);
    }
}
