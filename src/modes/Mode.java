package modes;

import java.awt.event.MouseAdapter;

public interface Mode {
    public void handle();

    public MouseAdapter getTrigger();
}
