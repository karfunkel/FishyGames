package fishygames.ui

import javax.swing.JComponent
import javax.swing.JRootPane
import javax.swing.plaf.ComponentUI
import javax.swing.plaf.PanelUI
import javax.swing.plaf.RootPaneUI
import javax.swing.plaf.basic.BasicRootPaneUI
import javax.swing.UIManager

class FishyRootPaneUI extends BasicRootPaneUI {
    private static RootPaneUI componentUI = new FishyRootPaneUI()
    private static PanelUI contentPaneUI = new FishyContentPaneUI()

    static ComponentUI createUI(JComponent c) {
        return componentUI
    }

    @Override
    void installUI(JComponent c) {
        super.installUI(c)
        if (c instanceof JRootPane) {
            c.contentPane.setUI(contentPaneUI)
        }
    }
}
