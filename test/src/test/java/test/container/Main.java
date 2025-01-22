package test.container;

import javax.swing.JFrame;

import com.nadaletti.impl.container.BeanContainer;
import com.nadaletti.impl.inject.Injectable;

@Injectable
public class Main {

    public static void main(String[] args) {
        CdiTest.startApplication();

        GUIProvider provider = BeanContainer.getBean(GUIProvider.class);
        JFrame defaultFrame = provider.defaultFrame();

        defaultFrame.setVisible(true);
    }
}
