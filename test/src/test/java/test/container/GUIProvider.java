package test.container;

import java.awt.Dimension;

import javax.swing.JFrame;

import com.nadaletti.impl.annotation.Component;
import com.nadaletti.impl.annotation.Singleton;
import com.nadaletti.impl.container.BeanContainer;
import com.nadaletti.impl.lifecycle.Initializable;

@Singleton
@Component
public class GUIProvider implements Initializable {

    private JFrame defaultFrame;

    /**
     * Provides a default configured JFrame
     *
     * @return a JFrame
     */
    public JFrame defaultFrame() {
        return defaultFrame;
    }

    public void afterPropertiesSet() {
        defaultFrame = new JFrame();
        defaultFrame.setSize(new Dimension(600, 300));
        defaultFrame.setTitle(BeanContainer.getConfigValue("appName", String.class));
        defaultFrame.setDefaultCloseOperation(1);
    }
}
