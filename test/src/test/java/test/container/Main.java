package test.container;

import javax.swing.JFrame;

import com.nadaletti.impl.annotation.Component;
import com.nadaletti.impl.container.BeanContainer;

@Component
public class Main {

    public static void main(String[] args) {
        CdiTest.startApplication();

        GUIProvider provider = BeanContainer.getBean(GUIProvider.class);
        JFrame defaultFrame = provider.defaultFrame();

        defaultFrame.setVisible(true);
    }
}
